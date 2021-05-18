package com.bharath.flashmessenger.Chatroom.Model;

public class ChatModel {
    String msg,senderid,reciverid,profilepic,attachment;
    long time;

    public ChatModel(long time, String msg, String senderid, String reciverid, String profilepic, String attachment) {
        this.time = time;
        this.msg = msg;
        this.senderid = senderid;
        this.reciverid = reciverid;
        this.profilepic = profilepic;
        this.attachment = attachment;
    }

    public ChatModel() {
    }

    public ChatModel(String msg, String senderid, long time,String profilepic) {
        this.msg = msg;
        this.senderid = senderid;
        this.time = time;
        this.profilepic=profilepic;

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReciverid() {
        return reciverid;
    }

    public void setReciverid(String reciverid) {
        this.reciverid = reciverid;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
