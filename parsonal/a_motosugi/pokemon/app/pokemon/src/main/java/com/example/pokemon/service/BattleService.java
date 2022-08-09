package com.example.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.BattleBean;
import com.example.pokemon.bean.StrengthBean;
import com.example.pokemon.bean.ViewPartnerBean;

@Service
public class BattleService {

    @Autowired
    AttackService attackService;

    // List<PartnerBean>をBattleBeanに入れる
    public BattleBean NBeanToBattleBean(List<ViewPartnerBean> PartnerBeanList1, List<ViewPartnerBean> PartnerBeanList2) {

        BattleBean battleBean = new BattleBean();

        battleBean.setTrainer1PartnerList(PartnerBeanList1);
        battleBean.setTrainer2PartnerList(PartnerBeanList2);

        return battleBean;
    }

    // 手持ち回して戦います(バトル結果の表を返す)
    public BattleBean resultBattle(BattleBean battleBean){

        Integer trainer1Count = 0;
        Integer trainer2Count = 0;

        // 手持ちをコピーして違う箱に入れる
        BattleBean copyBattleBean = (BattleBean) battleBean.clone();

        // ポケモンのつよさ
        Integer strength1 = 0;
        Integer strength2 = 0;

        // それぞれのトレーナーカウントがトレーナー1,2の手持ちの数よりも小さければループし続ける
        while(trainer1Count < battleBean.getTrainer1PartnerList().size() && trainer2Count < battleBean.getTrainer2PartnerList().size()) {

            // たたかいの場に出るポケモン
            ViewPartnerBean pokemon1 = copyBattleBean.getTrainer1PartnerList().get(trainer1Count);
            ViewPartnerBean pokemon2 = copyBattleBean.getTrainer2PartnerList().get(trainer2Count);

            // ここでどつきあい
            StrengthBean afterAttackStrengthBean = attackService.pokemon1VSpokemon2(pokemon1, pokemon2);
            strength1 = afterAttackStrengthBean.getStrength1();
            strength2 = afterAttackStrengthBean.getStrength2();

            /*  もし強さ勝ちなら、相手の強さ分引かれて生き残り
                負けたら強さ＝０、count+1
                引き分けなら共倒れ  */
            if(strength1 > strength2) {
                //strength1 = strength1 - strength2;// バトルしてつよさ削る
                //strength2 = 0;// 戦闘不能
                
                // 戦闘後のつよさをセット
                copyBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                copyBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                // トレーナー2は次のてもちを出す
                trainer2Count++;

            }else if(strength1 < strength2) {
                //strength2 = strength2 - strength1;
                //strength1 = 0;
                copyBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                copyBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                trainer1Count++;
            }else{
                //strength1 = 0;
                //strength2 = 0;
                copyBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                copyBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                trainer1Count++;
                trainer2Count++;
            }
        }

        return copyBattleBean;
    }

    //手持ちの強さの合計を出して勝ち負けを判断
    public Integer sumStrengthToResult(BattleBean battleBean) {
        
        // トレーナーそれぞれの手持ちリスト
        List<ViewPartnerBean> trainer1List = battleBean.getTrainer1PartnerList();
        List<ViewPartnerBean> trainer2List = battleBean.getTrainer2PartnerList();

        // つよさ合計のためのリスト作成
        List<Integer> strengthList1 = new ArrayList<Integer>();
        List<Integer> strengthList2 = new ArrayList<Integer>();

        // リストを1行づつ見ていき、強さのカラムの値をstrengthListに入れる
        for(Integer i = 0; i < trainer1List.size(); i++) { 
            Integer strength = trainer1List.get(i).getStrength();
            strengthList1.add(strength);
        }
        for(Integer i = 0; i < trainer2List.size(); i++) { 
            Integer strength = trainer2List.get(i).getStrength();
            strengthList2.add(strength);
        }

        // strengthListの中身をそれぞれ足す
        Integer sum1 = 0;
        Integer sum2 = 0;
        for(Integer j = 0; j < strengthList1.size(); j++) {
            sum1 += strengthList1.get(j); 
        }
        for(Integer j = 0; j < strengthList2.size(); j++) {
            sum2 += strengthList2.get(j); 
        }

        /***** result=0:引き分け 1:トレーナー１勝利　2:トレーナー２勝利  *****/
        Integer result = 0;
        if(sum1 > sum2) {
            result = 1;
        }else if(sum2 > sum1) {
            result = 2;
        }else {
            result = 0;
        }
        return result;
    }
    
}
