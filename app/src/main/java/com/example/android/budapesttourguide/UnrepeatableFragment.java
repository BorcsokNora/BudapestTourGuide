package com.example.android.budapesttourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//This fragment is about listing various art programs based on improvisation
public class UnrepeatableFragment extends Fragment {


    public UnrepeatableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        // Inflate the layout for this fragment

        TextView pageDescription = rootView.findViewById(R.id.page_description);
        pageDescription.setText(R.string.fragment_description_unrepeatable);

        final ArrayList<Program> unrepeatableList = new ArrayList<Program>();
        unrepeatableList.add(new Program(
                getString(R.string.unrepeatable_title_1),
                getString(R.string.unrepeatable_description_1),
                getString(R.string.unrepeatable_location_1),
                getString(R.string.unrepeatable_time_1),
                getString(R.string.unrepeatable_price_1)));
        unrepeatableList.add(new Program(
                getString(R.string.unrepeatable_title_2),
                getString(R.string.unrepeatable_description_2),
                getString(R.string.unrepeatable_location_2),
                getString(R.string.unrepeatable_time_2),
                getString(R.string.unrepeatable_price_2)));
        unrepeatableList.add(new Program(
                getString(R.string.unrepeatable_title_3),
                getString(R.string.unrepeatable_description_3),
                getString(R.string.unrepeatable_location_3),
                getString(R.string.unrepeatable_time_3),
                getString(R.string.unrepeatable_price_3)));
        unrepeatableList.add(new Program(
                getString(R.string.unrepeatable_title_4),
                getString(R.string.unrepeatable_description_4),
                getString(R.string.unrepeatable_location_4),
                getString(R.string.unrepeatable_time_4),
                getString(R.string.unrepeatable_price_4)));


        //this is the recyclerView for showing the words
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list_container);

        //set a LinearLayoutManager on the recycleView to get a vertically scrollable list
        recyclerView.setLayoutManager( new LinearLayoutManager (getActivity()));

        //construct adapter that will create views from the Program arraylist to the listview.
         UnrepeatableAdapter adapter = new UnrepeatableAdapter(unrepeatableList);

        //connects the adapter and the recycling view
        recyclerView.setAdapter(adapter);


        return rootView;
    }

}
