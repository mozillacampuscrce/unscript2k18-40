package com.example.somesh.music;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.somesh.myapplication_test.Home;
import com.example.somesh.myapplication_test.R;

public class music_home extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_home);
        tabLayout=(TabLayout)findViewById(R.id.tab_1);
        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        appBarLayout=(AppBarLayout)findViewById(R.id.appbar_1);
        pager=(ViewPager)findViewById(R.id.pager11);
        pager.setAdapter(new viewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ii);
        tabLayout.getTabAt(1).setIcon(R.drawable.ii);
        tabLayout.getTabAt(0).setText("Bollywood Songs");
        tabLayout.getTabAt(1).setText("Hollywood Songs");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}