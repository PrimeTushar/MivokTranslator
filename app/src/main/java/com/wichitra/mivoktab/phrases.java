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
 * Use the {@link phrases#newInstance} factory method to
 * create an instance of this fragment.
 */
public class phrases extends Fragment {
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

    public phrases() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment phrases.
     */
    // TODO: Rename and change types and number of parameters
    public static phrases newInstance(String param1, String param2) {
        phrases fragment = new phrases();
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
        ArrayList<itemModel> phrases=new ArrayList<itemModel>();

        phrases.add(new itemModel("Where are you going?", "minto wuksus",
                R.raw.phrase_where_are_you_going));
        phrases.add(new itemModel("What is your name?", "tinnә oyaase'nә",
                R.raw.phrase_what_is_your_name));
        phrases.add(new itemModel("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrases.add(new itemModel("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new itemModel("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrases.add(new itemModel("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrases.add(new itemModel("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrases.add(new itemModel("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        phrases.add(new itemModel("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phrases.add(new itemModel("Come here.", "әnni'nem", R.raw.phrase_come_here));



        /*LinearLayoutCompat rootView=(LinearLayoutCompat) findViewById(R.id.rootView);
        for(int i=0;i< numlist.size();i++)
        {
            TextView numview= new TextView(this);
            numview.setText(numlist.get(i));
            rootView.addView(numview);
        }*/
        ListView list = (ListView) view.findViewById(R.id.list);
        itemModelAdapter adapter=new itemModelAdapter(getContext(),phrases,R.color.category_phrases);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemModel number=phrases.get(position);
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