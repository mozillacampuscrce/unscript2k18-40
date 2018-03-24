package com.example.somesh.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.somesh.myapplication_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Somesh on 1/14/2018.
 */

public class weather_Adapter extends ArrayAdapter {

    List list=new ArrayList();
    public weather_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    public void add(weather_pojo object) {
        list.add(object);
    }

    @Override
    public int getCount() {
          Log.d("Size is"+list.size()," much");
        return list.size();
    }

    @Nullable
    @Override
    public weather_pojo getItem(int position) {
        return (weather_pojo) list.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        weather_Adapter.weather_Holder holder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =layoutInflater.inflate(R.layout.forcast_list_base,parent,false);
            holder=new weather_Adapter.weather_Holder();
            holder.Date=row.findViewById(R.id.date);
            holder.D2=row.findViewById(R.id.d2);
            holder.Humidity=row.findViewById(R.id.humidity);
            holder.T1=row.findViewById(R.id.t1);

            row.setTag(holder);
        }
        else{

            holder=(weather_Adapter.weather_Holder)row.getTag();
        }
        weather_pojo pojo=(weather_pojo)this.getItem(position);
        holder.Date.setText("Date: "+pojo.getDate());
        holder.D2.setText("Description:"+pojo.getDesc());
        holder.T1.setText(pojo.getTemp()+"C");
        holder.Humidity.setText("Humidity: "+pojo.getHumidity());
        return row;
    }
    static class weather_Holder{

        TextView T1,Date,D2,Humidity;

    }
}
