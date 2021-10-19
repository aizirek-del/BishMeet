package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Entry_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
    }

    public void continueAsGuest(View view) {
       // Intent in = new Intent(Entry_page.this ,.class);
    }

    public void goToregister(View view) {
        Intent in = new Intent(Entry_page.this ,RegisterPage.class);
        startActivity(in);
    }

    public void GoToLogin(View view) {
        Intent in = new Intent(Entry_page.this ,LoginActivity.class);
        startActivity(in);
    }
}