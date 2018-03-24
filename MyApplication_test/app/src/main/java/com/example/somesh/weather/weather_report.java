package com.example.somesh.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.somesh.myapplication_test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class weather_report extends AppCompatActivity {

    private JSONObject jsonObject_data1;
    private JSONArray jsonArray;
    private JSONObject jsonObject_data;
    private TextView name,lat,longitude,temp,wind,desc;
    private  String input,input1,place,lati,longi,desc1,wind1;
    private Double tt,t;
    weather_Adapter adapter;
    ListView listview;
    private  String d,d2,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);
        input1=getIntent().getExtras().getString("input1");
        input=getIntent().getExtras().getString("input");
        place=getIntent().getExtras().getString("place");
        if(input!=null)
        setCard1();
        else
            Toast.makeText(weather_report.this,"Error ....",Toast.LENGTH_LONG).show();
        if(input1!=null)
        setCard2();
        else
         Toast.makeText(weather_report.this,"Error ....",Toast.LENGTH_LONG).show();
    }

    public void setCard1(){
        if(input!=null){
            Log.d("The Weater is ",input);
            name=(TextView)findViewById(R.id.location);
            lat=(TextView)findViewById(R.id.lat);
            longitude=(TextView)findViewById(R.id.longitude);
            if(place!=null)
            {
                name.setText("Location is: "+place);}
            else{
                Toast.makeText(weather_report.this,"Failed to set..",Toast.LENGTH_LONG).show();}
            temp=(TextView)findViewById(R.id.temp);
            desc=(TextView)findViewById(R.id.desc);
            wind=(TextView)findViewById(R.id.wind);
            try {
                Log.d("Arrived input ", input);
                jsonObject_data = new JSONObject(input);
                lati=jsonObject_data.getJSONObject("coord").getString("lat");
                longi=jsonObject_data.getJSONObject("coord").getString("lon");
                desc1=jsonObject_data.getJSONArray("weather").getJSONObject(0).getString("description");
                tt=jsonObject_data.getJSONObject("main").getDouble("temp");
                tt=tt-273.15;
                wind1=jsonObject_data.getJSONObject("wind").getString("speed");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            lat.setText("Latitude is: "+lati);
            longitude.setText("Longitude is: "+longi);
            int i = Integer.valueOf(tt.intValue());
            temp.setText(i+" C");
            wind.setText("Wind Speed is: "+wind1);
            desc.setText("Description: "+desc1);
        }

    }

    public void setCard2(){
        listview=(ListView)findViewById(R.id.listview_1);
        adapter=new weather_Adapter(this,R.layout.forcast_list_base);
        listview.setAdapter(adapter);

        if(input1!=null)
        {
            try {
                Log.d("MyApp","I am here "+input1);
                jsonObject_data=new JSONObject(input1);
                jsonArray=jsonObject_data.getJSONArray("list");
                int count=0;
                while (count<jsonArray.length())
                {
                    JSONObject jo=jsonArray.getJSONObject(count);
                    d2=jo.getJSONArray("weather").getJSONObject(0).getString("description");
                    h=jo.getJSONObject("main").getString("humidity");
                    d=jo.getString("dt_txt");
                    t=jo.getJSONObject("main").getDouble("temp");
                    t=t-273.15;
                    String t1=t.toString();
                    weather_pojo pojo=new weather_pojo(d,d2,h,t1);
                    adapter.add(pojo);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{

            Toast.makeText(weather_report.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
        }
    }
}
