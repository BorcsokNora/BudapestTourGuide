package com.example.android.budapesttourguide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//This is the custom adapter for Singalong Fragment
//This adapter helps to find the proper views and populate the proper data
//The adapter has a custom ViewHolder class
public class SingalongAdapter extends RecyclerView.Adapter<SingalongAdapter.ProgramViewHolder> {


    private ArrayList<Program> programList;  // the list type that has to be passed to the adapter

    //the custom listener that we added as an interface to the adapter,
    //This is an input parameter for the constructor, and has to be implemented by the fragment
    private OnItemClickListener listener;

    //abstract custom OnItemClickListener that has to be implemented when the adapter is constructed
    //the method is implemented within Singalong Fragment.
    public interface OnItemClickListener {
        //the input parameter has to be one list item that will be clicked by the user
        void onItemClick(Program programListItem, ImageView playPauseIcon);
    }

    //This is a custom RecyclerView.ViewHolder class
    //It defines all the views from the layout that will be used
    //only one layout is used for every fragment, so all the views are listed here.
    //This ViewHolder will have all the views that can be used to be populated by the data
    static class ProgramViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView programTitle;
        TextView description;
        Button apply;
        ViewGroup container;
        ImageView programImage;
        TextView location;
        TextView locationTitle;
        TextView time;
        TextView timeTitle;
        TextView price;
        TextView priceTitle;
        ImageView playPauseIcon;

        //This method connects the view variables to the views from the layout by their IDs
        ProgramViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.program_icon);
            programTitle = itemView.findViewById(R.id.program_title);
            description = itemView.findViewById(R.id.program_description);
            apply = itemView.findViewById(R.id.meaningful_apply_button);
            container = itemView.findViewById(R.id.extending_layout);
            programImage = itemView.findViewById(R.id.program_image);
            location = itemView.findViewById(R.id.program_location);
            locationTitle = itemView.findViewById(R.id.program_location_title);
            time = itemView.findViewById(R.id.program_time);
            timeTitle = itemView.findViewById(R.id.program_time_title);
            price = itemView.findViewById(R.id.program_price);
            priceTitle = itemView.findViewById(R.id.program_price_title);
            playPauseIcon = itemView.findViewById(R.id.expand_arrow);
            playPauseIcon.setImageResource(R.drawable.ic_play_arrow_black_36dp);
        }
    }

    // CONSTRUCTOR
    //the arguments of the custom adapter are:
    // a Program object, which is a custom ArrayList
    //and a custom OnItemClickListener - which has to be implemented by the fragment
    SingalongAdapter(ArrayList<Program> programList, OnItemClickListener listener) {
        this.programList = programList;
        this.listener = listener;
    }

    //Overriding the 3 abstract methods of RecyclerView.Adapter superclass

    //This method returns the number of items present in the data list.
    @Override
    public int getItemCount() {
        return programList.size();
    }

    //this method is called when the custom ViewHolder needs to be initialized.
    //the viewHolder creates a custom layout that can be populated with the data
    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // We specify the layout that each item of the RecyclerView should use.
        // This is done by inflating the layout using LayoutInflater,
        // passing the output to the constructor of the custom ViewHolder.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.program_list_item, viewGroup, false);
        //This is the custom ViewHolder object
        final ProgramViewHolder programViewHolder = new ProgramViewHolder(view);

        //We can customize all the views that the ViewHolder contains
        //These views are not needed in this fragment, so we hide them.
        programViewHolder.icon.setVisibility(View.GONE);
        programViewHolder.apply.setVisibility(View.GONE);
        programViewHolder.programImage.setVisibility(View.GONE);
        programViewHolder.location.setVisibility(View.GONE);
        programViewHolder.locationTitle.setVisibility(View.GONE);
        programViewHolder.time.setVisibility(View.GONE);
        programViewHolder.timeTitle.setVisibility(View.GONE);
        programViewHolder.price.setVisibility(View.GONE);
        programViewHolder.priceTitle.setVisibility(View.GONE);

        programViewHolder.description.setTextSize(14);
        //This setting reduces the distance between the description and the title view
        //The zero values mean they don't change (add or deduct) the spacing
        programViewHolder.description.setPadding(0, -6, 0, 0);

        //The class returns a custom ViewHolder that has a custom layout and views that can be populated with the data.
        return programViewHolder;
    }


    //Override the onBindViewHolder to specify the content of each item of the RecyclerView.
    // This method is very similar to the getView method of a ListView's adapter.
    // Here I set the values of the icon, title and description fields of the list item view.
    @Override
    public void onBindViewHolder(
            @NonNull final ProgramViewHolder programViewHolder, // the custom ViewHolder whose views will contain the data
            int i   //position of the item
    ) {
        //get the actual item's position with the superclass' get method
        final Program programItem = programList.get(i);
        //and connect the data source to the right view variable
        programViewHolder.programTitle.setText(programItem.getProgramTitle());
        programViewHolder.description.setText(programItem.getDescription());
        programViewHolder.programImage.setImageResource(programItem.getProgramImageId());

        //sets listener on the program list item that will be implemented when the adapter is called
        programViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(programItem, programViewHolder.playPauseIcon );
            }
        });
    }

    //Overriding the onAttachedToRecyclerView method.
    //We can simply use the superclass's implementation of this method.
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

