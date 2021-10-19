package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText Log_edit,login_password;
    ImageButton previousBtn;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log_edit = findViewById(R.id.edit_Email_Login);
        login_password = findViewById(R.id.LoginsPassword);
        firebaseAuth = FirebaseAuth.getInstance();
progressDialog = new ProgressDialog(this);
progressDialog.setMessage("Вход..");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser!= null){
            Toast.makeText(LoginActivity.this, "Найден пользователь", Toast.LENGTH_SHORT).show();
        }
            
    }

    public void fromLoginBack(View view) {
        startActivity(new Intent(LoginActivity.this,Entry_page.class));
    }

    public void forgetPassword(View view) {
        startActivity(new Intent(LoginActivity.this,ResetPassword.class));
    }

    public void Registernow(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterPage.class));
    }

    public void EnterFirstTime(View view) {

        String email = Log_edit.getText().toString();
        String passw = login_password.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Log_edit.setError("Неверный email");
            Log_edit.setFocusable(true);
        }else{
            loginUser(email,passw);
        }

    }

    private void loginUser(String email, String passw) {
        progressDialog.dismiss();

      firebaseAuth.signInWithEmailAndPassword(email,passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                         //   progressDialog.dismiss();
                            FirebaseUser fbUser = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,Home_after_authorization.class));
                            finish();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Аутентификация не успешна", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Неправильный пароль", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }
}