package com.example.pokemon.service;

import org.springframework.stereotype.Service;

@Service
public class UpdateLevelService {
    Integer trainerLevel1;
    Integer trainerLevel2;

    public Integer getTrainerLevel1() {
        return trainerLevel1;
    }
    public void setTrainerLevel1(Integer trainerLevel1) {
        this.trainerLevel1 = trainerLevel1;
    }
    public Integer getTrainerLevel2() {
        return trainerLevel2;
    }
    public void setTrainerLevel2(Integer trainerLevel2) {
        this.trainerLevel2 = trainerLevel2;
    }
    
    public Integer updateLevel1(Integer result) {
        Integer level = 0;

        // result=1ならトレーナー１のレベルアップ
        if(result == 1 && trainerLevel1 < 100) {
            level = trainerLevel1 + 1;
        // result=2ならトレーナー１のレベルダウン
        }else if(result == 2 && trainerLevel1 > 1) {
            level = trainerLevel1 - 1;
        }else{
            level = trainerLevel1;
        }
        return level;
    }

    public Integer updateLevel2(Integer result) {
        Integer level = 0;

        if(result == 2 && trainerLevel2 < 100) {
            level = trainerLevel2 + 1;
        }else if(result == 1 && trainerLevel2 > 1) {
            level = trainerLevel2 - 1;
        }else{
            level = trainerLevel2;
        }
        return level;
    }

}
