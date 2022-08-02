package com.example.pokemon.controller;

import com.example.pokemon.bean.BattleBean;
//import com.example.pokemon.bean.MultiTypeStrength;
//import com.example.pokemon.bean.NumAndStrengthBean;
import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.bean.ViewPartnerBean;
//import com.example.pokemon.bean.TypeStrengthBean;
import com.example.pokemon.form.BattleForm;
import com.example.pokemon.form.ResultForm;
import com.example.pokemon.service.PokemonService;
import com.example.pokemon.service.ResultMessageService;
import com.example.pokemon.service.BattleService;
import com.example.pokemon.service.TrainerLevelService;
import com.example.pokemon.service.LevelService;
import com.example.pokemon.service.TrainerNameService;
import com.example.pokemon.service.TrainerService;
import com.example.pokemon.service.UpdateLevelService;
import com.example.pokemon.service.ViewPartnerListService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BattleController {

    @Autowired
    TrainerService trainerService;
    @Autowired
    TrainerNameService trainerNameService;
    @Autowired
    PokemonService pokemonService;
    @Autowired
    BattleService battleService;
    @Autowired
    ResultMessageService resultMessageService;
    @Autowired
    LevelService levelService;
    @Autowired
    UpdateLevelService updateLevelService;
    @Autowired
    TrainerLevelService trainerLevelService;
    @Autowired
    ViewPartnerListService viewPartnerListService;

    //トレーナーを選ぶ画面
    @PostMapping("/battle")
    public String battle(Model model) {
        BattleForm battleForm = new BattleForm();

        battleForm.setTrainerList(trainerService.selectTrainerMaster());
        battleForm.setTrainerLevelList(trainerService.selectTrainerAndLevel());
        battleForm.settId1(null);
        battleForm.settId2(null);

        model.addAttribute("battleForm", battleForm);
        return "battle";
    }

    // バトル結果画面
    @PostMapping("/result")
    public String result(@ModelAttribute BattleForm battleForm, Model model) {
        Integer tId1 = battleForm.gettId1();
        Integer tId2 = battleForm.gettId2();

        // t_idから名前を持ってくる
        String trainer1 = trainerNameService.trainerName(tId1);
        String trainer2 = trainerNameService.trainerName(tId2);

        // 手持ちポケモンのリスト
        List<PartnerBean> trainer1PartnerList = pokemonService.selectPartner(tId1);
        List<PartnerBean> trainer2PartnerList = pokemonService.selectPartner(tId2);
        List<ViewPartnerBean> viewPartnerList1 = viewPartnerListService.convertViewPartnerBeanList(trainer1PartnerList);
        List<ViewPartnerBean> viewPartnerList2 = viewPartnerListService.convertViewPartnerBeanList(trainer2PartnerList);

        // 手持ちのリストをBattleBeanに入れる
        BattleBean battleBean = battleService.NBeanToBattleBean(viewPartnerList1, viewPartnerList2);
        //BattleBean battleBean = new BattleBean(trainer1PartnerList, trainer2PartnerList);

        // バトルした結果の表
        BattleBean resultList = battleService.resultBattle(battleBean);

        // 結果表から最終的な勝ち負けを判断
        Integer result = battleService.sumStrengthToResult(resultList);

        // トレーナーレベル
        Integer trainerLevel1 = levelService.trainerLevel(tId1);
        Integer trainerLevel2 = levelService.trainerLevel(tId2);
        updateLevelService.setTrainerLevel1(trainerLevel1);
        updateLevelService.setTrainerLevel2(trainerLevel2);
        
        // レベルアップデート
        trainerLevel1 = updateLevelService.updateLevel1(result);
        trainerLevel2 = updateLevelService.updateLevel2(result);
        trainerLevelService.updateLevel(trainerLevel1, tId1);
        trainerLevelService.updateLevel(trainerLevel2, tId2);

        // メッセージ
        String tMsg1 = resultMessageService.trainer1Msg(trainer1, result);
        String tMsg2 = resultMessageService.trainer2Msg(trainer2, result);
        String lMsg1 = resultMessageService.levelMsg1(trainerLevel1, result);
        String lMsg2 = resultMessageService.levelMsg2(trainerLevel2, result);
        
        ResultForm resultForm = new ResultForm();
        resultForm.setTrainer1(trainer1);//トレーナー１の名前
        resultForm.setTrainer2(trainer2);//トレーナー２の名前
        resultForm.setTrainer1Msg(tMsg1);//勝ち負けメッセージ
        resultForm.setTrainer2Msg(tMsg2);
        resultForm.setLevel1Msg(lMsg1);//レベルアップorダウンメッセージ
        resultForm.setLevel2Msg(lMsg2);
        resultForm.setPokemonList1(viewPartnerList1);//手持ちリスト(戦う前)
        resultForm.setPokemonList2(viewPartnerList2);
        resultForm.setResultList1(resultList.getTrainer1PartnerList());//手持ちリスト(最終結果)
        resultForm.setResultList2(resultList.getTrainer2PartnerList());
        resultForm.setTrainerLevelList(trainerService.selectTrainerAndLevel());//トレーナー一覧
        
        model.addAttribute("resultForm", resultForm);
        return "result";
    }
    
}
