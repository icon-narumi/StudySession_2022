package com.example.pokemon.service;

import org.springframework.stereotype.Service;

import com.example.pokemon.bean.StrengthBean;
import com.example.pokemon.bean.ViewPartnerBean;

// ポケモンどつきあい
@Service
public class AttackService {
    /*　・ポケモン２体持ってきます
    　　    ピカチュウ（でんき）　つよさ60,攻撃30  vs  トゲピー（フェアリー）　つよさ100,攻撃50
    
        ・どっちか死ぬまでたたかう
        　　1攻撃目
            ピカつよさ60 - トゲ攻撃50 = ピカつよさ10
            トゲつよさ100 - ピカ攻撃30 = トゲつよさ70

            2攻撃目
            ピカつよさ10 - トゲ攻撃50 = ピカつよさ-30 LOSE
            トゲつよさ70 - ピカ攻撃30 = トゲつよさ40 WIN

        ・つよさ返す(StrengthBean)
     */
    
    public StrengthBean aaa(ViewPartnerBean pokemon1, ViewPartnerBean pokemon2) {
        // バトル後のつよさを入れる箱
        StrengthBean strengthBean = new StrengthBean();

        // それぞれのつよさ・こうげきりょく
        Integer strength1 = pokemon1.getStrength();
        Integer power1 = pokemon1.getPower();
        Integer strength2 = pokemon2.getStrength();
        Integer power2 = pokemon2.getPower();

        


        return strengthBean;
     }
}
