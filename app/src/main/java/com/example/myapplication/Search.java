package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Search extends AppCompatActivity {
    int year;
    int month;
    int day;
    ImageView mdateformat2;
    private final List<NewGroupData> mItems = new ArrayList<>();
    GroupsAdapter mAdapter;
    RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private final List<NewEvent> eventList = new ArrayList<>();
    RecyclerView verticalRecView;
    EventAdapter eventAdapter;
    DatabaseReference mDatareference;
    ProgressBar progress;
    private DatabaseReference databaseReference;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseReference = FirebaseDatabase.getInstance().getReference("groups");


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                mItems.add(newData);
                initRecycler(mItems);
                progressBar.setVisibility(View.INVISIBLE);
            }

            private void initRecycler(List<NewGroupData> list) {
                mAdapter = new GroupsAdapter(Search.this, list);
                mRecyclerView.setAdapter(mAdapter);

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                NewGroupData datas = snapshot.getValue(NewGroupData.class);
                String dataKey = snapshot.getKey();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String dataKey = snapshot.getKey();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewGroupData data = snapshot.getValue(NewGroupData.class);
                String dataKey = snapshot.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);
            }

        });


      //  verticalRecView = view.findViewById(R.id.vertical_recycler_view);
        mDatareference = FirebaseDatabase.getInstance().getReference("events");

        mDatareference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent events = snapshot.getValue(NewEvent.class);
                eventList.add(events);
                initEventRecycler(eventList);
                progress.setVisibility(View.GONE);
            }
            private void initEventRecycler(List<NewEvent> eList) {
                eventAdapter = new EventAdapter(Search.this, eList);
                verticalRecView.setAdapter(eventAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent event = snapshot.getValue(NewEvent.class);
                String key = snapshot.getKey();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                String key = snapshot.getKey();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent event = snapshot.getValue(NewEvent.class);
                String key = snapshot.getKey();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.setVisibility(View.INVISIBLE);

            }
        });


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

                    }
                }, year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();



            }
        });
    }
    }

