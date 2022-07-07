package com.example.bird.service;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bird.entity.BirdEntity;
import com.example.bird.entity.StrBirdEntity;

@Service
public class CastService {

    

    //テーブル結合前の一行を全てString型に変換していたが、結合後のテーブルを変換するメソッドになった
    public StrBirdEntity StrCast(BirdEntity birdEntitys){

        StrBirdEntity strBirdEntity = new StrBirdEntity();

        strBirdEntity.setSpecies(birdEntitys.getSpecies());
        strBirdEntity.setOrdo(birdEntitys.getOrdoname());
        strBirdEntity.setFamily(birdEntitys.getFamilyname());
        strBirdEntity.setVolume(Unit(birdEntitys.getVolume()));
        strBirdEntity.setFood(birdEntitys.getFoodname());

        return strBirdEntity;
    }

    //変換した一行をListに格納していく
    public List<StrBirdEntity> StrList(List<BirdEntity> birdEntity){

        List<StrBirdEntity> strBirdEntity = new ArrayList<StrBirdEntity>();

        for(int i = 0 ; i < birdEntity.size(); i++){
            
            strBirdEntity.add(StrCast(birdEntity.get(i)));
        }

        return strBirdEntity;
    }

    //Integer型のVolumeをString型に変換し単位を付ける
    public String Unit(Integer volume){
    
		String dateFormat = new String(volume +"㎝");
		
        return dateFormat;
        
    }

}
