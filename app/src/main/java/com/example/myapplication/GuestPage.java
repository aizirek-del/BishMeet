package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class GuestPage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView navigationView;
    FragmentManager fragm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);

        fragm=getSupportFragmentManager();

        navigationView = findViewById(R.id.navig_menu);
        navigationView.setOnItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.main);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main:
                fragm.beginTransaction().replace(R.id.container_line, new HomeFragment()).commit();
                break;

            case R.id.search:
                fragm.beginTransaction().replace(R.id.container_line, new ProfileFragment()).commit();
                break;
        }
        return true;
    }
}