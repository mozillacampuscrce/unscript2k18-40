package com.example.somesh.tweet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.somesh.myapplication_test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Somesh on 2/17/2018.
 */

public class tweet_Adapter extends ArrayAdapter {

    List list=new ArrayList();
    public tweet_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(tweet_pojo object) {
        list.add(object);
    }

    @Override
    public int getCount() {
        //   Log.d("Size is"+list.size(),"much");
        return list.size();
    }

    @Nullable
    @Override
    public tweet_pojo getItem(int position) {
        return (tweet_pojo) list.get(position);
    }


    private Context context;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        tweet_Adapter.news_Holder holder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =layoutInflater.inflate(R.layout.row_layout1,parent,false);
            holder=new tweet_Adapter.news_Holder();
            holder.tweet_name=row.findViewById(R.id.name_tweet);
            holder.tweet_url=row.findViewById(R.id.url_tweet);
            holder.tweet_location=row.findViewById(R.id.loc_tweet);
            holder.tweet_desc=row.findViewById(R.id.text_tweet);
            holder.tweet_res=row.findViewById(R.id.res_tweet);
            holder.tweet_created=row.findViewById(R.id.tweet_create);
            holder.tweet_img=row.findViewById(R.id.base_img);
            row.setTag(holder);
        }
        else{

            holder=(tweet_Adapter.news_Holder)row.getTag();
        }
        tweet_pojo pojo=(tweet_pojo) this.getItem(position);
        holder.tweet_name.setText("Person: "+pojo.getName());
        holder.tweet_url.setText("url: "+pojo.getUrl());
        holder.tweet_location.setText("Place: "+pojo.getLocation());
        holder.tweet_desc.setText("Tweet: "+pojo.getText());
        holder.tweet_res.setText("Section: "+pojo.getResult_type());
        holder.tweet_created.setText("Date: "+pojo.getCreated());
        Picasso.with(getContext()).load(pojo.getImg()).fit().centerCrop()
                .placeholder(R.drawable.circleyellow)
                .error(R.drawable.twitter)
               .into(holder.tweet_img);

        return row;
    }




    static class news_Holder{

        TextView tweet_name,tweet_url,tweet_location,tweet_desc,tweet_res,tweet_created;
        ImageView tweet_img;

    }

}
