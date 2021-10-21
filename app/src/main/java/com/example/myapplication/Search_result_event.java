package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Search_result_event extends AppCompatActivity {

    private ArrayList<searchResult> SearchList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_event);

        searchResultAdapter SRadapter = new searchResultAdapter(this,SearchList);

        RecyclerView recycler = findViewById(R.id.recycler_viewRSearch);
        recycler.setLayoutManager(new GridLayoutManager(this,RecyclerView.VERTICAL));
        recycler.setAdapter(SRadapter);


        SRadapter.notifyDataSetChanged();

        initList();



    }

    private void initList() {

        SearchList.add(new searchResult("GDG Bishkek","DevFest Bishkek","24-июля|14:00-21:00",R.drawable.fotoff,R.drawable.time));
        SearchList.add(new searchResult("GDG Bishkek","DevFest Bishkek","24-июля|14:00-21:00",R.drawable.fotoff,R.drawable.time));
        SearchList.add(new searchResult("GDG Bishkek","DevFest Bishkek","24-июля|14:00-21:00",R.drawable.fotoff,R.drawable.time));
        SearchList.add(new searchResult("GDG Bishkek","DevFest Bishkek","24-июля|14:00-21:00",R.drawable.fotoff,R.drawable.time));
    }
}