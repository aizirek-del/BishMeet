package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Detailed_group extends AppCompatActivity {
    TextView tvTitle,tvdesc,tvInterest,tvCategory;
    ImageView ivClub;
    DatabaseReference databaseReference;
    StorageReference mStorageRef;
    ImageButton imgBtn;
    private final List<NewGroupData> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_group);


        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");


        tvTitle = findViewById(R.id.titleOfclub);
        ivClub = findViewById(R.id.imgofClub);
        tvdesc = findViewById(R.id.descriptionClub);
        tvInterest = findViewById(R.id.interestOfClub);
        tvCategory=findViewById(R.id.categoryClub);

        imgBtn = findViewById(R.id.back_button);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Detailed_group.this,Home_after_authorization.class));
            }
        });

        getIntentMain();
    }
    private void getIntentMain(){
        Intent  in = getIntent();
        if(in!= null){
            tvTitle.setText(in.getStringExtra("title"));
            Picasso.get().load(Uri.parse(in.getStringExtra("image")) ).into(ivClub);//Здесь внимательнее
            tvdesc.setText(in.getStringExtra("description"));
            tvInterest.setText(in.getStringExtra("interest"));
            tvCategory.setText(in.getStringExtra("category"));
            startActivity(in);
        }


    }


    public void joinGroup(View view) {


    }
}