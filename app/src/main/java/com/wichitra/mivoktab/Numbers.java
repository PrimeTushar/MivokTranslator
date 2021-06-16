package com.wichitra.mivoktab;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Numbers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Numbers extends Fragment {
    MediaPlayer mp;
    AudioManager am;
    MediaPlayer.OnCompletionListener mlistener=new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Numbers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Numbers.
     */
    // TODO: Rename and change types and number of parameters
    public static Numbers newInstance(String param1, String param2) {
        Numbers fragment = new Numbers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.words_list, container, false);

        am=(AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        //getContext().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<itemModel> numlist = new ArrayList<itemModel>();
        numlist.add(new itemModel("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numlist.add(new itemModel("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numlist.add(new itemModel("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numlist.add(new itemModel("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numlist.add(new itemModel("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numlist.add(new itemModel("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numlist.add(new itemModel("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numlist.add(new itemModel("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numlist.add(new itemModel("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        numlist.add(new itemModel("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));



        /*LinearLayoutCompat rootView=(LinearLayoutCompat) findViewById(R.id.rootView);
        for(int i=0;i< numlist.size();i++)
        {
            TextView numview= new TextView(this);
            numview.setText(numlist.get(i));
            rootView.addView(numview);
        }*/
        ListView list = (ListView) view.findViewById(R.id.list);
        itemModelAdapter adapter = new itemModelAdapter(getContext(), numlist, R.color.category_numbers);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemModel number = numlist.get(position);

                releaseMediaPlayer();


                mp = MediaPlayer.create(getContext(), number.getSongid());
                mp.start();
                mp.setOnCompletionListener(mlistener);
            }
        });
        return view;
    }
    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }
    }
}