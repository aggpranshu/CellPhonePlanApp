package com.example.paggarwal1.sampletestapp;

import java.util.Date;

/**
 * Created by PAggarwal1 on 9/30/2015.
 */
public class CallLogs {
    private Long number;
    private int duration;
    private Date date;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    CallLogs(String number, String duration, Date date){
        this.number = Long.valueOf(number);
        this.duration = Integer.valueOf(duration);
        this.date = date;
    }
}
