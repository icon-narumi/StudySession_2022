package com.example.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;

@Service
public class ViewPartnerListService {

    // DBからもってきた値にこうげきりょく追加(ListをPartnerBeanからViewPartnerBeanへ)
    public List<ViewPartnerBean> aaa(List<PartnerBean> partnerBeanList){
        List<ViewPartnerBean> viewPartnerBeanList = new ArrayList<ViewPartnerBean>();

        return viewPartnerBeanList;

    }

    // DBからもってきた値にこうげきりょく追加(PartnerBeanからViewPartnerBeanへ)

    // つよさの半分の値がこうげきりょく
    
}
