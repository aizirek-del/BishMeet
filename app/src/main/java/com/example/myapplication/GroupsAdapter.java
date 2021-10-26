package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder> {
    private LayoutInflater inflater;

     Context context;
     private  List<NewGroupData> groupDataList ;
   // private ItemClickListener mClickListener;


    public GroupsAdapter(FragmentActivity activity, List<NewGroupData> groupDataList) {
        this.context = activity;
        this.groupDataList = groupDataList;
    }


    @NonNull
    @Override
    public GroupsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_layout,parent ,false);
        return new MyViewHolder(view) ;
    }


    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.MyViewHolder holder, int position) {

        NewGroupData gr = groupDataList.get(position);
        holder.txtView.setText(gr.getTitle());

        Picasso.get()
                .load(Uri.parse(gr.imageUri))
                .into(holder.imgView);

     holder.imgView.setOnClickListener(view ->{
      Log.e("GroupsAdapter"," OnClick Click on");


    Intent in = new Intent(context,Detailed_group.class);
    in.putExtra("title",gr.title);
      in.putExtra("image",gr.imageUri);
        in.putExtra("description",gr.decription);
        in.putExtra("interest",gr.interest);
        in.putExtra("category",gr.category);
        context.startActivity(in);


     });


  }
    @Override
    public int getItemCount() {

        return groupDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtView;
        CardView cardV;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            imgView = itemView.findViewById(R.id.ivOfClubList);
            txtView = itemView.findViewById(R.id.txtView);
        }
    }
}
