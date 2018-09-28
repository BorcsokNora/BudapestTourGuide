package com.example.android.budapesttourguide;

//Custom class to provide objects that will hold all data for one programlist item

public class Program {
    private String mHead; //the head of the program title
    private String mTitle; //the title of the program
    private CharSequence mDescription; //the subtitle or description of the program
    private int mBrandIconId; // the resource ID of the icon of the recommended brand
    private String mVolunteerUrl; // the url link for apply as volunteer
    private int mProgramImageId;  // the resource ID of the image that illustrates the program
    private String mLocation; // the url link for apply as volunteer
    private String mTime; // the url link for apply as volunteer
    private String mPrice; // the price of the program
    private int mSongId; // the price of the program
    private boolean mIsSong; //differentiate the Singalong and the ForFree constructor


    //CONSTRUCTORS
    //Each fragment will have its own constructor based on the data the program type is having

    //Meaningful category constructor
    public Program(String title, String description, int brandIconId, String volunteerUrl) {
        mTitle = title;
        mDescription = description;
        mBrandIconId = brandIconId;
        mVolunteerUrl = volunteerUrl;
    }

    //Eat and Drink category constructor
    public Program(String head, String title, String description, int brandIconId, int programImageId, String location, String time) {
        mHead = head;
        mTitle = title;
        mDescription = description;
        mBrandIconId = brandIconId;
        mProgramImageId = programImageId;
        mLocation = location;
        mTime = time;
    }

    //Unrepeatable category constructor
    public Program(String title, String description, String location, String time, String price) {
        mTitle = title;
        mDescription = description;
        mLocation = location;
        mTime = time;
        mPrice = price;
    }

    //For free category constructor
    public Program(String title, String description, int programImageId) {
        mTitle = title;
        mDescription = description;
        mProgramImageId = programImageId;
        }

    //Singalong category constructor
    public Program(String artist, String songTitle, int songId, boolean isSong) {
        mTitle = artist;
        mDescription = songTitle;
        mSongId = songId;
        mIsSong = isSong;
    }


    //PUBLIC METHODS
    // these getter methods provide access to the data held by the program object
    public String getProgramHead() {
        return mHead;
}

    public String getProgramTitle() {
        return mTitle;
    }

    public CharSequence getDescription() {
        return mDescription;
    }

    public int getBrandIconId() {
        return mBrandIconId;
    }

    public String getVolunteerUrl() {
        return mVolunteerUrl;
    }

    public int getProgramImageId() {
        return mProgramImageId;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getTime() {
        return mTime;
    }

    public String getPrice() {
        return mPrice;
    }

    public int getSongId() {
        return mSongId;
    }

}
