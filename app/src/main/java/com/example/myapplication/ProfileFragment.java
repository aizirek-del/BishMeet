package com.example.myapplication;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


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
        progressBar = view.findViewById(R.id.prog);

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
                Picasso.get().load(Uri.parse(user.imgUri)).into(myProfile_Foto);
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

