package com.example.petbird.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.CastPetBirdEntity;
import com.example.petbird.entity.SpeciesEntity;
import com.example.petbird.mapper.LoginMapper;


@Service
public class CastService {
    @Autowired
    private LoginMapper loginMapper;
    
    //テーブル結合前の一行を全てString型に変換していたが、結合後のテーブルを変換するメソッドになった
    public PetBirdBean strCast(CastPetBirdEntity castPetBirdEntity){

        PetBirdBean petBirdBean = new PetBirdBean();

        petBirdBean.setId(strChange(castPetBirdEntity.getId()));
        petBirdBean.setSpecies(castPetBirdEntity.getSpeciesname());
        petBirdBean.setSex(castPetBirdEntity.getSexname());
        petBirdBean.setColor(castPetBirdEntity.getColorname());
        petBirdBean.setLife(strChange(castPetBirdEntity.getLife())+"年");
        petBirdBean.setCount(strChange(castPetBirdEntity.getCount())+"羽");
        petBirdBean.setPrice("¥"+(String.format("%,d", castPetBirdEntity.getPrice())));


        return petBirdBean;
    }

    //変換した一行をListに格納していく
    public List<PetBirdBean> strList(List<CastPetBirdEntity> castPetBirdEntitys){

        List<PetBirdBean> petBirdBean = new ArrayList<PetBirdBean>();

        for(int i = 0 ; i < castPetBirdEntitys.size(); i++){
            
            petBirdBean.add(strCast(castPetBirdEntitys.get(i)));
        }

        return petBirdBean;
    }
    
    //Integer型のVolumeをString型に変換し単位を付ける
    public String strChange(Integer number){
		String dateFormat = new String(number+"");
        return dateFormat;     
    }

    //String型のSpeciesをListに変換
    public List<SpeciesEntity> listSpeciesChange(String species){	
        return loginMapper.speciesList(species);     
    }       

   }
