package com.example.pokemon.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.StrengthBean;
import com.example.pokemon.bean.TypeStrengthBean;
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
    
    @Autowired
    TypeCompatibilityService typeCompatibilityService;

    public StrengthBean pokemon1VSpokemon2(ViewPartnerBean pokemon1, ViewPartnerBean pokemon2) {
        // バトル後のつよさを入れる箱
        StrengthBean strengthBean = new StrengthBean();

        // それぞれのつよさ・こうげきりょく
        Integer strength1 = pokemon1.getStrength();
        Integer power1 = pokemon1.getPower();
        Integer strength2 = pokemon2.getStrength();
        Integer power2 = pokemon2.getPower();
        // タイプ・攻撃タイプ
        String pokemon1Type1 = pokemon1.getType1();
        String pokemon2Type1 = pokemon2.getType1();
        String pokemon1AttackType = pokemon1.getAttackType();
        String pokemon2AttackType = pokemon2.getAttackType();

        // 効果
        double effect1 = typeCompatibilityService.selectTypeCompatibilityEffect(pokemon1AttackType, pokemon2Type1);
        double effect2 = typeCompatibilityService.selectTypeCompatibilityEffect(pokemon2AttackType, pokemon1Type1);

        // どちらかのつよさも０より大きい間はループ
        while(strength1 > 0 && strength2 > 0) {
            BigDecimal bd1 = new BigDecimal(strength1);
            BigDecimal bd2 = new BigDecimal(effect2);

           // BigDecimal result1 = 
            strength1 = strength1 - power2 * effect2; // ポケモン1の体力 
            strength2 = strength2 - power1 * effect1; // ポケモン2の体力
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

  /*  public Integer typeCompare(String type1, String type2) {
        TypeStrengthBean typeStrengthBean = new TypeStrengthBean();

        // もし「攻撃：ほのお」「守り：みず」なら　攻撃は半減

        // もし「攻撃：みず」「守り：ほのお」なら　攻撃は２倍




        
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
    }*/
}
