package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    Context context;
    private List<NewEvent> eventDataList ;

    public EventAdapter(List<NewEvent> eventDataList) {
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
        holder.cards_tv.setText(event.groups);
        holder.tv_descrip.setText(event.eventDescription);
        holder.tv_participants.setText(event.users.size() +" участников");

//        Picasso.get()
//                .load(Uri.parse(event.EventImage))
//                .into(holder.event_imgView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                    }
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });
//        Picasso.get().load(Uri.parse(event.userImg)).
//                into(holder.partic_img, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in = new Intent(context,Detailed_event.class);
//                in.putExtra("title",gr.title);
//                //  in.putExtra("image",gr.imageUri);
//                in.putExtra("description",gr.decription);
//                in.putExtra("interest",gr.interest);
//                in.putExtra("category",gr.category);
//                context.startActivity(in);
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

        event_imgView = itemView.findViewById(R.id.cards_image_view);
        partic_img = itemView.findViewById(R.id.shapeableFoto);
        cards_tv = itemView.findViewById(R.id.cards_tv);
        tv_descrip = itemView.findViewById(R.id.cards_text_title);
        tv_participants = itemView.findViewById(R.id.membersNum);
        cardView = itemView.findViewById(R.id.card_event);

    }

}
