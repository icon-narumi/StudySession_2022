package com.example.saibaikun.entity;
public class LoginLogEntity {

    private Integer userId;
    private Integer  rrkNo;
    private String loginTm;
    private String zenkaiLoginTm;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRrkNo() {
        return rrkNo;
    }
    public void setRrkNo(Integer rrkNo) {
        this.rrkNo = rrkNo;
    }

    // public Timestamp getLoginTm() {
    //     return loginTm;
    // }
    // public void setLoginTm(Timestamp loginTm) {
    //     this.loginTm = loginTm;
    // }
    // public Timestamp getZenkaiLoginTm() {
    //     return zenkaiLoginTm;
    // }
    // public void setZenkaiLoginTm(Timestamp zenkaiLoginTm) {
    //     this.zenkaiLoginTm = zenkaiLoginTm;
    // }

    public String getLoginTm() {
        return loginTm;
    }
    public void setLoginTm(String loginTm) {
        this.loginTm = loginTm;
    }
    public String getZenkaiLoginTm() {
        return zenkaiLoginTm;
    }
    public void setZenkaiLoginTm(String zenkaiLoginTm) {
        this.zenkaiLoginTm = zenkaiLoginTm;
    }

    
}