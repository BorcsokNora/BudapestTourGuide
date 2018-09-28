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


//This fragment is about listing volunteering options
//The user is redirected to the program's own site by pressing the apply button

public class MeaningfulFragment extends Fragment {

    //CONSTRUCTOR
    public MeaningfulFragment() {
        // Required empty public constructor
    }

    //This method is called when the fragment is opened
    //It returns the inflated view defined by the adapter, and populated with data from the Programlist
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        TextView pageDescription = rootView.findViewById(R.id.page_description);
        pageDescription.setText(R.string.fragment_description_meaningful);

        //custom ArrayList of Program objects containing the data to be populated within the Singalong fragment
        //The arraylist contains:
        //resource ID of logo, program title, description, volounteer site's url
        final ArrayList<Program> meaningfulList = new ArrayList<>();
        meaningfulList.add(new Program(
                getString(R.string.meaningful_title_1),
                getString(R.string.meaningful_description_1),
                R.drawable.meaningful_bbmlogo,
                getString(R.string.meaningful_url_1)));
        meaningfulList.add(new Program(
                getString(R.string.meaningful_title_2),
                getString(R.string.meaningful_description_2),
                R.drawable.foodnotbombs_icon,
                getString(R.string.meaningful_url_2)));
        meaningfulList.add(new Program(
                getString(R.string.meaningful_title_3),
                getString(R.string.meaningful_description_3),
                R.drawable.mikulasgyar_icon,
                getString(R.string.meaningful_url_3)));


        //this is the recyclerView for showing the words
        RecyclerView recyclerView = rootView.findViewById(R.id.list_container);

        //set a LinearLayoutManager on the recycleView to get a vertically scrollable list
        recyclerView.setLayoutManager( new LinearLayoutManager (getActivity()));
                //layoutManager = LinearLayoutManager(getActivity().this);

        //construct adapter that will create views from the Program arraylist to the listview.
         MeaningfulAdapter adapter = new MeaningfulAdapter(meaningfulList);

        //connects the adapter and the recycling view
        recyclerView.setAdapter(adapter);

        // returns the inflated RecyclerView that is populated with the program list data
        return rootView;
    }

}
