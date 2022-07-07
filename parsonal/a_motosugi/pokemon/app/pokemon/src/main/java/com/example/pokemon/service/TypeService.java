package com.example.pokemon.service;

import org.springframework.stereotype.Service;

import com.example.pokemon.bean.TypeStrengthBean;

@Service
public class TypeService {

    
    
    public TypeStrengthBean typeCompare(String type1, String type2) {
        TypeStrengthBean typeStrengthBean = new TypeStrengthBean();
        
        // もし「ほのお×みず」なら ほのお＝１、みず＝２
        if(type1.equals("ほのお") && type2.equals("みず")) {
            typeStrengthBean.setTrainerType1(1);
            typeStrengthBean.setTrainerType2(2);
        }else if(type1.equals("みず") && type2.equals("ほのお")) {
            typeStrengthBean.setTrainerType1(2);
            typeStrengthBean.setTrainerType2(1);
        }else {
            typeStrengthBean.setTrainerType1(1);
            typeStrengthBean.setTrainerType2(1);
        }

        return typeStrengthBean;
    }

}
