package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Create_new_group extends AppCompatActivity {

    EditText editText, editDesc, editInterest;
    Spinner spinnerCategory;
    Button btn;
    DatabaseReference databaseReference;
    private static final int PICK_IMAGE_REQUEST = 1;
    Button mSelectButton;
    ImageView mImage;
    Uri imageUri;
    StorageReference mStorageRef;
    private StorageTask storageTask;
    ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group);

        databaseReference = FirebaseDatabase.getInstance().getReference("groups");
        mStorageRef = FirebaseStorage.getInstance().getReference("groups_images");

        editText = (EditText) findViewById(R.id.titleEdT);
        editDesc = (EditText) findViewById(R.id.decriptn);
        editInterest = (EditText) findViewById(R.id.interest);
        spinnerCategory = (Spinner) findViewById(R.id.spinner_menu);
        btn = (Button) findViewById(R.id.createbtn);
        mSelectButton = (Button) findViewById(R.id.pickFoto);
        mImage = (ImageView) findViewById(R.id.addImg);
        backBtn = findViewById(R.id.goBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Create_new_group.this, Home_after_authorization.class));
            }
        });


        mSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChooser();
            }
        });

        //Functions of Button here;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                Toast.makeText(Create_new_group.this, "Загрузка...", Toast.LENGTH_LONG).show();
                 }

        });

    }
//Image uploading

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(String key) {
        if (imageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            storageTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.e("Create_NewGRoup", "Done OnSuccess");
                            Task<Uri> downloadUri = fileReference.getDownloadUrl();
                            downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    databaseReference.child(key).child("imgUri").setValue(imageUrl);
                                }
                            });

                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Log.e("Create_NewGRoup", "Done OnComplete");
                            Toast.makeText(Create_new_group.this, "Добавлена", Toast.LENGTH_SHORT).show();
                            PackageManager pm = getPackageManager();
                            pm.setComponentEnabledSetting(new ComponentName(Create_new_group.this, Create_new_group.class),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

                            finish();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Create_NewGRoup", e.getMessage());
                            Toast.makeText(Create_new_group.this, "Ошибка", Toast.LENGTH_LONG).show();
                            PackageManager pm = getPackageManager();
                            pm.setComponentEnabledSetting(new ComponentName(Create_new_group.this, Create_new_group.class),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                        }
                    });


        } else {
            Toast.makeText(this, "No file  selected", Toast.LENGTH_SHORT).show();
        }
    }

    //here we choose image from our phone gallery;
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            if (resultCode == RESULT_OK) {
                Log.d("My tag", "Image uri" + data.getData());
                mImage.setImageURI(data.getData());
            }

        }
    }
//Picasso.get().load(imgUri).into(mImage);

    public void uploadImage() {
        Bitmap bitmap = ((BitmapDrawable) mImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        final StorageReference myRef = mStorageRef.child(System.currentTimeMillis() + "myREf");
        UploadTask up = myRef.putBytes(byteArray);

        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return myRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                imageUri = task.getResult();
            }
        });

    }

    private void init() {
        String id = editText.getText().toString().trim();
        String titleGr = editText.getText().toString().trim();
        String description = editDesc.getText().toString().trim();
        String interest = editInterest.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        List<User> users = new ArrayList<>();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("User")
                .child(userId);
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                users.add(user);
                createDb(new NewGroupData(id,users, titleGr, description, interest, category, " "));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void createDb(NewGroupData group) {
        if (!TextUtils.isEmpty(group.title)&&!TextUtils.isEmpty(group.decription)&&!TextUtils.isEmpty(group.interest)) {
            if (group.id != null) databaseReference.child(group.id).setValue(group);
            uploadFile(group.id);
        } else {
            Toast.makeText(this, "Введите название", Toast.LENGTH_SHORT).show();
        }

    }
}
//editText.getText().toString().trim(),
//                                    taskSnapshot.getMetadata().getReference().getDownloadUrl().toString()