package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {
    private FloatingActionButton fab;
    DatabaseReference databaseReference;
    StorageReference mStorageRef;
    private ProgressBar progressBar;
    MenuItem iV;
    GroupsAdapter mAdapter;
    RecyclerView mRecyclerView;

    private final List<NewGroupData> mItems = new ArrayList<>();
    // vertical list of events
    RecyclerView verticalRecView;
    EventAdapter eventAdapter;
    DatabaseReference mDatareference;
    ProgressBar progress;
    private final List<NewEvent> eventList = new ArrayList<>();
    Button goButton, wholeList;
    private SearchView searchView;
    private SearchView.OnQueryTextListener queryTextListener;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBAr);
//        iV = view.findViewById(R.id.addNewGroup);
        mRecyclerView = view.findViewById(R.id.horizontal_recycler_view);

        progress = view.findViewById(R.id.in_progress);


        databaseReference = FirebaseDatabase.getInstance().getReference("groups");
//      mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewGroupData newData = snapshot.getValue(NewGroupData.class);
                mItems.add(newData);
                initGroupsRecycler(mItems);
                progressBar.setVisibility(View.GONE);
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
                progressBar.setVisibility(View.GONE);
            }

        });


//        iV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), Create_new_group.class);
//                getContext().startActivity(i);
//            }
//        });


        //Here begins the vertical RecyclerView of Events


        fab = view.findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), create_new_event.class);
                startActivity(i);

            }
        });

        verticalRecView = view.findViewById(R.id.vertical_recycler_view);
        mDatareference = FirebaseDatabase.getInstance().getReference("events");

        mDatareference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent events = snapshot.getValue(NewEvent.class);
                eventList.add(events);
                initEventRecycler(eventList);
                progress.setVisibility(View.GONE);
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
                progress.setVisibility(View.GONE);

            }
        });

        goButton = view.findViewById(R.id.Will_goBtn);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListByMe();
            }
        });
        wholeList = view.findViewById(R.id.wholeList);
        wholeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDateList();
            }
        });

        return view;

    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.search_view_menu).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        MenuItem searchMenuItem = menu.findItem(R.id.search_view_menu);
        queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2) {
                    updateList(query);
                } else {
                    Toast.makeText(getContext(), "Type more than two letters!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 2) {
                    updateList(newText);
                }

                return false;
            }
        };
        searchView.setQueryHint("Search Latest News...");
        searchView.setOnQueryTextListener(queryTextListener);


        searchMenuItem.getIcon().setVisible(false, false);


        iV = menu.findItem(R.id.create_new_group_menu);

        iV.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent i = new Intent(getActivity(), Create_new_group.class);
                requireActivity().startActivity(i);

                return false;
            }
        });

        MenuItem calendarItem = menu.findItem(R.id.calendar_menu);

        Calendar calendar = Calendar.getInstance();
        calendarItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String sDate = dayOfMonth + "." + month + "." + year;
                        showByCalendar(sDate);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_view_menu:

            case R.id.create_new_group_menu:
                Intent i = new Intent(getActivity(), Create_new_group.class);
                requireActivity().startActivity(i);

            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);


        return super.onOptionsItemSelected(item);
    }

    private void showByCalendar(String sDate) {
    }

    private void updateList(String query) {
        mDatareference.equalTo("title", query).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent events = snapshot.getValue(NewEvent.class);
                eventList.add(events);
                initEventRecycler(eventList);
                progress.setVisibility(View.GONE);
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
                progress.setVisibility(View.GONE);

            }
        });

    }


    //Groups recyclerview
    private void initGroupsRecycler(List<NewGroupData> list) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, true));
        mAdapter = new GroupsAdapter(getActivity(), list);
        mRecyclerView.setAdapter(mAdapter);

    }

    //events recyclerview
    private void initEventRecycler(List<NewEvent> eList) {
        verticalRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, true));
        eventAdapter = new EventAdapter(getActivity(), eList);
        verticalRecView.setAdapter(eventAdapter);
    }

    private void updateListByMe() {
        progress.setVisibility(View.VISIBLE);
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("events");
        eventList.clear();
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent events = snapshot.getValue(NewEvent.class);
                if (events.users.containsKey(firebaseUser.getUid())) {
                    eventList.add(events);
                }
                eventAdapter.eventDataList = eventList;
                eventAdapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);
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
                progress.setVisibility(View.GONE);

            }
        });
    }

    private void upDateList() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("events");
        eventList.clear();
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NewEvent events = snapshot.getValue(NewEvent.class);
                eventList.add(events);

                eventAdapter.eventDataList = eventList;
                eventAdapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);
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
                progress.setVisibility(View.GONE);
            }
        });
    }
}






