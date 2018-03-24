package com.example.somesh.tweet;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.somesh.myapplication_test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class tweet_Home extends AppCompatActivity {

    private ProgressDialog pd;
    private String result;
    class twitterAPI extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(tweet_Home.this, "",
                    "Loading.....", true, false);
        }

        @Override
        protected String doInBackground(String... strings) {
            result=null;
            if (strings.length > 0) {

                result = TwitterUtils.getTimelineForSearchTerm(strings[0]);

            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {

            if (pd != null && pd.isShowing()) {
                pd.dismiss();
                Intent intent = new Intent(tweet_Home.this, tweet_Home.class);
                intent.putExtra("tweet", result);
                startActivity(intent);
                finish();
            }
            Log.d("twitter Result ", s);
        }
    }

    private FloatingActionButton actionButton;
    JSONObject jsonObject_data;
    JSONArray jsonArray;
    private  ListView listview;
    String text,url,img,result_type,name,location,created;
    tweet_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet__home);
        actionButton=(FloatingActionButton)findViewById(R.id.fa);
        String s=getIntent().getExtras().getString("tweet");
        listview=(ListView)findViewById(R.id.tweet_list);
        adapter=new tweet_Adapter(tweet_Home.this,R.layout.row_layout1);
        listview.setAdapter(adapter);
        if(s!=null)
        {
            try {
                Log.d("MyApp","I am here "+s);
                jsonObject_data=new JSONObject(s);
                jsonArray=jsonObject_data.getJSONArray("statuses");
                int count=0;
                while (count<jsonArray.length())
                {
                    JSONObject jo=jsonArray.getJSONObject(count);
                    created=jo.getString("created_at");
                    text=jo.getString("text");
                    url=jo.getJSONObject("user").getString("url");
                    result_type=jo.getJSONObject("metadata").getString("result_type");
                    location=jo.getJSONObject("user").getString("location");
                    name=jo.getJSONObject("user").getString("name");
                    img=jo.getJSONObject("user").getString("profile_background_image_url");
                    tweet_pojo pojo1=new tweet_pojo(text,url,img,result_type,name,location,created);
                    adapter.add(pojo1);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{

            Toast.makeText(tweet_Home.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
        }
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }
    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(tweet_Home.this);
        final View view = getLayoutInflater().inflate(R.layout.dialog_search, null);
        builder.setTitle("Enter #tag For Search");
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et_search = (EditText) view.findViewById(R.id.et_search);
                String search = et_search.getText().toString().trim();
                if (search.length() > 0) {
                    twitterAPI api=new twitterAPI();
                    api.execute(search);
                } else {
                    Toast.makeText(tweet_Home.this, "Error input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.create().show();


    }

}
