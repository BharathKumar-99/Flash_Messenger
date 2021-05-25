package com.bharath.flashmessenger.Model;


public class Statusim {
    private String imageurl,caption;
    private String timestamp;

    public Statusim() {
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Statusim(String imageurl, String timestamp,String caption) {
        this.imageurl = imageurl;
        this.timestamp = timestamp;
        this.caption=caption;
    }
}