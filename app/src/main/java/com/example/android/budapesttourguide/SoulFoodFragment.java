package com.example.android.budapesttourguide;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


//This fragment is about listing special cafés and bars with an added value
public class SoulFoodFragment extends Fragment {

    public SoulFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        // Inflate the layout for this fragment

        TextView pageDescription = rootView.findViewById(R.id.page_description);
        pageDescription.setText(R.string.fragment_description_soulfood);

        final ArrayList<Program> eatAndDrinkList = new ArrayList<>();
        eatAndDrinkList.add(new Program(
                getString(R.string.eat_drink_head_1),
                getString(R.string.eat_drink_title_1),
                getString(R.string.eat_drink_description_1),
                R.drawable.aurora_icon,
                R.drawable.aurora,
                getString(R.string.eat_drink_location_1),
                getString(R.string.eat_drink_time_1)));
        eatAndDrinkList.add(new Program(
                getString(R.string.eat_drink_head_2),
                getString(R.string.eat_drink_title_2),
                getString(R.string.eat_drink_description_2),
                R.drawable.golya_icon,
                R.drawable.golya,
                getString(R.string.eat_drink_location_2),
                getString(R.string.eat_drink_time_2)));
        eatAndDrinkList.add(new Program(
                getString(R.string.eat_drink_head_3),
                getString(R.string.eat_drink_title_3),
                getString(R.string.eat_drink_description_3),
                R.drawable.placeholder,
                R.drawable.vittula,
                getString(R.string.eat_drink_location_3),
                getString(R.string.eat_drink_time_3)));
        eatAndDrinkList.add(new Program(
                getString(R.string.eat_drink_head_4),
                getString(R.string.eat_drink_title_4),
                getString(R.string.eat_drink_description_4),
                R.drawable.cserpes_icon,
                R.drawable.cserpes,
                getString(R.string.eat_drink_location_4),
                getString(R.string.eat_drink_time_4)));


        //this is the recyclerView for showing the list of program items
        RecyclerView recyclerView = rootView.findViewById(R.id.list_container);

        //set a LinearLayoutManager on the recycleView to get a vertically scrollable list
        recyclerView.setLayoutManager( new LinearLayoutManager (getActivity()));

        //construct adapter that will create views from the Program arraylist to the listview.
         SoulFoodAdapter adapter = new SoulFoodAdapter(eatAndDrinkList);

        //connects the adapter and the recycling view
        recyclerView.setAdapter(adapter);


        return rootView;
    }

}
