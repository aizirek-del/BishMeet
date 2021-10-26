package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event_detailed extends AppCompatActivity {
    Button btn_choose;
    ImageView group_img, event_img;
    TextView ev_titleOf_gr, ev_title, time_event, ev_location, ev_description;
    ImageButton b_btn;

    DatabaseReference mDatareference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detailed);

        b_btn = findViewById(R.id.b_btn);
        group_img = findViewById(R.id.gr_image_in_evDetailed);
        event_img = findViewById(R.id.foto_event_detailed);
        ev_titleOf_gr = findViewById(R.id.gr_title_in_evDetailed);
        ev_title = findViewById(R.id.title_of_event_detailed);
        time_event = findViewById(R.id.time_of_event_detailed);
        ev_location = findViewById(R.id.location_of_event_detailed);
        ev_description = findViewById(R.id.description_event_detailed);

        mDatareference = FirebaseDatabase
                .getInstance().getReference("events")
                .child(getIntent().getStringExtra("event_id"));


        b_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Event_detailed.this, Home_after_authorization.class));
            }
        });

        mDatareference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NewEvent event = snapshot.getValue(NewEvent.class);
                initView(event);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_choose = findViewById(R.id.join_to_event);
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Select[] items = {
                        new Select("не пойду", R.drawable.ic_baseline_cancel_24),
                        new Select("Пойду", R.drawable.ic_baseline_check_circle_outline_24),
                        new Select("интересно", R.drawable.ic_favorite)
                };
                ListAdapter adapter = new ArrayAdapter<Select>(
                        Event_detailed.this,
                        android.R.layout.select_dialog_item,
                        android.R.id.text1,
                        items) {
                    public View getView(int position, View convertView, ViewGroup parent) {
                        //Use super class to create the View
                        View v = super.getView(position, convertView, parent);
                        TextView tv = (TextView) v.findViewById(android.R.id.text1);

                        //Put the image on the TextView
                        tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);

                        //Add margin between image and text (support various screen densities)
                        int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                        tv.setCompoundDrawablePadding(dp5);

                        PackageManager pm = getPackageManager();
                        pm.setComponentEnabledSetting(new ComponentName(Event_detailed.this, Event_detailed.class),
                                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

                        return v;
                    }
                };


                new AlertDialog.Builder(Event_detailed.this)
                        .setTitle("Ваш ответ:")
                        .setIcon(R.drawable.ic_baseline_close_24)
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                if (item == 0) {
                                    btn_choose.setText(items[0].toString());
                                    btn_choose.setBackgroundColor(getResources().getColor(R.color.browser_actions_bg_grey));
                                } else if (item == 1) {
                                    btn_choose.setText(items[1].toString());
                                    btn_choose.setBackgroundColor(getResources().getColor(R.color.blue));
                                    saveMyIdToDb();
                                    // btn_choose.setBackground(getDrawable(R.drawable.ic_white_check));


                                } else {
                                    btn_choose.setText(items[2].toString());
                                    // btn_choose.setBackgroundTintMode(Color.BLUE);
                                }
                            }
                        }).show();
            }
        });


    }

    private void saveMyIdToDb() {
        String id = FirebaseAuth.getInstance().getUid();
        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("User").child(id);

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                Map<String, Object> map = new HashMap<>();
                map.put(id, user);
                mDatareference.child("users").updateChildren(map);
                Log.e("Event_detailed", "Saving my Id");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initView(NewEvent event) {
        {
            ev_title.setText(event.eventTitle);
            Picasso.get().load(Uri.parse(event.event_image)).into(event_img);//Здесь внимательнее
            ev_description.setText(event.eventDescription);
            time_event.setText(event.Event_date);
            ev_location.setText(event.eventLocation);
            ev_titleOf_gr.setText(event.groupData.title);
            Picasso.get().load(Uri.parse(event.groupData.imageUri)).into(group_img);
        }
    }

    private class Select {
        public final String text;
        public final int icon;

        public Select(String text, Integer icon) {
            this.text = text;
            this.icon = icon;
        }

        @Override
        public String toString() {

            return text;
        }
    }
}