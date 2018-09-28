package com.example.android.budapesttourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//This is the custom adapter for Meaningful Fragment
//This adapter helps to find the proper views and populate the proper data
//The adapter has a custom ViewHolder class
public class MeaningfulAdapter extends RecyclerView.Adapter<MeaningfulAdapter.ProgramViewHolder> {

    private ArrayList<Program> programList;      // the list type that has to be passed to the adapter
    private Boolean isViewExtended = false;  // this value indicates if all views of the program item is visible (extended) or not

    //This is a custom RecyclerView.ViewHolder class
    //It defines all the views from the layout that will be used
    //only one layout is used for every fragment, so all the views are listed here.
    //This ViewHolder will have all the views that can be used to be populated by the data
    public static class ProgramViewHolder extends RecyclerView.ViewHolder {
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
        ImageView expandArrow;

        //This method connects the view variables to the views from the layout by their IDs
        ProgramViewHolder(View itemView) {
            super(itemView);

            icon =  itemView.findViewById(R.id.program_icon);
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
            expandArrow = itemView.findViewById(R.id.expand_arrow);
        }
    }

    // CONSTRUCTOR
    MeaningfulAdapter(ArrayList<Program> programList) {
        this.programList = programList;
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
        //These views are not needed to be shown when the fragment opens, so we hide them.
        programViewHolder.description.setVisibility(View.GONE);
        programViewHolder.apply.setVisibility(View.GONE);
        programViewHolder.programImage.setVisibility(View.GONE);
        programViewHolder.location.setVisibility(View.GONE);
        programViewHolder.locationTitle.setVisibility(View.GONE);
        programViewHolder.time.setVisibility(View.GONE);
        programViewHolder.timeTitle.setVisibility(View.GONE);
        programViewHolder.price.setVisibility(View.GONE);
        programViewHolder.priceTitle.setVisibility(View.GONE);

        //Indicate that the view is not extended currently
        isViewExtended = false;

        //Set a listener to the program title view
        programViewHolder.programTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the view is not extended it extends (makes visible further views) when the user clicks on it
                if (!isViewExtended) {
                    programViewHolder.description.setVisibility(View.VISIBLE); //set description visible
                    programViewHolder.apply.setVisibility(View.VISIBLE);    //set apply button visible
                    programViewHolder.expandArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    isViewExtended = true;      //set the boolean's value true that indicates if the view is "extended"

                    //If the view is extended it closes when the user clicks on it
                } else {
                    programViewHolder.description.setVisibility(View.GONE); //hides description
                    programViewHolder.apply.setVisibility(View.GONE);   //hides apply button
                    programViewHolder.expandArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    isViewExtended = false; //set the boolean's value false that indicates if the view is "extended"
                }
            }
        });

        //The class returns a custom ViewHolder that has a custom layout and views that can be populated with the data.
        return programViewHolder;
    }


    //Override the onBindViewHolder to specify the contents of each item of the RecyclerView.
    // This method is very similar to the getView method of a ListView's adapter.
    // Here I set the values of the icon, title and description fields of the list item view.
    @Override
    public void onBindViewHolder(
            @NonNull final ProgramViewHolder programViewHolder,  // the custom ViewHolder whose views will contain the data
            int i   //position of the item
    ) {
        //get the actual item's position with the superclass' get method
        Program programItem = programList.get(i);
        //and connect the data source to the right view variable
        programViewHolder.icon.setImageResource(programItem.getBrandIconId());
        programViewHolder.programTitle.setText(programItem.getProgramTitle());
        programViewHolder.description.setText(programItem.getDescription());

        //stores the volunteer page url's value in a final variable to be able to pass to the intent
        final String url = programItem.getVolunteerUrl();

        //sets listener on the apply button to send a web intent when the button is clicked
        programViewHolder.apply.setOnClickListener
                (new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         //Creates an intent to opening a website (ACTION_VIEW)
                         Intent intent = new Intent(Intent.ACTION_VIEW);
                         //Set pass url string of the site that has to be opened
                         intent.setData(Uri.parse(url));
                         //Make sure that there is an app that can perform the task
                         if (intent.resolveActivity(programViewHolder.container.getContext().getPackageManager()) != null) {
                             //send the intent and start the activity
                             programViewHolder.container.getContext().startActivity(intent);
                         }
                     }
                 }
                );
    }

    //Overriding the onAttachedToRecyclerView method.
    //We can simply use the superclass's implementation of this method.
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

