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

//This fragment is about listing free program options
public class ForFreeFragment extends Fragment {


    public ForFreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        // Inflate the layout for this fragment

        TextView pageDescription = rootView.findViewById(R.id.page_description);
        pageDescription.setText(R.string.fragment_description_for_free);
        final ArrayList<Program> forFreeList = new ArrayList<>();
        forFreeList.add(new Program(
                getString(R.string.forfree_title_1),
                getString(R.string.forfree_description_1),
                R.drawable.szabihid));
        forFreeList.add(new Program(
                getString(R.string.forfree_title_2),
                getString(R.string.forfree_description_2),
                R.drawable.duna));
        forFreeList.add(new Program(
                getString(R.string.forfree_title_3),
                getString(R.string.forfree_description_3),
                R.drawable.varosliget));
        forFreeList.add(new Program(
                getString(R.string.forfree_title_4),
                getString(R.string.forfree_description_4),
                R.drawable.margitsziget));
        forFreeList.add(new Program(
                getString(R.string.forfree_title_5),
                getString(R.string.forfree_description_5),
                R.drawable.muzeumkert));


        //this is the recyclerView for showing the words
        RecyclerView recyclerView = rootView.findViewById(R.id.list_container);

        //set a LinearLayoutManager on the recycleView to get a vertically scrollable list
        recyclerView.setLayoutManager( new LinearLayoutManager (getActivity()));

        //construct adapter that will create views from the Program arraylist to the listview.
         ForFreeAdapter adapter = new ForFreeAdapter(forFreeList);

        //connects the adapter and the recycling view
        recyclerView.setAdapter(adapter);


        return rootView;
    }

}
