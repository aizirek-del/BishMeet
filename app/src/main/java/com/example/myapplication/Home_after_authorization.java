package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home_after_authorization extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    BottomNavigationView navView;
    FragmentManager mFragm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_after_authorization);


        mFragm=getSupportFragmentManager();

        navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(this);
        navView.setSelectedItemId(R.id.page_1);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                mFragm.beginTransaction().replace(R.id.contnr, new HomeFragment()).commit();
                break;

            case R.id.page_2:
                mFragm.beginTransaction().replace(R.id.contnr, new ProfileFragment()).commit();
                break;
        }
        return true;
    }
}