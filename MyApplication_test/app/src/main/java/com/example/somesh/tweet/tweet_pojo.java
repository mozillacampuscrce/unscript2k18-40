package com.example.somesh.tweet;

/**
 * Created by Somesh on 2/17/2018.
 */

public class tweet_pojo {
    private String text;
    private String url;
    private String img;
    private String result_type;
    private String name;
    private String Location;
    private String Created;

    tweet_pojo(String text,String url,String img,String result_type,String name,String Location,String Created){
        this.setText(text);
        this.setImg(img);
        this.setLocation(Location);
        this.setName(name);
        this.setResult_type(result_type);
        this.setUrl(url);
        this.setCreated(Created);
    }
    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }


}
