package com.example.pokemon.bean;

import java.util.ArrayList;
import java.util.List;

public class BattleBean implements Cloneable{
    
    List<PartnerBean> trainer1PartnerList;
    List<PartnerBean> trainer2PartnerList;

    @Override
    public BattleBean clone() {
        BattleBean battleBean = null;
        try {
            battleBean = (BattleBean)super.clone();
            battleBean.trainer1PartnerList = new ArrayList<PartnerBean>();
            if(this.trainer1PartnerList != null) {
                for(PartnerBean partnerBean : this.trainer1PartnerList) {
                    battleBean.trainer1PartnerList.add(partnerBean.clone());
                }          
            }
            battleBean.trainer2PartnerList = new ArrayList<PartnerBean>();
            if(this.trainer2PartnerList != null) {
                for(PartnerBean partnerBean : this.trainer2PartnerList) {
                    battleBean.trainer2PartnerList.add(partnerBean.clone());
                }          
            }
            return battleBean;
        } catch(CloneNotSupportedException ex){
            return null;
        }
    } 

    public List<PartnerBean> getTrainer1PartnerList() {
        return trainer1PartnerList;
    }
    public void setTrainer1PartnerList(List<PartnerBean> trainer1PartnerList) {
        this.trainer1PartnerList = trainer1PartnerList;
    }
    public List<PartnerBean> getTrainer2PartnerList() {
        return trainer2PartnerList;
    }
    public void setTrainer2PartnerList(List<PartnerBean> trainer2PartnerList) {
        this.trainer2PartnerList = trainer2PartnerList;
    }

    
}
