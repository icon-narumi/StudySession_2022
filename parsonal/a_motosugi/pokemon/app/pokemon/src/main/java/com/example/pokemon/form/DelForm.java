package com.example.pokemon.form;

import java.util.List;

import com.example.pokemon.bean.PartnerBean;

public class DelForm {
    
    String resultMessage;
    String trainer;
    Integer tId;
    List<PartnerBean> partnerList;

    public String getResultMessage() {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    public String getTrainer() {
        return trainer;
    }
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
    public Integer gettId() {
        return tId;
    }
    public void settId(Integer tId) {
        this.tId = tId;
    }
    public List<PartnerBean> getPartnerList() {
        return partnerList;
    }
    public void setPartnerList(List<PartnerBean> partnerList) {
        this.partnerList = partnerList;
    }
    
    
}
