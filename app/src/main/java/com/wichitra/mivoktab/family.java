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
 * Use the {@link family#newInstance} factory method to
 * create an instance of this fragment.
 */
public class family extends Fragment {
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

    public family() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment family.
     */
    // TODO: Rename and change types and number of parameters
    public static family newInstance(String param1, String param2) {
        family fragment = new family();
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
        View view=inflater.inflate(R.layout.words_list, container, false);
        final ArrayList<itemModel> words=new ArrayList<itemModel>();

        words.add(new itemModel("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new itemModel("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new itemModel("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new itemModel("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new itemModel("older brother", "taachi", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        words.add(new itemModel("younger brother", "chalitti", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        words.add(new itemModel("older sister", "teṭe", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        words.add(new itemModel("younger sister", "kolliti", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        words.add(new itemModel("grandmother ", "ama", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        words.add(new itemModel("grandfather", "paapa", R.drawable.family_grandfather,
                R.raw.family_grandfather));



        /*LinearLayoutCompat rootView=(LinearLayoutCompat) findViewById(R.id.rootView);
        for(int i=0;i< numlist.size();i++)
        {
            TextView numview= new TextView(this);
            numview.setText(numlist.get(i));
            rootView.addView(numview);
        }*/
        ListView list = (ListView)view.findViewById(R.id.list);
        itemModelAdapter adapter=new itemModelAdapter(getContext(),words,R.color.category_family);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemModel number=words.get(position);
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