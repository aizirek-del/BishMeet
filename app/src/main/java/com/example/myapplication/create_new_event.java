package com.example.myapplication;

import static java.text.DateFormat.getDateInstance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class create_new_event extends AppCompatActivity {
    AutoCompleteTextView dropDownText;
    private EditText titlevent, Evdescription, eventLocation;
    private ImageView Event_imgView;
    ImageButton imgBtn;

    DatabaseReference mDatabase;
    StorageReference mStorage;
    private Uri uri;
    private StorageTask storageTask;
    private String EVENT_KEY = "events";
    private static final int PICK_IMAGE_REQUEST = 1;

    EditText mdateformat;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    private DatabaseReference groupsDatabase;
    private List<String> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);

        titlevent = (EditText) findViewById(R.id.eventTitle);
        Evdescription = (EditText) findViewById(R.id.eventDescription);
        eventLocation = (EditText) findViewById(R.id.event_location);
        Event_imgView = findViewById(R.id.addEventImg);


        imgBtn = findViewById(R.id.gobck);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(create_new_event.this, Home_after_authorization.class));

            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference("events");
        mStorage = FirebaseStorage.getInstance().getReference(EVENT_KEY);

        groupsDatabase = FirebaseDatabase.getInstance().getReference("groups");
        dropDownText = findViewById(R.id.autoComplete);

        groupsDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                items.add(newData.title);
                initDropDown(items);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                items.add(newData.title);
                initDropDown(items);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                items.add(newData.title);
                initDropDown(items);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                items.add(newData.title);
                initDropDown(items);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mdateformat = findViewById(R.id.dateformat);
        Calendar calendar = Calendar.getInstance();
        mdateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                //hour = calendar.get(Calendar.HOUR_OF_DAY);
                //minute= calendar.get(Calendar.MINUTE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(create_new_event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String sDate = dayOfMonth + "." + month + "." + year;
                        mdateformat.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        mdateformat.setText(sDate);
                 //messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getMessageTime()));
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

    }

    private void initDropDown(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, items);
        dropDownText.setText(adapter.getItem(0).toString(), false);
        dropDownText.setAdapter(adapter);

    }

    public void createEvent(View view) {
        String event_id = mDatabase.push().getKey();
        String group_id = dropDownText.getAdapter().toString().trim();
        String event_title = titlevent.getText().toString().trim();
        String event_decription = Evdescription.getText().toString().trim();
        String event_location = eventLocation.getText().toString().trim();
        String event_date = mdateformat.getText().toString().trim();
        List<User> users = new ArrayList<>();


        if (!TextUtils.isEmpty(event_title)) {

            NewEvent event = new NewEvent(event_id, group_id, event_title, event_decription,
                    event_location, event_date, users, " ");
            mDatabase.child(event_id).setValue(event);
            Toast.makeText(this, "Добавлена", Toast.LENGTH_SHORT).show();
            uploadFile(event_id);
        } else {
            Toast.makeText(this, "Введите название", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(create_new_event.this, "Загрузка...", Toast.LENGTH_LONG).show();
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(create_new_event.this, Create_new_group.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    public void selectFoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(String key) {
        if (uri != null) {
            StorageReference fileReference = mStorage.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            storageTask = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.e("Create_NewEvent", "Done OnSuccess");
                            Task<Uri> downloadUri = fileReference.getDownloadUrl();
                            downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    mDatabase.child(key).child("event_image").setValue(imageUrl);
                                }
                            });
                        }
                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Log.e("Create_Newevent", "Done OnComplete");
                            Toast.makeText(create_new_event.this, "Добавлена", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Log.e("Create_NewGRoup", e.getMessage());
                            Toast.makeText(create_new_event.this, "Ошибка", Toast.LENGTH_LONG).show();
                            PackageManager pm = getPackageManager();
                            pm.setComponentEnabledSetting(new ComponentName(create_new_event.this, Create_new_group.class),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                        }
                    });
        } else {
            Toast.makeText(this, "Файл не выбран", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
            if (resultCode == RESULT_OK) {
                Log.d("My tag", "Image uri" + data.getData());
                Event_imgView.setImageURI(data.getData());
            }
        }
    }


}