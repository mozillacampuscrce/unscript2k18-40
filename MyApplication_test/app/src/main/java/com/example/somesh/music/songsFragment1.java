package com.example.somesh.music;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.somesh.myapplication_test.R;

/**
 * Created by Somesh on 1/20/2018.
 */

public class songsFragment1 extends Fragment implements View.OnClickListener {
    View view;
    private CardView option_112;
    private  String music;
    public songsFragment1(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.songs_fragment1,container,false);
        option_112=(CardView) view.findViewById(R.id.option_112);
        option_112.setOnClickListener(this);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Music Console");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.option_112:

                music="selena gomez";
                Intent i=new Intent(getActivity(),musicPlayer.class);
                i.putExtra("demand",music);
                startActivity(i);
                break;

        }
    }
}
