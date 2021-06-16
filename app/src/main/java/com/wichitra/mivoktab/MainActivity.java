package com.wichitra.mivoktab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;
    TabItem t1,t2,t3;
    PageAdapter adapter;
    ViewPager vpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs=(TabLayout) findViewById(R.id.tb);
        t1=(TabItem)findViewById(R.id.tab1);
        t2=(TabItem)findViewById(R.id.tab2);
        t3=(TabItem)findViewById(R.id.tab3);
        vpager=(ViewPager)findViewById(R.id.vpager);

        adapter=new PageAdapter(getSupportFragmentManager(),tabs.getTabCount());
        vpager.setAdapter(adapter);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2)
                {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


    }
}