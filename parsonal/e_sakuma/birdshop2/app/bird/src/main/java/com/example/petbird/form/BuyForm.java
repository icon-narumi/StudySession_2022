package com.example.petbird.form;

import java.util.List;

import com.example.petbird.bean.IdCountBean;
import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.PetBirdEntity;

public class BuyForm {
    
    Integer id;
    
    PetBirdEntity petEntity;
    PetBirdEntity line;
    String  total;
    String comment;
    Integer count;
    Integer dbCount;
    

    PetBirdBean beanList;

    List<PetBirdBean> checks;

    IdCountBean idCountBean;
    
    List<IdCountBean> idCountBeanList;

    List<CastPetBirdEntity> cartList;




     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public PetBirdBean getBeanList() {
        return beanList;
    }

    public void setBeanList(PetBirdBean beanList) {
        this.beanList = beanList;
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

    public PetBirdEntity getLine() {
        return line;
    }

    public void setLine(PetBirdEntity line) {
        this.line = line;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDbCount() {
        return dbCount;
    }

    public void setDbCount(Integer dbCount) {
        this.dbCount = dbCount;
    }

    public List<PetBirdBean> getChecks() {
        return checks;
    }

    public void setChecks(List<PetBirdBean> checks) {
        this.checks = checks;
    }

    public List<IdCountBean> getIdCountBeanList() {
        return idCountBeanList;
    }

    public void setIdCountBeanList(List<IdCountBean> idCountBeanList) {
        this.idCountBeanList = idCountBeanList;
    }

    public IdCountBean getIdCountBean() {
        return idCountBean;
    }

    public void setIdCountBean(IdCountBean idCountBean) {
        this.idCountBean = idCountBean;
    }

    public List<CastPetBirdEntity> getCartList() {
        return cartList;
    }

    public void setCartList(List<CastPetBirdEntity> cartList) {
        this.cartList = cartList;
    }

    

   
}
