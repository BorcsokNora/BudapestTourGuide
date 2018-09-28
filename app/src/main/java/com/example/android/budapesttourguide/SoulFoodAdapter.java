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


public class SoulFoodAdapter extends RecyclerView.Adapter<SoulFoodAdapter.ProgramViewHolder> {
    private ArrayList<Program> programList;     // the list type that will be passed to the adapter
    private Boolean isViewExtended = false;     // this value indicates if all views of the program item is visible (extended) or not

    public static class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView programHead;
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

        ProgramViewHolder(View itemView) {
            super(itemView);
            programHead = itemView.findViewById(R.id.program_head);
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
            expandArrow = itemView.findViewById(R.id.expand_arrow);
        }
    }

    // CONSTRUCTOR
    SoulFoodAdapter(ArrayList<Program> programList) {
        this.programList = programList;
    }

    //Overriding the 3 abstract methods of RecyclerView.Adapter superclass
    @Override
    //This method returns the number of items present in the data list.
    public int getItemCount() {
        return programList.size();
    }

    //this method is called when the custom ViewHolder needs to be initialized.
    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // We specify the layout that the RecyclerView should use for each list item.
        // This is done by inflating the layout using LayoutInflater,
        // passing the output to the constructor of the custom ViewHolder.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.program_list_item, viewGroup, false);
        final ProgramViewHolder programViewHolder = new ProgramViewHolder(view);
        programViewHolder.programHead.setVisibility(View.VISIBLE);
        programViewHolder.description.setVisibility(View.GONE);
        programViewHolder.apply.setVisibility(View.GONE);
        programViewHolder.programImage.setVisibility(View.GONE);
        programViewHolder.location.setVisibility(View.GONE);
        programViewHolder.locationTitle.setVisibility(View.GONE);
        programViewHolder.time.setVisibility(View.GONE);
        programViewHolder.timeTitle.setVisibility(View.GONE);
        programViewHolder.price.setVisibility(View.GONE);
        programViewHolder.priceTitle.setVisibility(View.GONE);
        programViewHolder.icon.setMinimumWidth(48);
        isViewExtended = false;

        programViewHolder.programTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isViewExtended) {
                    programViewHolder.description.setVisibility(View.VISIBLE);
                    programViewHolder.programImage.setVisibility(View.VISIBLE);
                    programViewHolder.location.setVisibility(View.VISIBLE);
                    programViewHolder.locationTitle.setVisibility(View.VISIBLE);
                    programViewHolder.time.setVisibility(View.VISIBLE);
                    programViewHolder.timeTitle.setVisibility(View.VISIBLE);
                    programViewHolder.expandArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    isViewExtended = true;
                } else {
                    programViewHolder.description.setVisibility(View.GONE);
                    programViewHolder.programImage.setVisibility(View.GONE);
                    programViewHolder.location.setVisibility(View.GONE);
                    programViewHolder.locationTitle.setVisibility(View.GONE);
                    programViewHolder.time.setVisibility(View.GONE);
                    programViewHolder.timeTitle.setVisibility(View.GONE);
                    programViewHolder.expandArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    isViewExtended = false;
                }
            }
        });


        return programViewHolder;
    }


    //Override the onBindViewHolder to specify the contents of each item of the RecyclerView.
    // This method is very similar to the getView method of a ListView's adapter.
    // Here I set the values of the icon, title and description fields of the list item view.
    @Override
    public void onBindViewHolder(
            @NonNull final ProgramViewHolder programViewHolder,
            int i   //position of the item
    ) {
        //get the actual item's position with the superclass' get method
        Program programItem = programList.get(i);

        programViewHolder.programHead.setText(programItem.getProgramHead());
        programViewHolder.icon.setImageResource(programItem.getBrandIconId());
        programViewHolder.programTitle.setText(programItem.getProgramTitle());
        programViewHolder.description.setText(programItem.getDescription());
        programViewHolder.programImage.setImageResource(programItem.getProgramImageId());
        programViewHolder.location.setText(programItem.getLocation());
        programViewHolder.time.setText(programItem.getTime());
    }

    //Overriding the onAttachedToRecyclerView method.
    //We can simply use the superclass's implementation of this method.
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

