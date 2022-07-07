package com.example.pokemon.service;

import org.springframework.stereotype.Service;

@Service
public class ResultMessageService {

    // 勝ち負けメッセージ
    public String trainer1Msg(String trainer1, Integer result) {
        String message = "";

        if(result == 1) {
            message = trainer1 + "のしょうり！";
        }else if(result == 2) {
            message = trainer1 + "のまけ ";
        }else{
            message = "ひきわけ";
        }
        return message;
    }

    // 勝ち負けメッセージ
    public String trainer2Msg(String trainer2, Integer result) {
        String message = "";

        if(result == 2) {
            message = trainer2 + "のしょうり！";
        }else if(result == 1) {
            message = trainer2 + "のまけ ";
        }else{
            message = "ひきわけ";
        }
        return message;
    }

    // レベルメッセージ
    public String levelMsg1(Integer trainerLevel1, Integer result) {
        String message = "";
        if(result == 1 && trainerLevel1 < 100) {
            message = "トレーナーレベルが１あがった";
        }else if(result == 1 && trainerLevel1 == 100) {
            message = "トレーナーレベルは１００のまま";
        }else if(result == 2 && trainerLevel1 == 1) {
            message = "トレーナーレベルは１のまま";
        }else if(result == 2 && trainerLevel1 > 1) {
            message = "トレーナーレベルが１さがった";
        }else{
            message = "トレーナーレベルはそのまま";
        }
        return message;
    }

    // レベルメッセージ
    public String levelMsg2(Integer trainerLevel2, Integer result) {
        String message = "";
        if(result == 2 && trainerLevel2 < 100) {
            message = "トレーナーレベルが１あがった";
        }else if(result == 2 && trainerLevel2 == 100) {
            message = "トレーナーレベルは１００のまま";
        }else if(result == 1 && trainerLevel2 == 1) {
            message = "トレーナーレベルは１のまま";
        }else if(result == 1 && trainerLevel2 > 1) {
            message = "トレーナーレベルが１さがった";
        }else{
            message = "トレーナーレベルはそのまま";
        }
        return message;
    }
}
