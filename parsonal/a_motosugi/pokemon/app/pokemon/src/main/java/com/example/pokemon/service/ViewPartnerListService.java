package com.example.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;

@Service
public class ViewPartnerListService {

    // DBからもってきた値にこうげきりょく追加(ListをPartnerBeanからViewPartnerBeanへ)
    public List<ViewPartnerBean> convertViewPartnerBeanList(List<PartnerBean> partnerBeanList){
        List<ViewPartnerBean> viewPartnerBeanList = new ArrayList<ViewPartnerBean>();

        for(Integer i = 0; i < partnerBeanList.size(); i++) {
            viewPartnerBeanList.add(convertViewPartnerBean(partnerBeanList.get(i)));
        }

        return viewPartnerBeanList;

    }

    // DBからもってきた値にこうげきりょく追加(PartnerBeanからViewPartnerBeanへ入れ替え)
    public ViewPartnerBean convertViewPartnerBean(PartnerBean partnerBean) {
        ViewPartnerBean viewPartnerBean = new ViewPartnerBean();
        viewPartnerBean.setNum(partnerBean.getNum());
        viewPartnerBean.setId(partnerBean.getId());
        viewPartnerBean.setName(partnerBean.getName());
        viewPartnerBean.setType1(partnerBean.getType1());
        viewPartnerBean.setType2(partnerBean.getType2());
        viewPartnerBean.setStrength(partnerBean.getStrength());
        viewPartnerBean.setAttackType(partnerBean.getAttackType());
        viewPartnerBean.setPower(culPower(partnerBean.getStrength())); //こうげきりょく

        return viewPartnerBean;
    }
 
    // つよさの半分の値がこうげきりょく
    public Integer culPower(Integer strength) {
        Integer power = strength / 2;
        return power;
    }
}
