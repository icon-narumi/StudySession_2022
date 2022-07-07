package com.example.pokemon.bean;

import java.util.List;

public class BattleBean {
    
    List<PartnerBean> trainer1PartnerList;
    List<PartnerBean> trainer2PartnerList;

   /*  public BattleBean() {
    }
    public BattleBean(List<PartnerBean> trainer1PartnerList, List<PartnerBean> trainer2PartnerList) {
        this.trainer1PartnerList = trainer1PartnerList;
        this.trainer2PartnerList = trainer2PartnerList;
    }*/

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
