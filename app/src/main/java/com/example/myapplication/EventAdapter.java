package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

 public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    Context context;
    public List<NewEvent> eventDataList ;

    public EventAdapter(FragmentActivity activity, List<NewEvent> eventDataList) {
        this.context = activity;
        this.eventDataList = eventDataList;
    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_list_layout,parent ,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        NewEvent event = eventDataList.get(position);
//        holder.cards_tv.setText(event.groupData.title);
        holder.tv_descrip.setText(event.eventDescription);

       holder.tv_participants.setText(event.users.size() +" участников");

        Picasso.get()
                .load(event.event_image)
                .into(holder.event_imgView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(context,Event_detailed.class);

//                in.putExtra("eventTitle",event.eventTitle);
//                 in.putExtra("event_image",event.event_image);
//                in.putExtra("eventDescription",event.eventDescription);
//                in.putExtra("event_date",event.Event_date);
//                in.putExtra("eventLocation",event.eventLocation);
                in.putExtra("event_id", event.Event_id);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {

        return eventDataList.size();
    }
}
class EventViewHolder extends RecyclerView.ViewHolder{

    ImageView event_imgView;
    ImageView partic_img;
    TextView cards_tv,tv_descrip,tv_participants;
    CardView cardView;

    public EventViewHolder(@NonNull View itemView){
        super(itemView);

        event_imgView = itemView.findViewById(R.id.events_image_view);
        partic_img = itemView.findViewById(R.id.participants_foto);
        cards_tv = itemView.findViewById(R.id.cards_tv);
        tv_descrip = itemView.findViewById(R.id.cards_text_title);
        tv_participants = itemView.findViewById(R.id.membersNum);
        cardView = itemView.findViewById(R.id.card_event);
    }

}
