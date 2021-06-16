package com.wichitra.mivoktab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class itemModelAdapter extends ArrayAdapter<com.wichitra.mivoktab.itemModel> {

        int colid;

        public itemModelAdapter(@NonNull Context context, ArrayList<com.wichitra.mivoktab.itemModel> item, int colid){


            super(context,0, item);
            this.colid=colid;

        }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View Listview=convertView;
        //infalet the single item layout into soame empty view variable like here is the Listview
        if(Listview==null)
        {
            Listview= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }

        //get position for each view
        com.wichitra.mivoktab.itemModel item = getItem(position);
        //now update each subitem componant like textview,imageview which oudefined in listitem layout with value from objects incances of model class
        TextView mvk=(TextView)Listview.findViewById(R.id.mivok_txt);
        mvk.setText(item.getMivok());


        TextView eng=(TextView)Listview.findViewById(R.id.eng_txt);
        eng.setText(item.getEng());

        LinearLayoutCompat lin1=(LinearLayoutCompat)Listview.findViewById(R.id.lin1);
        int color= ContextCompat.getColor(getContext(),colid);
        lin1.setBackgroundColor(color);


        if(item.hasImage()) {
            ImageView img = (ImageView) Listview.findViewById(R.id.img1);
            img.setVisibility(View.VISIBLE);
            img.setImageResource(item.getResid());

        }
        else {
            ImageView img = (ImageView) Listview.findViewById(R.id.img1);
            img.setVisibility(View.GONE);
        }

        return Listview;

    }



}























