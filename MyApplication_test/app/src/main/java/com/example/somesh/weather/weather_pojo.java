package com.example.somesh.weather;

/**
 * Created by Somesh on 1/11/2018.
 */

public class weather_pojo {

    private  String date,desc,humidity,temp;
    public weather_pojo(String date,String desc,String humidity,String temp){
        this.setDate(date);
        this.setHumidity(humidity);
        this.setDesc(desc);
        this.setTemp(temp);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
