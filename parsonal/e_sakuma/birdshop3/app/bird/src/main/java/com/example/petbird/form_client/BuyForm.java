package com.example.petbird.form_client;

import java.util.List;
import com.example.petbird.bean.StrPetBirdBean;

public class BuyForm {

    Integer id;

    List<StrPetBirdBean> strPetBirdBeanList;

    String totalPrice;
    String comment;

    

    public List<StrPetBirdBean> getStrPetBirdBeanList() {
        return strPetBirdBeanList;
    }

    public void setStrPetBirdBeanList(List<StrPetBirdBean> strPetBirdBeanList) {
        this.strPetBirdBeanList = strPetBirdBeanList;
    }

    
    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    
}
