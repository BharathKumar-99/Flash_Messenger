package com.bharath.flashmessenger.Setup.Model;

public class SetupModel {
    String Name,Status,Gender,Profile,Phonenum,Uid;

    public SetupModel(String name, String status,String phonenum, String gender, String profile,String uid) {
        Name = name;
        Status = status;
        Gender = gender;
        Profile = profile;
        Uid=uid;
        Phonenum=phonenum;
    }

    public SetupModel() {

    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getProfile() {
        return Profile;
    }

    public String getPhonenum() {
        return Phonenum;
    }

    public void setPhonenum(String phonenum) {
        Phonenum = phonenum;
    }

    public void setProfile(String profile) {
        Profile = profile;
    }
}
