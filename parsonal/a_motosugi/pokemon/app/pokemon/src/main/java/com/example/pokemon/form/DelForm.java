package com.example.pokemon.form;

import java.util.List;

//import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;

public class DelForm {
    
    String resultMessage;
    String trainer;
    Integer tId;
    List<ViewPartnerBean> partnerList;

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
    public List<ViewPartnerBean> getPartnerList() {
        return partnerList;
    }
    public void setPartnerList(List<ViewPartnerBean> partnerList) {
        this.partnerList = partnerList;
    }
    
    
}
