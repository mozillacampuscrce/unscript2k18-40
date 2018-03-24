package com.example.somesh.music;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.somesh.myapplication_test.R;



/**
 * Created by Somesh on 1/20/2018.
 */

public class songsFragment extends Fragment implements View.OnClickListener {

    private View view1;
    private CardView option_11;
    private  String music;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.songs_fragment,container,false);
        option_11=(CardView) v.findViewById(R.id.option_11);
        option_11.setOnClickListener(this);

        return v;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Music Console");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.option_11:

                music="Bollywood";
                Intent i=new Intent(getActivity(),musicPlayer.class);
                i.putExtra("demand",music);
                startActivity(i);
                break;

    }
  }
}
