package com.example.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.AllPartyViewBean;
import com.example.pokemon.entity.MtrainerEntity;
import com.example.pokemon.service.mapper.MtrainerService;
import com.example.pokemon.service.mapper.PartyPokemonService;

@Service
public class TrainerService {
    Integer tId;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }
    
    @Autowired
    MtrainerService mtrainerService;
    @Autowired
    PartyPokemonService partyPokemonService;
    
    // トレーナーの名前表示
    public String trainerName() {

        String name = "";
        List<MtrainerEntity> mtrainerList = mtrainerService.selectMtrainer();

        // トレーナーリストの中でtId同じのあれば名前もってくる
        for(Integer i = 0; i < mtrainerList.size(); i++) {
            if(this.tId.equals(mtrainerList.get(i).gettId())) {
                name = mtrainerList.get(i).getName();
            }
        }

        return name;
    }

    // 手持ちポケモン一覧
    public List<AllPartyViewBean> partyView() {

        // 名前・性別・レベル・HPをDBから持ってくる
        List<AllPartyViewBean> partyViewList = partyPokemonService.selectAllPartyViewBean(this.tId);

        return partyViewList;
    }
}
