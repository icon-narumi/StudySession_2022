package com.example.pokemon.form;

import java.util.List;

import com.example.pokemon.bean.AllPartyViewBean;

public class SelectPartyForm {
    
    String trainer;
    List<AllPartyViewBean> allPartyList;
    Integer tId;

    public String getTrainer() {
        return trainer;
    }
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
    public List<AllPartyViewBean> getAllPartyList() {
        return allPartyList;
    }
    public void setAllPartyList(List<AllPartyViewBean> allPartyList) {
        this.allPartyList = allPartyList;
    }
    public Integer gettId() {
        return tId;
    }
    public void settId(Integer tId) {
        this.tId = tId;
    }  
    
}
