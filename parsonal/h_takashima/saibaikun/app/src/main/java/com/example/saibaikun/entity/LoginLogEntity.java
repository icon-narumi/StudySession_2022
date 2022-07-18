package com.example.saibaikun.entity;
import java.sql.Timestamp;

public class LoginLogEntity {

    private Integer userId;
    private String  rrkNo;
    private Timestamp loginTm;
    private Timestamp zenkaiLoginTm;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getRrkNo() {
        return rrkNo;
    }
    public void setRrkNo(String rrkNo) {
        this.rrkNo = rrkNo;
    }
    public Timestamp getLoginTm() {
        return loginTm;
    }
    public void setLoginTm(Timestamp loginTm) {
        this.loginTm = loginTm;
    }
    public Timestamp getZenkaiLoginTm() {
        return zenkaiLoginTm;
    }
    public void setZenkaiLoginTm(Timestamp zenkaiLoginTm) {
        this.zenkaiLoginTm = zenkaiLoginTm;
    }

    
}
