package com.example.somesh.tweet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.somesh.myapplication_test.R;


public class twitter_home extends AppCompatActivity {


    private AutoCompleteTextView view1;
    private Button button;
    private String result_tweet,result;
    private ProgressDialog pd;
    class twitterAPI extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(twitter_home.this, "",
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
                result_tweet=result;
                Intent intent = new Intent(twitter_home.this, tweet_Home.class);
                intent.putExtra("tweet", result_tweet);
                startActivity(intent);
            }
            Log.e("twitter Result ", s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_home);
        view1=(AutoCompleteTextView)findViewById(R.id.tweet);
        button=(Button)findViewById(R.id.but);
     }

    public void sync(View view) {
        twitterAPI api=new twitterAPI();
        String q=view1.getEditableText().toString();
        api.execute(q);

    }

}
