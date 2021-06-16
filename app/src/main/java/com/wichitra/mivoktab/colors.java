package com.wichitra.mivoktab;

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
 * Use the {@link colors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class colors extends Fragment {
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

    public colors() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment colors.
     */
    // TODO: Rename and change types and number of parameters
    public static colors newInstance(String param1, String param2) {
        colors fragment = new colors();
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
        View view= inflater.inflate(R.layout.words_list, container, false);

        ArrayList<itemModel> collist=new ArrayList<itemModel>();
        collist.add(new itemModel("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        collist.add(new itemModel("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));
        collist.add(new itemModel("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        collist.add(new itemModel("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        collist.add(new itemModel("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        collist.add(new itemModel("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        collist.add(new itemModel("black", "kululli", R.drawable.color_black, R.raw.color_black));
        collist.add(new itemModel("white", "kelelli", R.drawable.color_white, R.raw.color_white));




        /*LinearLayoutCompat rootView=(LinearLayoutCompat) findViewById(R.id.rootView);
        for(int i=0;i< numlist.size();i++)
        {
            TextView numview= new TextView(this);
            numview.setText(numlist.get(i));
            rootView.addView(numview);
        }*/
        ListView list = (ListView) view.findViewById(R.id.list);
        itemModelAdapter adapter=new itemModelAdapter(getContext(),collist,R.color.category_colors);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemModel number=collist.get(position);
                mp= MediaPlayer.create(getContext(),number.getSongid());
                mp.start();
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