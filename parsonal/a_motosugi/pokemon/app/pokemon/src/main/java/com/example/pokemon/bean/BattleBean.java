package com.example.pokemon.bean;

import java.util.ArrayList;
import java.util.List;

// ViewPartnerBeanが２個入ってるよ
public class BattleBean implements Cloneable{
    
    List<ViewPartnerBean> trainer1PartnerList;
    List<ViewPartnerBean> trainer2PartnerList;

    @Override
    public BattleBean clone() {
        BattleBean battleBean = null;
        try {
            battleBean = (BattleBean)super.clone();
            battleBean.trainer1PartnerList = new ArrayList<ViewPartnerBean>();
            if(this.trainer1PartnerList != null) {
                for(ViewPartnerBean viewPartnerBean : this.trainer1PartnerList) {
                    battleBean.trainer1PartnerList.add(viewPartnerBean.clone());
                }          
            }
            battleBean.trainer2PartnerList = new ArrayList<ViewPartnerBean>();
            if(this.trainer2PartnerList != null) {
                for(ViewPartnerBean viewPartnerBean : this.trainer2PartnerList) {
                    battleBean.trainer2PartnerList.add(viewPartnerBean.clone());
                }          
            }
            return battleBean;
        } catch(CloneNotSupportedException ex){
            return null;
        }
    } 

    public List<ViewPartnerBean> getTrainer1PartnerList() {
        return trainer1PartnerList;
    }
    public void setTrainer1PartnerList(List<ViewPartnerBean> trainer1PartnerList) {
        this.trainer1PartnerList = trainer1PartnerList;
    }
    public List<ViewPartnerBean> getTrainer2PartnerList() {
        return trainer2PartnerList;
    }
    public void setTrainer2PartnerList(List<ViewPartnerBean> trainer2PartnerList) {
        this.trainer2PartnerList = trainer2PartnerList;
    }

    
}
