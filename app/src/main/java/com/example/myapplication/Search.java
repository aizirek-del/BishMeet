package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Search extends AppCompatActivity {
    ImageView mdateformat2;
    int year;
    int month;
    int day;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mdateformat2 = findViewById(R.id.calendar);

        Calendar calendar = Calendar.getInstance();
        mdateformat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String sDate = dayOfMonth + "." + month + "." + year;
                        //  mdateformat.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        //mdateformat2.setText(sDate);

                        Intent intent = new Intent(Search.this, Search_result_event.class);
                        startActivity(intent);
                    }
                }, year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();



            }
        });
    }
    }

