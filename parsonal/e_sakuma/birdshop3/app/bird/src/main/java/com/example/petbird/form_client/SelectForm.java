package com.example.petbird.form_client;

import java.util.List;

import com.example.petbird.bean.CartBean;
import com.example.petbird.bean.StrPetBirdBean;
import com.example.petbird.bean.UnitIdBean;

public class SelectForm {
    
    Integer[] Id;

    List<Integer> selectIdList;
    
    List<StrPetBirdBean> strPetBirdBeanList;
    List<CartBean> cartBeanList;
    List<UnitIdBean> cartAllList;

    

    public List<StrPetBirdBean> getStrPetBirdBeanList() {
        return strPetBirdBeanList;
    }
    public void setStrPetBirdBeanList(List<StrPetBirdBean> strPetBirdBeanList) {
        this.strPetBirdBeanList = strPetBirdBeanList;
    }
    
    public List<UnitIdBean> getCartAllList() {
        return cartAllList;
    }
    public void setCartAllList(List<UnitIdBean> cartAllList) {
        this.cartAllList = cartAllList;
    }
    public List<CartBean> getCartBeanList() {
        return cartBeanList;
    }
    public void setCartBeanList(List<CartBean> cartBeanList) {
        this.cartBeanList = cartBeanList;
    }
    public Integer[] getId() {
        return Id;
    }
    public void setId(Integer[] id) {
        Id = id;
    }

   
    
}
