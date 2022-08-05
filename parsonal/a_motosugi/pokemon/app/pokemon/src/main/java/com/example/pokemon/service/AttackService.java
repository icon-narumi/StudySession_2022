package com.example.pokemon.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        String pokemon1Type2 = pokemon1.getType2();
        String pokemon2Type1 = pokemon2.getType1();
        String pokemon2Type2 = pokemon2.getType2();
        String pokemon1AttackType = pokemon1.getAttackType();
        String pokemon2AttackType = pokemon2.getAttackType();

        // 単タイプかもしれないから先にタイプ２に対する効果は１を設定
        double effect1_2 = 1;
        double effect2_2 = 1;

        // 効果:こうげき１、まもり２のタイプ１
        double effect1_1 = typeCompatibilityService.selectTypeCompatibilityEffect(pokemon1AttackType, pokemon2Type1);
        // もし複合タイプなら　効果:こうげき１、まもり２のタイプ２
        if(pokemon2Type2 != null) {
            effect1_2 = typeCompatibilityService.selectTypeCompatibilityEffect(pokemon1AttackType, pokemon2Type2);
        }
        // 効果:こうげき２、まもり１のタイプ１
        double effect2_1 = typeCompatibilityService.selectTypeCompatibilityEffect(pokemon2AttackType, pokemon1Type1);
        // もし複合タイプなら　効果:こうげき２、まもり１のタイプ２
        if(pokemon1Type2 != null) {
            effect2_2 = typeCompatibilityService.selectTypeCompatibilityEffect(pokemon2AttackType, pokemon1Type2);
        }   

        // 計算用
        BigDecimal culStrength1 = new BigDecimal(strength1);
        BigDecimal culStrength2 = new BigDecimal(strength2);
        BigDecimal culPower1 = new BigDecimal(power1);
        BigDecimal culPower2 = new BigDecimal(power2);
        BigDecimal culEffect1_1 = new BigDecimal(effect1_1);
        BigDecimal culEffect1_2 = new BigDecimal(effect1_2);
        BigDecimal culEffect2_1 = new BigDecimal(effect2_1);
        BigDecimal culEffect2_2 = new BigDecimal(effect2_2);
        BigDecimal resultStrength1 = new BigDecimal(strength1);
        BigDecimal resultStrength2 = new BigDecimal(strength2);

        // どちらかのつよさも０より大きい間はループ
        while(resultStrength1.compareTo(BigDecimal.ZERO) > 0 && resultStrength2.compareTo(BigDecimal.ZERO) > 0) {
            
            // ポケモン1の体力
            BigDecimal aaa = resultStrength1;
            resultStrength1 = resultStrength1.subtract(culPower2.multiply(culEffect2_1).multiply(culEffect2_2)).setScale(0, RoundingMode.HALF_UP); 
            /*resultStrength1 = culPower2.multiply(culEffect2_1);
            resultStrength1 = resultStrength1.multiply(culEffect2_2);
            resultStrength1 = culStrength1.subtract(resultStrength1).setScale(0, RoundingMode.HALF_UP);*/
            //strength1 = strength1 - power2 * effect2_1 * effect2_2; 
            System.out.println(pokemon1.getName() + "(つよさ)" + aaa + "- (こうげき)" + culPower2 + 
                                " × (効果1)" + culEffect2_1 + " × (効果2)" + culEffect2_2 + " ＝ "+ resultStrength1); 

            // ポケモン2の体力
            BigDecimal bbb = resultStrength2;
            resultStrength2 = resultStrength2.subtract(culPower1.multiply(culEffect1_1).multiply(culEffect1_2)).setScale(0, RoundingMode.HALF_UP);
           /* resultStrength2 = culPower1.multiply(culEffect1_1);
            resultStrength2 = resultStrength2.multiply(culEffect1_2);
            resultStrength2 = culStrength2.subtract(resultStrength2).setScale(0, RoundingMode.HALF_UP); */
            System.out.println(pokemon2.getName() + "(つよさ)" + bbb + "- (こうげき)" + culPower1 + 
                                " × (効果1)" + culEffect1_1  + " × (効果2)" + culEffect1_2 + " ＝ " + resultStrength2);  
            //strength2 = strength2 - power1 * effect1; 
        }

        // BigDecimalからintへ変換
        strength1 = resultStrength1.intValue();
        strength2 = resultStrength2.intValue();


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
