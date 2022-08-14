package com.example.saibaikun.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;


@Service
public class DateService {

    //今日
    public String getDateYmd() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        Date dateObj = calendar.getTime();
        String date = dtf.format(dateObj);

        return date;
    }
    //前日
    public String getDateYmdYest() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date dateObj = calendar.getTime();
        String yestdate = dtf.format(dateObj);

        return yestdate;
    }
    //前々日
    public String getDateYmdDbyest() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        Date dateObj = calendar.getTime();
        String dbyestdate = dtf.format(dateObj);

        return dbyestdate;
    }
    //前々々日
    public String getDateYmdTDbyest() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        Date dateObj = calendar.getTime();
        String tdbyestdate = dtf.format(dateObj);

        return tdbyestdate;
    }


    public String getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String datetime = sdf.format(timestamp);

        return datetime;
    }

}
