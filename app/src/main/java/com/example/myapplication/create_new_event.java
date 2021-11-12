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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    EditText mdateformat, hour_time;
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

        hour_time = findViewById(R.id.hour_minute);
        Calendar calendar = Calendar.getInstance();
        mdateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

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
        hour_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                hour_time.setText(SimpleDateFormat.getTimeInstance().format(calendar.getTime()));


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
                String group_id = dropDownText.getText().toString().trim();
                String event_title = titlevent.getText().toString().trim();
                String event_decription = Evdescription.getText().toString().trim();
                String event_location = eventLocation.getText().toString().trim();
                String event_date = mdateformat.getText().toString().trim();
                Map<String, User> users = new HashMap<>();

                DatabaseReference mDatareference = FirebaseDatabase.getInstance().getReference("groups").child(group_id);

                mDatareference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NewGroupData groupData = snapshot.getValue(NewGroupData.class);
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("User")
                                .child(userId);
                        userDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User user = snapshot.getValue(User.class);
                                users.put(userId, user);
                                createDb(new NewEvent(event_id, groupData, event_title,
                                        event_decription, event_location, event_date, users, " "));

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Toast.makeText(create_new_event.this, "Загрузка...", Toast.LENGTH_LONG).show();
                PackageManager pm = getPackageManager();
                pm.setComponentEnabledSetting(new ComponentName(create_new_event.this, Create_new_group.class),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            }

            public void createDb(NewEvent event) {

                if (!TextUtils.isEmpty(event.eventTitle)) {
                    mDatabase.child(event.Event_id).setValue(event);
                    uploadFile(event.Event_id);
                } else {
                    Toast.makeText(create_new_event.this, "Введите название", Toast.LENGTH_SHORT).show();
                }
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
                    Toast.makeText(create_new_event.this, "Файл не выбран", Toast.LENGTH_SHORT).show();
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



