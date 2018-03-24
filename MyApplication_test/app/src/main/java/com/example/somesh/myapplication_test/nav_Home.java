package com.example.somesh.myapplication_test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.somesh.music.music_home;
import com.example.somesh.news.listview;
import com.example.somesh.tweet.twitter_home;
import com.example.somesh.weather.weather_Start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Somesh on 12/28/2017.
 */


public class nav_Home extends Fragment implements View.OnClickListener {

    ProgressDialog progressDialog;
    String json;
    String input_json;
    private class BackgroundTask extends AsyncTask<Void,Void,String>{

        String myurl;
        String key="15868380de3b453cb469ccb675f150ae";
        @Override
        protected void onPreExecute() {
            progressDialog=ProgressDialog.show(getActivity(),"Please Wait....","Processing request..",true);
            myurl="http://api.nytimes.com/svc/news/v3/content/nyt/all.json?&limit=25&api-key="+key;
        }

        @Override
        protected String doInBackground(Void... voids) {
            URL NYT_Url;
            try {
                NYT_Url = new URL(myurl);
                BufferedReader br=new BufferedReader(new InputStreamReader(NYT_Url.openStream()));
                input_json = br.readLine();
                Log.d("MyApp","I am here "+input_json);
                return input_json;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
                      if(progressDialog.isShowing()){
                          progressDialog.dismiss();
                          json=s;
                          Intent i2=new Intent(getActivity(),listview.class);
                          i2.putExtra("json_data",input_json)  ;
                          startActivity(i2);
                      }
        }
    }

    private CardView option_1,option_2,option_3,option_4,option_5,option_6,option_7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.nav_home,container,false);
        option_1=(CardView) v.findViewById(R.id.option_1);
        option_1.setOnClickListener(this);
        option_2=(CardView) v.findViewById(R.id.option_2);
        option_2.setOnClickListener(this);
        option_3=(CardView) v.findViewById(R.id.option_3);
        option_3.setOnClickListener(this);
        option_4=(CardView) v.findViewById(R.id.option_4);
        option_4.setOnClickListener(this);
        option_5=(CardView) v.findViewById(R.id.option_5);
        option_5.setOnClickListener(this);
        option_6=(CardView) v.findViewById(R.id.option_6);
        option_6.setOnClickListener(this);
        option_7=(CardView) v.findViewById(R.id.option_7);
        option_7.setOnClickListener(this);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home Console");
    }

    @Override
    public void onClick(View v) {
       // Intent i;
        switch (v.getId()){
            case R.id.option_1:
                startActivity(new Intent(getActivity(),weather_Start.class));
                break;

            case R.id.option_2:
                BackgroundTask bg=new BackgroundTask();
                bg.execute();
                break;

            case R.id.option_3:
                startActivity(new Intent(getActivity(),music_home.class));
                break;

            case R.id.option_4:
                startActivity(new Intent(getActivity(),twitter_home.class));
                break;

            case R.id.option_5:Toast.makeText(getActivity(),"Option 5 is Selected",Toast.LENGTH_LONG).show();break;
            case R.id.option_6:
                startActivity(new Intent(getActivity(),insta.class));
                break;
            case R.id.option_7:
                startActivity(new Intent(getActivity(),tube.class));
                break;
        }

    }

}
