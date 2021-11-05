package com.example.myapplication;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class Edit_profile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference mDatabase;
    private final String USER_KEY = "User";
    EditText Editname, Editsurname, Editcity;
    Button mButton;
    private static final int PICK_IMAGE = 1;
    Uri imgUri;
    private StorageTask sTask;

    ImageView avatar_img;
    ImageButton imaBut;
    ProgressDialog pd;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        storageReference = FirebaseStorage.getInstance().getReference("users_images");
        imaBut = findViewById(R.id.editBackBtn);

        Editname = findViewById(R.id.editName);
        Editsurname = findViewById(R.id.edit_surname);
        Editcity = findViewById(R.id.edit_city);
        avatar_img = findViewById(R.id.avatar_foto);
        mButton = findViewById(R.id.changephoto);

        imaBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Edit_profile.this, Home_after_authorization.class));
            }
        });


    }

    private void init() {

        String id = FirebaseAuth.getInstance().getUid();
        String name = Editname.getText().toString();
        String surname = Editsurname.getText().toString();
        String city = Editcity.getText().toString();


        User newUser = new User(id, name, surname, city, " ");

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(city)) {
            if (id != null) mDatabase.child(id).setValue(newUser);
            uploadFile(id);
        } else {
            Toast.makeText(this, "Введите ...", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadFile(String key) {
        if (imgUri != null) {
            StorageReference mStorageRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));

            sTask = mStorageRef.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.e("Edit_Profile", "Done OnSuccess");
                            Task<Uri> dwnUri = mStorageRef.getDownloadUrl();
                            dwnUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String imageUrl = uri.toString();
                                    mDatabase.child(key).child("imgUri").setValue(imageUrl);



                                }
                            });
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Log.e("Edit_Profile", "Done OnComplete");
                            Toast.makeText(Edit_profile.this, "Добавлена", Toast.LENGTH_LONG).show();
                            finish();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Log.e("Create_NewGRoup", e.getMessage());
                            Toast.makeText(Edit_profile.this, "Ошибка", Toast.LENGTH_LONG).show();
                            PackageManager pm = getPackageManager();
                            pm.setComponentEnabledSetting(new ComponentName(Edit_profile.this, Create_new_group.class),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                        }
                    });

        } else {
            Toast.makeText(this, "No file  selected", Toast.LENGTH_SHORT).show();
        }

    }


    //Image uploading

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));


    }

    public void chooseImage(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imgUri = data.getData();
            if (resultCode == RESULT_OK) {
                Log.d("My tag", "Image uri" + data.getData());
                avatar_img.setImageURI(data.getData());
            }

        }
    }

    private void uploadImage() {
        Bitmap bitmap = ((BitmapDrawable) avatar_img.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        final StorageReference myRef = storageReference.child(System.currentTimeMillis() + "myREf");
        UploadTask up = myRef.putBytes(byteArray);

        Task<Uri> uriTask = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                return myRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                imgUri = task.getResult();

                Toast.makeText(Edit_profile.this, "Файл загружен", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void saveEnterProf(View view) {
        init();
    }
}