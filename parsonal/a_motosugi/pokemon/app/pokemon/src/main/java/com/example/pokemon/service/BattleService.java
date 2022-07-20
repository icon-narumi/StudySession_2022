package com.example.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.BattleBean;
import com.example.pokemon.bean.MultiTypeStrength;
import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.TypeStrengthBean;
import com.example.pokemon.bean.ViewPartnerBean;

@Service
public class BattleService {

    @Autowired
    TypeService typeCompareService;

    // 相性考慮して強さの合計に掛ける
    public MultiTypeStrength multiStrength(Integer strength1, Integer strength2, TypeStrengthBean typeStrengthBean) {
        MultiTypeStrength multiTypeStrength = new MultiTypeStrength();
        Integer trainer1 = 0;
        Integer trainer2 = 0;
        // strength1とtypeStrengthBeanのtrainerType1を掛ける
        trainer1 = strength1 * typeStrengthBean.getTrainerType1();
        
        // strength2とtypeStrengthBeanのtrainerType2を掛ける
        trainer2 = strength2 * typeStrengthBean.getTrainerType2();

        multiTypeStrength.setTrainerStrength1(trainer1);
        multiTypeStrength.setTrainerStrength2(trainer2);

        return multiTypeStrength;
    }

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

        // 返すバトル結果表の入れ物
        //BattleBean resultBattleBean = new BattleBean();

        BattleBean copyBattleBean = (BattleBean) battleBean.clone();

        // バトル結果表を入れる箱(トレーナー１、２)
     /*   List<PartnerBean> resultTrainer1PartnerList = new ArrayList<PartnerBean>();
        List<PartnerBean> resultTrainer2PartnerList = new ArrayList<PartnerBean>();

        // バトル結果表に手持ちの情報いれる
        for(Integer i = 0; i < battleBean.getTrainer1PartnerList().size(); i++) {
            resultTrainer1PartnerList.add(strength0PartnerBean(battleBean.getTrainer1PartnerList().get(i)));
        }
        for(Integer j = 0; j < battleBean.getTrainer2PartnerList().size(); j++) {
            resultTrainer2PartnerList.add(strength0PartnerBean(battleBean.getTrainer2PartnerList().get(j)));
        }
        resultBattleBean.setTrainer1PartnerList(resultTrainer1PartnerList);
        resultBattleBean.setTrainer2PartnerList(resultTrainer2PartnerList); */

        // ポケモンのつよさ
        Integer strength1 = 0;
        Integer strength2 = 0;

        // それぞれのトレーナーカウントがトレーナー1,2の手持ちの数よりも小さければループし続ける
        while(trainer1Count < battleBean.getTrainer1PartnerList().size() && trainer2Count < battleBean.getTrainer2PartnerList().size()) {

            //トレーナー1,2の手持ち1番目の強さ
            strength1 = copyBattleBean.getTrainer1PartnerList().get(trainer1Count).getStrength();
            strength2 = copyBattleBean.getTrainer2PartnerList().get(trainer2Count).getStrength();

            /*  もし強さ勝ちなら、相手の強さ分引かれて生き残り
                負けたら強さ＝０、count+1
                引き分けなら共倒れ  */
            if(strength1 > strength2) {
                strength1 = strength1 - strength2;// バトルしてつよさ削る
                strength2 = 0;// 戦闘不能
                
                // 戦闘後のつよさをセット
                copyBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                copyBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                // トレーナー2は次のてもちを出す
                trainer2Count++;

            }else if(strength1 < strength2) {
                strength2 = strength2 - strength1;
                strength1 = 0;
                copyBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                copyBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                trainer1Count++;
            }else{
                strength1 = 0;
                strength2 = 0;
                copyBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                copyBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                trainer1Count++;
                trainer2Count++;
            }
        }

    /*    // もし手持ち残っているなら(trainerCountが手持ちサイズより小さい）、その手持ちの分リストのつよさを0から残っているつよさに戻す
        if(trainer1Count < battleBean.getTrainer1PartnerList().size()) {
            // 最後に戦っていたポケモンのつよさをセット
            resultBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
            //trainer1ResultPartnerBean.setStrength(strength1);
            trainer1Count++;
        }
        // もし戦っていない無傷のポケモンいれば、そのつよさをそのままセット
        if(trainer1Count < battleBean.getTrainer1PartnerList().size()) {
            while(trainer1Count + 1 < battleBean.getTrainer1PartnerList().size()) {
                resultBattleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(battleBean.getTrainer1PartnerList().get(trainer1Count).getStrength());
                trainer1Count++;
            }
        }

        // トレーナー２も同様に
        if(trainer2Count < battleBean.getTrainer2PartnerList().size()) {
            resultBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
            trainer2Count++;
        }
        if(trainer2Count < battleBean.getTrainer2PartnerList().size()) {
            while(trainer2Count + 1 < battleBean.getTrainer2PartnerList().size()) {
                resultBattleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(battleBean.getTrainer2PartnerList().get(trainer2Count).getStrength());
                trainer2Count++;
            }
        }  */

    /*    // trainer1Countもtrainer1Countも手持ちの数超えたら引き分け
        if(trainer1Count > battlebean.getTrainer1List().size() && trainer2Count > battlebean.getTrainer2List().size()) {
            result = 2;
        }else if(trainer1Count > battlebean.getTrainer1List().size()) {
            // 1が負けだよ
        }else if(trainer2Count > battlebean.getTrainer2List().size()) {
            //　2が負けだよ
        }
        return result;  */

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
