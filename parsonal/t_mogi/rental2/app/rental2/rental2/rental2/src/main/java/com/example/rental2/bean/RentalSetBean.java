package com.example.rental2.bean;

public class RentalSetBean {
    
    //1行ずつ表示するためのbean
    //配列に格納
    private Integer id;

    private Integer statusId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }


}
