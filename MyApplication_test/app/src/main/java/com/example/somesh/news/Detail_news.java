package com.example.somesh.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somesh.myapplication_test.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Detail_news extends AppCompatActivity {


    TextView title,detail,url;
    ImageView img;
    String t,d,u,im;
  // private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        int i=getIntent().getExtras().getInt("position");
        String input=getIntent().getExtras().getString("json");
        JSONObject json= null;
        try {
            json = new JSONObject(input);
           t=(String )json.getJSONArray("results").getJSONObject(i).get("title");
           d=(String) json.getJSONArray("results").getJSONObject(i).get("abstract");
           u=(String) json.getJSONArray("results").getJSONObject(i).get("url");
           im=(String) json.getJSONArray("results").getJSONObject(i).getJSONArray("multimedia").getJSONObject(3).get("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("index","no is :"+im);
//        context=context.getApplicationContext();

        title=(TextView)findViewById(R.id.tx_title1);
        detail=(TextView)findViewById(R.id.tx_detail);
        url=(TextView)findViewById(R.id.tx_url);
        img=(ImageView)findViewById(R.id.tx_img);
        if(im==null){
            Picasso.with(this.getApplicationContext()).load("https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwj5kfui1M_YAhXGQo8KHRb8DRcQjRwIBw&url=https%3A%2F%2Fcode.tutsplus.com%2Ftutorials%2Fhow-to-solve-androids-most-common-error-messages--cms-28706&psig=AOvVaw0Qfns1kmXAWxRUtGpS3cyl&ust=1515751399613622")
                    .fit().centerCrop()
                    .placeholder(R.drawable.circleyellow)
                    .error(R.drawable.circle)
                    .into(img);
        }
        else {
            Picasso.with(this.getApplicationContext()).load(im).fit().centerCrop()
                    .placeholder(R.drawable.circleyellow)
                    .error(R.drawable.circle)
                    .into(img);
        }
        title.setText("Title: "+t);
        detail.setText("Detail: "+d);
        url.setText("Url: "+u);
    }

    public void website(View view){

        String url = u;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
