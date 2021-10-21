package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Result;


public class ProfileFragment extends Fragment {


    DatabaseReference databaseReference;
    TextView myProfileName, MyProfileSurname, MyProfileCity;

    ImageView myProfile_Foto;
    ProgressBar progressBar;
    ImageView imgEditProfile;
    GroupsAdapter mAdapter;
    RecyclerView mRecyclerView;

    private final List<NewGroupData> mItems = new ArrayList<>();
    // vertical list of events
    RecyclerView verticalRecView;
    EventAdapter eventAdapter;
    DatabaseReference mDatareference;
    ProgressBar progress;
    private final List<NewEvent> eventList = new ArrayList<>();




    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("User")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getParent();

        myProfileName = view.findViewById(R.id.myprofile_name);
        MyProfileSurname = view.findViewById(R.id.myprofile_Surname);
        MyProfileCity = view.findViewById(R.id.myProfile_city);
        myProfile_Foto = view.findViewById(R.id.MyProfile_foto);


        imgEditProfile = view.findViewById(R.id.editProfilePage);
        imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Edit_profile.class));
            }
        });
        //storageReference = getInstance().getReference();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);

                myProfileName.setText(user.name);
                MyProfileSurname.setText(user.surname);
                MyProfileCity.setText(user.city);
                Picasso.get().load(Uri.parse(user.imageUri)).into(myProfile_Foto);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//horizontal rrecycler

        mRecyclerView = view.findViewById(R.id.profile_hor_recycler_view);

        progress =view.findViewById(R.id.in_prog);

        databaseReference = FirebaseDatabase.getInstance().getReference("groups");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                mItems.add(newData);
                initRecycler(mItems);
               progressBar.setVisibility(View.INVISIBLE);
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

        //vertical recycler

        verticalRecView = view.findViewById(R.id.profiles_vert_recycler_view);
        mDatareference =FirebaseDatabase.getInstance().getReference("events");

        mDatareference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent events = snapshot.getValue(NewEvent.class);
                eventList.add(events);
                createRecycler(eventList);
                progress.setVisibility(View.INVISIBLE);

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

        return view;
    }

    private void createRecycler(List<NewEvent> eventList) {
        verticalRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventAdapter = new EventAdapter(getActivity(),eventList);
        verticalRecView.setAdapter(eventAdapter);


    }

    private void initRecycler(List<NewGroupData> mItems) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, true));
        mAdapter = new GroupsAdapter(getActivity(), mItems);
        mRecyclerView.setAdapter(mAdapter);

    }

}

