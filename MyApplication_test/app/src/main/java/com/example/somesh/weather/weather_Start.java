package com.example.somesh.weather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.somesh.myapplication_test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class weather_Start extends AppCompatActivity {

    private String input_json,input_json1;
    private String json,json1,city;
    private w_pojo_1 pojo;
    ProgressDialog progressDialog;

    private  class BackgroundTask extends AsyncTask<String,Void,String> {

        String myurl;
        String key="ec647535b9db5d7494bf13ac703dba41";

        @Override
        protected void onPreExecute() {
            myurl="http://api.openweathermap.org/data/2.5/weather?APPID="+key;
        }

        @Override
        protected String doInBackground(String... strings) {
            URL Url;
            try {
                String city=pojo.getCity();
                Url = new URL(myurl+"&q="+ URLEncoder.encode(city));
                BufferedReader br=new BufferedReader(new InputStreamReader(Url.openStream()));
                input_json = br.readLine();
                Log.d("MyApp","The Weather Is:  "+input_json);
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
          json=s;
        }
    }
    private  class BackgroundTask1 extends AsyncTask<String,Void,String> {

        String myurl1;
       // String key="ec647535b9db5d7494bf13ac703dba41";

        @Override
        protected void onPreExecute() {
            progressDialog= ProgressDialog.show(weather_Start.this,"Please Wait....","Processing request..",true);
            myurl1="http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=ec647535b9db5d7494bf13ac703dba41";
        }

        @Override
        protected String doInBackground(String... strings) {
            URL Url;
            try {
                String city=pojo.getCity();
                Url = new URL(myurl1+"&q="+ URLEncoder.encode(city, "UTF-8"));
                BufferedReader br1=new BufferedReader(new InputStreamReader(Url.openStream()));
                input_json1 = br1.readLine();
                Log.d("MyApp","The Forecast Is:  "+input_json1);
                return input_json1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
                json1 = s;
                Intent i=new Intent(weather_Start.this,weather_report.class);
                i.putExtra("input",json);
                i.putExtra("input1",json1);
                i.putExtra("place",city);
                startActivity(i);
            }
        }
    }
    private AutoCompleteTextView view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather__start);
        view1=(AutoCompleteTextView) findViewById(R.id.Location);

    }

    private void runMultipleAsyncTask() // Run Multiple Async Task
    {
         BackgroundTask bg=new BackgroundTask(); // First
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) // Above Api Level 13
        {
            bg.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else // Below Api Level 13
        {
            bg.execute();
        }
        BackgroundTask1 bg1 = new BackgroundTask1(); // Second
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)// Above Api Level 13
        {
            bg1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else // Below Api Level 13
        {
            bg1.execute();
        }
    }



    public void pass_Detail(View view){
        city=view1.getEditableText().toString();
        if(city !=null){
            Log.d("My City ",view1.getText().toString());
            pojo =new w_pojo_1(city);
            runMultipleAsyncTask();
        }

        else{
            Toast.makeText(weather_Start.this,"Field Empty",Toast.LENGTH_LONG).show();
        }
    }
}
