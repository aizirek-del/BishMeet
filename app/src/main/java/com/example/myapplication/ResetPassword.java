package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    ImageButton imgButton;
    EditText editEmail;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance();
        imgButton = findViewById(R.id.resetBack_btn);

        editEmail = findViewById(R.id.resetEmail);


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResetPassword.this, Entry_page.class);
                startActivity(intent);
            }
        });

    }

    public void sendToSystem(View view) {

        String userEmail = editEmail.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(ResetPassword.this, "Пожалуйста, введите свой Email правильно!", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPassword.this, "Пожалуйста проверьте свою почту,для восстановления адреса ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPassword.this,LoginActivity.class));
                    }else{
                        String message = task.getException().getMessage();
                        Toast.makeText(ResetPassword.this, "Ошибка"+message, Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }
}