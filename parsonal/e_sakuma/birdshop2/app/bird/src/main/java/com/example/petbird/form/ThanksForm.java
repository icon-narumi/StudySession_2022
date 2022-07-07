package com.example.petbird.form;

import java.util.List;

import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.PetBirdEntity;

public class ThanksForm {
 
    
    Integer id;
    Integer incount;
    PetBirdEntity petEntity;
    String  total;
    String comment;
    Integer dbcount;

    
    List<PetBirdBean> beanList;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIncount() {
        return incount;
    }
    public void setIncount(Integer incount) {
        this.incount = incount;
    }
    public PetBirdEntity getPetEntity() {
        return petEntity;
    }
    public void setPetEntity(PetBirdEntity petEntity) {
        this.petEntity = petEntity;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getDbcount() {
        return dbcount;
    }
    public void setDbcount(Integer dbcount) {
        this.dbcount = dbcount;
    }
    public List<PetBirdBean> getBeanList() {
        return beanList;
    }
    public void setBeanList(List<PetBirdBean> beanList) {
        this.beanList = beanList;
    }





}
