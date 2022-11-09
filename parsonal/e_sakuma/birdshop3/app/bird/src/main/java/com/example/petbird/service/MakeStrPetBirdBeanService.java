package com.example.petbird.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petbird.bean.StrPetBirdBean;
import com.example.petbird.bean.UnitIdBean;
import com.example.petbird.entity_master.SpeciesEntity;
import com.example.petbird.mapper.T_LoginMapper;

@Service
public class MakeStrPetBirdBeanService {
    @Autowired
    private T_LoginMapper loginMapper;

     //Integer型のVolumeをString型に変換し単位を付ける
     public String intToString(Integer number){
		String dateFormat = new String(number+"");
        return dateFormat;     
    }

    //unitIdBeanの型を全てstrPetBirdBeanに合うよう変換
    public StrPetBirdBean strChange(UnitIdBean unitIdBean){

        StrPetBirdBean strPetBirdBean = new StrPetBirdBean();

        strPetBirdBean.setId(intToString(unitIdBean.getId()));
        strPetBirdBean.setSpecies(unitIdBean.getSpeciesname());
        strPetBirdBean.setSex(unitIdBean.getSexname());
        strPetBirdBean.setColor(unitIdBean.getColorname());
        strPetBirdBean.setLife(intToString(unitIdBean.getLife())+"年");
        strPetBirdBean.setCount(intToString(unitIdBean.getCount())+"羽");
        strPetBirdBean.setPrice("¥"+(String.format("%,d", unitIdBean.getPrice())));
        strPetBirdBean.setAlreadyCartCount(intToString(unitIdBean.getAlreadyCartCount()));

        return strPetBirdBean;
    }

    //変換した一行をListに格納していく
    public List<StrPetBirdBean> strChangeList(List<UnitIdBean> unitIdBean){

        List<StrPetBirdBean> strPetBirdBean = new ArrayList<StrPetBirdBean>();

        for(int i = 0 ; i < unitIdBean.size(); i++){
            
            strPetBirdBean.add(strChange(unitIdBean.get(i)));
        }

        return strPetBirdBean;
    }
   
    //String型のSpeciesをListに変換
    public List<SpeciesEntity> listSpeciesChange(String species){	
        return loginMapper.speciesList(species);     
    }
    
}
