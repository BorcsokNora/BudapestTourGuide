package com.example.android.budapesttourguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


//This is the opening activity of the app
//MainActivity contains custom fragments that have the further function of the app

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The ViewPager view provides swipeable pages that will be populated with fragments
        ViewPager viewPager = findViewById(R.id.viewpager);
        String[] tabTitles = getResources().getStringArray(R.array.tab_titles);


        //Custom adapter to connect the fragments to the viewPager
        TourGuideFragmentAdapter adapter = new TourGuideFragmentAdapter(getSupportFragmentManager(), tabTitles);
        //Connects adapter and paging view
        viewPager.setAdapter(adapter);

        //Set up the tab layout with the view pager
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

}
