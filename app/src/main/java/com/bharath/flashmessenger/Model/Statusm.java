package com.bharath.flashmessenger.Model;


import java.util.ArrayList;

public class Statusm extends Statusim {
    private String name,profileimg;
    private String lastupdate;
    ArrayList<Statusm> statusms;

    public Statusm() {
    }

    public Statusm(String name, String profileimg, String lastupdate, ArrayList<Statusm> statusms) {
        this.name = name;
        this.profileimg = profileimg;
        this.lastupdate = lastupdate;
        this.statusms = statusms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public ArrayList<Statusm> getStatusms() {
        return statusms;
    }

    public void setStatusms(ArrayList<Statusm> statusms) {
        this.statusms = statusms;
    }
}
