package com.bharath.flashmessenger.Setup.Model;

public class Selectormodel {
    String name,profile,uid,phonenum,status;



    public Selectormodel(String name, String profile, String uid, String phonenum,String status) {
        this.name = name;
        this.profile = profile;
        this.uid=uid;
        this.phonenum=phonenum;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Selectormodel() {
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getprofile() {
        return profile;
    }

    public void setprofile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
