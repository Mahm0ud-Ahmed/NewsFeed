package com.example.newsfeed;

public class Fields {
    private String title;
    private String date;
    private String section;
    private String url;

    public Fields(String title, String date, String url) {
        this.date = date;
        this.title = title;
        this.url = url;
    }

    public Fields(String title, String date, String section, String url) {
        this.date = date;
        this.title = title;
        this.url = url;
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
