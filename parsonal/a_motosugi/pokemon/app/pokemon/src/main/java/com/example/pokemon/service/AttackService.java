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
    
    public StrengthBean pokemon1VSpokemon2(ViewPartnerBean pokemon1, ViewPartnerBean pokemon2) {
        // バトル後のつよさを入れる箱
        StrengthBean strengthBean = new StrengthBean();

        // それぞれのつよさ・こうげきりょく
        Integer strength1 = pokemon1.getStrength();
        Integer power1 = pokemon1.getPower();
        Integer strength2 = pokemon2.getStrength();
        Integer power2 = pokemon2.getPower();

        // どちらかのつよさも０より大きい間はループ
        while(strength1 > 0 && strength2 > 0) {
            strength1 = strength1 - power2;
            strength2 = strength2 - power1;
        }

        // つよさマイナスなら０にする（表示の見栄え用）
        if(strength1 < 0) {
            strength1 = 0;
        }
        if(strength2 < 0) {
            strength2 = 0;
        }

        // 戦闘後のつよさをセット
        strengthBean.setStrength1(strength1);
        strengthBean.setStrength2(strength2);

        return strengthBean;
     }
}
