package com.example.android.budapesttourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


//Adapter that will connect the fragments to the main activity
public class TourGuideFragmentAdapter extends FragmentPagerAdapter {
    //The titles of the pages that will be shown on the tab
    private String pageTitle[];

    TourGuideFragmentAdapter(FragmentManager fm, String[] pageNames) {
        super(fm);  //constructor of super class which will take the fm FragmentManager input
        pageTitle = pageNames;
    }

    //overriding abstract method of FragmentPagerAdapter class
    //returns the fragment of the given position
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            //object of custom SoulFoodFragment class which extends Fragment class
            return new SoulFoodFragment();
        } else if (position == 1) {
            //object of custom MeaningfulFragment class which extends Fragment class
            return new MeaningfulFragment();
        } else if (position == 2) {
            //object of custom UnrepeatableFragment class which extends Fragment class
            return new UnrepeatableFragment();
        } else if (position == 3) {
            //object of custom ForFreeFragment class which extends Fragment class
            return new ForFreeFragment();
        } else {
            //object of custom SingalongFragment class which extends Fragment class
            return new SingalongFragment();
        }
    }

    // returns the overall amount of pages (fragments)
    @Override
    public int getCount() { //Returns the number of views available. (abstract method of PagerAdapter class)
        return 5;
    }

    // This method provides the current title of the page for the tab view
    @Override
    public CharSequence getPageTitle(int position) {
        //returns the title of the page of the given position
        return pageTitle[position];
    }
}