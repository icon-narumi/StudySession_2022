package com.example.bird.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bird.entity.BirdEntity;
import com.example.bird.entity.StrBirdEntity;

@Service
public class SearchService {

    //@Autowired
        //private StrBirdEntity strBirdEntity;
    
        //入力されたvolumeに該当するDBがないか探す
        String volume;//MainControllerより渡されたFormの値を保持しておく
        
        public String getVolume() {
            return volume;
        }
        public void setVolume(String volume) {
            this.volume = volume;
        }


        public List<StrBirdEntity> BirdSerch(List<StrBirdEntity> inBirdEntitys){

            List<StrBirdEntity> strBirdEntitys = new ArrayList<StrBirdEntity>();
            
            //getAllの行数だけ繰り返す
            for(int i = 0 ; i < inBirdEntitys.size(); i++){
                //
                if (inBirdEntitys.get(i).getVolume().contains(volume)){
                    strBirdEntitys.add(inBirdEntitys.get(i));
                }
            }
            return strBirdEntitys;

        }

        //DBと更新ワードをぶつける

        String species;//Formから受取った値を保持

        public String getSpecies() {
            return species;
        }
        public void setSpecies(String species) {
            this.species = species;
        }

        public List<StrBirdEntity> BirdUpdate(List<StrBirdEntity> inBirdEntitys){

            //更新対象のリストの作成
            List<StrBirdEntity> strBirdEntitys = new ArrayList<StrBirdEntity>();
            
            //getAllの行数だけ繰り返す
            for(int i = 0 ; i < inBirdEntitys.size(); i++){
                //
                if (inBirdEntitys.get(i).getSpecies().contains(species)){
                    strBirdEntitys.add(inBirdEntitys.get(i));
                }
            }
            return strBirdEntitys;

        }

        
}
