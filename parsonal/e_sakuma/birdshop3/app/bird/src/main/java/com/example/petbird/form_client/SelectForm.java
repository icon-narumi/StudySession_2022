package com.example.petbird.form_client;

import java.util.List;

import com.example.petbird.bean.CartBean;
import com.example.petbird.bean.StrPetBirdBean;
import com.example.petbird.bean.UnitIdBean;

public class SelectForm {
    
    Integer[] id;
    Integer onlyId;
    Integer count;
    
    List<Integer> selectIdList;

    List<StrPetBirdBean> strPetBirdBeanList;
    List<CartBean> cartBeanList;
    List<UnitIdBean> cartAllList;
    UnitIdBean cartAll;

    List<Integer> alreadyInCartList;
    

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
    
    public List<Integer> getSelectIdList() {
        return selectIdList;
    }
    public void setSelectIdList(List<Integer> selectIdList) {
        this.selectIdList = selectIdList;
    }
    public Integer[] getId() {
        return id;
    }
    public void setId(Integer[] id) {
        this.id = id;
    }
    public Integer getOnlyId() {
        return onlyId;
    }
    public void setOnlyId(Integer onlyId) {
        this.onlyId = onlyId;
    }
    public UnitIdBean getCartAll() {
        return cartAll;
    }
    public void setCartAll(UnitIdBean cartAll) {
        this.cartAll = cartAll;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public List<Integer> getAlreadyInCartList() {
        return alreadyInCartList;
    }
    public void setAlreadyInCartList(List<Integer> alreadyInCartList) {
        this.alreadyInCartList = alreadyInCartList;
    }
    
    

   
    
}
