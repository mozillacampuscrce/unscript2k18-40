package com.example.somesh.news;

/**
 * Created by Somesh on 1/8/2018.
 */

public class newsPojo {
    private String title,detail,images,section,subsection,url;

    public newsPojo(String title,String detail,String images,String section,String subsection,String url){
        this.setDetail(detail);
        this.setImages(images);
        this.setTitle(title);
        this.setSection(section);
        this.setSubsection(subsection);
        this.setUrl(url);
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
