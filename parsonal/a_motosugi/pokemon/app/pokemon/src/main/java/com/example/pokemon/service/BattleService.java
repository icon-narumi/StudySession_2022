package com.example.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.bean.BattleBean;
import com.example.pokemon.bean.MultiTypeStrength;
import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.TypeStrengthBean;

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

 /*    // PartnerBeanからNumAndStrengthBeanに入れる
    public List<NumAndStrengthBean> PartnerBeanToNumAndStrengthBean(List<PartnerBean> partnerList) {
        NumAndStrengthBean numAndStrengthBean = new NumAndStrengthBean();
        List<NumAndStrengthBean> list = new ArrayList<NumAndStrengthBean>();

        for(Integer i = 0; i < partnerList.size(); i++) {
            // PartnerBeanのnumとstrengthを入れる
            Integer num = partnerList.get(i).getNum();
            Integer strength = partnerList.get(i).getStrength();
            numAndStrengthBean.setNum(num);
            numAndStrengthBean.setStrength(strength);
            list.add(numAndStrengthBean);
        }

        return list;
    } */

    // List<PartnerBean>をBattleBeanに入れる
    public BattleBean NBeanToBattleBean(List<PartnerBean> PartnerBeanList1, List<PartnerBean> PartnerBeanList2) {
        // バトル用の手持ちの一覧
        BattleBean battleBean = new BattleBean();
       /*  List<PartnerBean> battlePartnerBeanList1 = new ArrayList<PartnerBean>();
        List<PartnerBean> battlePartnerBeanList2 = new ArrayList<PartnerBean>();
        
        for(Integer i = 0; i < PartnerBeanList1.size(); i++) {
            battlePartnerBeanList1.add(PartnerBeanList1.get(i));
        }

        for(Integer i = 0; i < PartnerBeanList2.size(); i++) {
            battlePartnerBeanList2.add(PartnerBeanList2.get(i));
        }
        
        battleBean.setTrainer1PartnerList(battlePartnerBeanList1);
        battleBean.setTrainer2PartnerList(battlePartnerBeanList2); */

        battleBean.setTrainer1PartnerList(PartnerBeanList1);
        battleBean.setTrainer2PartnerList(PartnerBeanList2);

        return battleBean;
    }

    // 手持ち回して戦います(バトル結果の表を返す)
    public BattleBean resultBattle(BattleBean battleBean) {

        Integer trainer1Count = 0;
        Integer trainer2Count = 0;

        // 返すバトル結果表の入れ物
        BattleBean resultBattleBean = new BattleBean();
        // バトル結果表を入れる箱(トレーナー１)
        List<PartnerBean> resultTrainer1PartnerList = new ArrayList<PartnerBean>();
        // バトルしたポケモン1体の箱
        PartnerBean trainer1ResultPartnerBean = new PartnerBean();
        
        Integer strength1 = 0;
        Integer strength2 = 0;

        // どっちも手持ちが0以上ならループ
        while(trainer1Count < battleBean.getTrainer1PartnerList().size() && trainer2Count < battleBean.getTrainer2PartnerList().size()) {

            // 一旦バトル結果表のつよさは全部0にしておく
            trainer1ResultPartnerBean.setId(battleBean.getTrainer1PartnerList().get(trainer1Count).getId());
            trainer1ResultPartnerBean.setName(battleBean.getTrainer1PartnerList().get(trainer1Count).getName());
            trainer1ResultPartnerBean.setNum(battleBean.getTrainer1PartnerList().get(trainer1Count).getNum());
            trainer1ResultPartnerBean.setType1(battleBean.getTrainer1PartnerList().get(trainer1Count).getType1());
            trainer1ResultPartnerBean.setType2(battleBean.getTrainer1PartnerList().get(trainer1Count).getType2());
            trainer1ResultPartnerBean.setStrength(0);
            resultTrainer1PartnerList.add(trainer1ResultPartnerBean);

            strength1 = battleBean.getTrainer1PartnerList().get(trainer1Count).getStrength();//トレーナー1の手持ち1番目の強さ
            strength2 = battleBean.getTrainer2PartnerList().get(trainer2Count).getStrength();//トレーナー2の手持ち1番目の強さ

            /*  もし強さ勝ちなら、相手の強さ分引かれて生き残り
                負けたら強さ＝０、count+1
                引き分けなら共倒れ  */
            if(strength1 > strength2) {
                strength1 = strength1 - strength2;// バトルしてつよさ削る
                strength2 = 0;// 戦闘不能
                /*PartnerBean aaa = new PartnerBean();
                aaa.setId(battlebean.getTrainer1PartnerList().get(trainer1Count).getId());*/
                // バトル後のつよさをセットし直す
                //battleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                //battleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);

                // トレーナー2は次のてもちを出す
                trainer2Count++;
            }else if(strength1 < strength2) {
                strength2 = strength2 - strength1;
                strength1 = 0;
                battleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                battleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                trainer1Count++;
            }else{
                strength1 = 0;
                strength2 = 0;
                battleBean.getTrainer1PartnerList().get(trainer1Count).setStrength(strength1);
                battleBean.getTrainer2PartnerList().get(trainer2Count).setStrength(strength2);
                trainer1Count++;
                trainer2Count++;
            }
        }

        // もし手持ち残っているなら(trainerCountが手持ちサイズより小さい）、その手持ちの分リストのつよさを0から残っているつよさに戻す
        
        if(trainer1Count < battleBean.getTrainer1PartnerList().size()) {
            trainer1ResultPartnerBean.setStrength(strength1);
            while(trainer1Count + 1 < battleBean.getTrainer1PartnerList().size()) {
                trainer1ResultPartnerBean.setStrength(battleBean.getTrainer1PartnerList().get(trainer1Count).getStrength());
                trainer1Count++;
            }
        }
        

        resultBattleBean.setTrainer1PartnerList(resultTrainer1PartnerList);
        resultBattleBean.setTrainer2PartnerList(battleBean.getTrainer2PartnerList());

    /*    // trainer1Countもtrainer1Countも手持ちの数超えたら引き分け
        if(trainer1Count > battlebean.getTrainer1List().size() && trainer2Count > battlebean.getTrainer2List().size()) {
            result = 2;
        }else if(trainer1Count > battlebean.getTrainer1List().size()) {
            // 1が負けだよ
        }else if(trainer2Count > battlebean.getTrainer2List().size()) {
            //　2が負けだよ
        }
        return result;  */

        return resultBattleBean;
    }

    //手持ちの強さの合計を出して勝ち負けを判断
    public Integer sumStrengthToResult(BattleBean battleBean) {
        
        // トレーナーそれぞれの手持ちリスト
        List<PartnerBean> trainer1List = battleBean.getTrainer1PartnerList();
        List<PartnerBean> trainer2List = battleBean.getTrainer2PartnerList();

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
