package com.example.somesh.myapplication_test;

import android.annotation.SuppressLint;
import android.content.Intent
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.somesh.Login.Login;
import com.example.somesh.Login.sessionMaker;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager ;
    private LinearLayout mlinear;
    private sessionMaker session;
    private TextView [] mDots;
    private SLiderAdapter sLiderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session=new sessionMaker(getApplicationContext());
        if(session.checkLogin()==1)
        {
            startActivity(new Intent(this,Home.class));
            finish();
        }
        mSlideViewPager =(ViewPager) findViewById(R.id.sideview);
        mlinear=(LinearLayout)findViewById(R.id.dotsandbuttonview);

        sLiderAdapter=new SLiderAdapter( this);
        mSlideViewPager.setAdapter(sLiderAdapter);
        addDotIndicator(0);
        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();

        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    @SuppressLint("NewApi")
    public void addDotIndicator(int position){

        mDots=new TextView[3];
        mlinear.removeAllViews();
        for(int i=0; i<mDots.length; i++){

            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mlinear.addView(mDots[i]);
        }
        if(mDots.length>0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorwhite));
        }

    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void Login(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }


}
