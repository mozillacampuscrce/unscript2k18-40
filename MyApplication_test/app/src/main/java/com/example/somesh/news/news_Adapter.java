package com.example.somesh.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.somesh.myapplication_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Somesh on 1/8/2018.
 */

public class news_Adapter extends ArrayAdapter {


    List list=new ArrayList();
    public news_Adapter(@NonNull Context context, int resource)
    {
        super(context, resource);
    }

    public void add(newsPojo object) {
        list.add(object);
    }

    @Override
    public int getCount() {
     //   Log.d("Size is"+list.size(),"much");
        return list.size();
    }

    @Nullable
    @Override
    public newsPojo getItem(int position) {
        return (newsPojo) list.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        news_Holder holder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =layoutInflater.inflate(R.layout.row_layout,parent,false);
            holder=new news_Holder();
            holder.tx_title=row.findViewById(R.id.tx_title);
            holder.tx_section=row.findViewById(R.id.tx_section);
            row.setTag(holder);
        }
        else{

            holder=(news_Holder)row.getTag();
        }
        newsPojo pojo=(newsPojo)this.getItem(position);
        holder.tx_title.setText("Title: "+pojo.getTitle());
        holder.tx_section.setText("Section: "+pojo.getSection());
        return row;
    }
    static class news_Holder{

        TextView tx_title,tx_section;

    }
}
