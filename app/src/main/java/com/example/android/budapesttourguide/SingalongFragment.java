package com.example.android.budapesttourguide;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;


//This fragment is about listening to popular songs
//The songs are part of the application - saved to the raw folder
public class SingalongFragment extends Fragment {

    MediaPlayer mediaPlayer;        //global MediaPlayer object
    static boolean isSongPaused = false;   //True, if the song is paused. Used as criteria for resume playing after pause
    int currentSongId = 0;          //Has the value of the song resource ID. Used as criteria for resume playing after pause
    private int playbackLength = 0; //The current position of the playback while playing the song (expressed in milliseconds). Used as reference to resume after pause.
    int focusRequest = 0;           //Global variable for storing the actual value of focus request. Used as a criteria for starting playback
    AudioManager audioManager;      //global AudioManager object
    int currentVolume;              //The current volume of the playback. Used as a reference when resuming playback after pause.

    //OnAudioFocusChangeListener
    //This listener lists the cases of audio focus change
    //and defines the behavior of the MediaPlayer for each case.
    AudioManager.OnAudioFocusChangeListener audioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //When audio focus is lost permanently
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
                abandonAF();

                //When audio focus is lost temporarily
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
                playbackLength = mediaPlayer.getCurrentPosition();
                isSongPaused = true;

                //When audio focus is lost temporarily, but the playback can go on on a reduced sound volume
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //save the current volume to set it back later when ducking is over
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                //lower volume to 2 when ducking
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 2, 0);

                //When audio focus is regained after temporary loss
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                if (isSongPaused) {
                    mediaPlayer.seekTo(playbackLength);
                    mediaPlayer.start();
                    isSongPaused = false;
                } else {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
                }
            }
        }
    };

    //OnItemClickListener
    //This listener calls back when a list item is tapped
    //The user taps the item to start the playback of the song
    //The listener is passed to the custom RecyclerView adapter as an input parameter
    private SingalongAdapter.OnItemClickListener clickListener = new SingalongAdapter.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onItemClick(Program programListItem, ImageView playPauseIcon) {

            //gets audio manager from the system to request audio focus for the playback
            getContext();
            audioManager = (AudioManager) Objects.requireNonNull(getActivity()).getSystemService(Context.AUDIO_SERVICE);
            //check if audio focus is already granted or not
            //this helps to avoid surplus focus request when the user is active within the app

            if (audioManager != null) {
                //If audio focus is not granted
                if (focusRequest != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //Request Audio Focus
                    focusRequest = audioManager.requestAudioFocus(
                            audioFocusListener,
                            AudioManager.STREAM_MUSIC,
                            AudioManager.AUDIOFOCUS_GAIN);
                }
                //If audio focus is granted
                if (focusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //Check if the user is tapping the same item by checking the song resource item
                    if (programListItem.getSongId() == currentSongId) {
                        //Check if the song is playing - then pause it
                        if (!isSongPaused) {
                            mediaPlayer.pause();
                            playPauseIcon.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                            playbackLength = mediaPlayer.getCurrentPosition();
                            isSongPaused = true;
                        }
                        //For resuming play after pause
                        else {
                            mediaPlayer.seekTo(playbackLength);
                            mediaPlayer.start();
                            playPauseIcon.setImageResource(R.drawable.ic_pause_black_36dp);
                            isSongPaused = false;
                        }

                        //If the user clicked on another item
                    } else {
                        //release MediaPlayer if it contains anything
                        if (mediaPlayer != null) {
                            playPauseIcon.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                            releaseMediaPlayer();
                        }
                        //create a media player containing the sound file of the current programList item
                        mediaPlayer = MediaPlayer.create(getContext(), programListItem.getSongId());
                        //start playback
                        mediaPlayer.start();
                        playPauseIcon.setImageResource(R.drawable.ic_pause_black_36dp);
                        //save the song ID for later reference to decide if the user clicked on the same item again
                        currentSongId = programListItem.getSongId();
                        isSongPaused = false;
                        //set OnCompletionListener on the media player, so when the playback is finished
                        // it releases the mediaPlayer and sets its value to null
                        // it sets the current song ID to zero
                        // it abandons audio focus
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                releaseMediaPlayer();
                                abandonAF();
                            }
                        });
                    }
                }
            }
        }
    };


    //CONSTRUCTOR OF THE FRAGMENT
    public SingalongFragment() {
        // Required empty public constructor
    }

    //This method is called when the fragment is opened
    //It returns the inflated view defined by the adapter, and populated with data from the Programlist
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(
                //input parameters
                R.layout.fragment_layout,
                container,
                false);

        TextView pageDescription = rootView.findViewById(R.id.page_description);
        pageDescription.setText(R.string.fragment_description_singalong);

        //custom ArrayList of Program objects containing the data to be populated within the Singalong fragment
        //The arraylist contains:
        //artist name, song title, resource ID of the song,
        // plus a boolean to differentiate the constructor from the constructor of ForFree program type
        final ArrayList<Program> singalongList = new ArrayList<>();
        singalongList.add(new Program(
                getString(R.string.singalong_artist_1),
                getString(R.string.singalong_song_title_1),
                R.raw.kispal, true));
        singalongList.add(new Program(
                getString(R.string.singalong_artist_2),
                getString(R.string.singalong_song_title_2),
                R.raw.quimby, true));
        singalongList.add(new Program(
                getString(R.string.singalong_artist_3),
                getString(R.string.singalong_song_title_3),
                R.raw.anna, true));
        singalongList.add(new Program(
                getString(R.string.singalong_artist_4),
                getString(R.string.singalong_song_title_4),
                R.raw.szabo, true));

        //this is the recyclerView for showing the list items
        RecyclerView recyclerView = rootView.findViewById(R.id.list_container);

        //set a LinearLayoutManager on the recycleView to get a vertically scrollable list
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //construct custom adapter that will create views from the Program arraylist to the listview.
        SingalongAdapter adapter = new SingalongAdapter(singalongList, clickListener);

        //connects the adapter and the recycling view
        recyclerView.setAdapter(adapter);

        // returns the inflated RecyclerView that is populated with the program list data
        return rootView;
    }

    //When the fragment gets in the Destroyed lifecycle state,
    // it releases MediaPlayer and abandons audio focus
    //Before this state the playback continues even if the app is in the background, until the audiofocus is lost or the user stops the playback
    @Override
    public void onDestroy() {
        releaseMediaPlayer();
        abandonAF();
        super.onDestroy();
    }

    //This method cleans up the MediaPlayer by releasing its resources
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources because we no longer need it.
            mediaPlayer.stop();
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
        //sets the value of the current song resource ID to zero, as a reference for our code that there is no current song playing.
        currentSongId = 0;
    }

    //This method gives back audio focus to the system (or cancels any current focus request)
    private void abandonAF() {
        if (audioManager != null) {
            audioManager.abandonAudioFocus(audioFocusListener);
        }
    }

}