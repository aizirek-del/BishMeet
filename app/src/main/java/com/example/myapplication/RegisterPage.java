package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {

   // private TokenBroadcastReceiver mTokenReceiver;


    ImageButton imgButton;
private EditText editLog,editPasw,editPassword;
ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        imgButton = findViewById(R.id.back_btn);

        editLog = findViewById(R.id.edit_Email);
        editPasw = findViewById(R.id.textIn);
        editPassword = findViewById(R.id.InputPassword);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Регистрация пользователя....");


        mAuth = FirebaseAuth.getInstance();


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterPage.this, Entry_page.class);
                startActivity(intent);
            }
        });
    }

    public void next(View view) {
        String email = editLog.getText().toString().trim();
        String password1 = editPasw.getText().toString().trim();
        String password2 = editPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editLog.setError("Неверный Email");
            editLog.setFocusable(true);
        }else if(password1.length()<6 ||password1.isEmpty()){
            editPasw.setError("Пожалуйста ,введите пароль   больше 6 символов");
            editPasw.setFocusable(true);
        }else if(!password2.equals(password1)){
            editPassword.setError("Пароли не совпадают.Повторите попытку");
            editPassword.setFocusable(true);
        }else{
            registerUser(email,password1,password2);
        }

    }

    private void registerUser(String email, String password1, String password2) {
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();

                            HashMap<Object,String> hashMap = new HashMap<>();
                            hashMap.put("email",email);
                            hashMap.put("uid",uid);
//                            hashMap.put("name","");
//                            hashMap.put("phone","");
//                            hashMap.put("image","");
//                            hashMap.put("cover","");


                            Toast.makeText(RegisterPage.this, " Вы успешно зарегистрировались"+user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterPage.this,Edit_profile.class));
                            finish();

                        } else {
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w( "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(RegisterPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText(RegisterPage.this, " "+e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void EnterToMain(View view) {
        Intent intent = new Intent(RegisterPage.this, LoginActivity.class);
        startActivity(intent);

    }
}