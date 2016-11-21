package com.example.adnan.onetoonechat;

/**
 * Created by Adnan on 10/5/2015.
 */
public class messages {
    String mess;
    String time;
    String name;


    public messages(String mess, String time, String name) {
        this.mess = mess;
        this.time = time;
        this.name = name;
    }

    public messages() {
    }

    public String getMess() {

        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
