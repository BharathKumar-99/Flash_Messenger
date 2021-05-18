package com.bharath.flashmessenger.Group.Model;

public class Friendlistmodel {
    String uid;

    public Friendlistmodel( String uid) {

        this.uid = uid;
    }

    public Friendlistmodel() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
