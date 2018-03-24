package com.example.somesh.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.somesh.myapplication_test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class listview extends AppCompatActivity {

    String json;
    JSONObject jsonObject_data;
    JSONArray jsonArray;
    String title,detail,img,section,subsection,url ;
    news_Adapter adapter;
    ListView listview;
private Detail_news news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listview=(ListView)findViewById(R.id.listview);
        adapter=new news_Adapter(this,R.layout.row_layout);
        listview.setAdapter(adapter);
        json=getIntent().getExtras().getString("json_data");
            if(json!=null)
            {
                try {
                    Log.d("MyApp","I am here "+json);
                    jsonObject_data=new JSONObject(json);
                    jsonArray=jsonObject_data.getJSONArray("results");
                    int count=0;
                    while (count<jsonArray.length())
                    {
                        JSONObject jo=jsonArray.getJSONObject(count);
                        title=jo.getString("title");
                        detail=jo.getString("abstract");
                        img=jo.getString("thumbnail_standard");
                        section=jo.getString("section");
                        subsection=jo.getString("subsection");
                        url=jo.getString("url");
                        newsPojo pojo=new newsPojo(title,detail,img,section,subsection,url);
                        adapter.add(pojo);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{

                Toast.makeText(listview.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
            }
       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(listview.this, Detail_news.class);
               intent.putExtra("position",position);
               intent.putExtra("json",json);
               startActivity(intent);
           }
       });

    }
}
