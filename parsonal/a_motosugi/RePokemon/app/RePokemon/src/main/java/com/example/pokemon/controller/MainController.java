package com.example.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.pokemon.bean.AllPartyViewBean;
//import com.example.pokemon.entity.MtrainerEntity;
import com.example.pokemon.form.SelectPartyForm;
import com.example.pokemon.form.SelectTrainerForm;
import com.example.pokemon.service.TrainerService;
import com.example.pokemon.service.mapper.MtrainerService;
import com.example.pokemon.service.mapper.PartyPokemonService;

@Controller
public class MainController {

    @Autowired
    MtrainerService mtrainerService;
    @Autowired
    PartyPokemonService partyPokemonService;
    @Autowired
    TrainerService trainerService;
    /* 引数持ったインスタンスは↑の形じゃ使えないよ */

    // ホーム画面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init() {
        return "home";
    }

    // 手持ちポケモンを見る（トレーナー選択）
    @PostMapping("/select/trainer")
    public String trainer(Model model) {
        SelectTrainerForm selectTrainerForm = new SelectTrainerForm();
        selectTrainerForm.setMtrainerList(mtrainerService.selectMtrainer()); // トレーナーのセレクトボックス
        selectTrainerForm.settId(null);

        model.addAttribute("selectTrainerForm", selectTrainerForm);
        return "selectTrainer";
    }

    // 手持ち一覧表示
    @PostMapping("/select/party")
    public String party(@ModelAttribute SelectTrainerForm selectTrainerForm, Model model) {
        
        // 選択したトレーナーのtId
        Integer tId = selectTrainerForm.gettId();

        // tIdをtrainerServiseにセット
        trainerService.settId(tId);
        // 手持ち一覧をtrainerServiceから持ってくる
        List<AllPartyViewBean> partyViewList = trainerService.partyView();

        SelectPartyForm selectPartyForm = new SelectPartyForm();
        selectPartyForm.setAllPartyList(partyViewList); //手持ち一覧
        selectPartyForm.setTrainer(trainerService.trainerName()); //トレーナーの名前
        selectPartyForm.settId(tId);

        model.addAttribute("selectPartyForm", selectPartyForm);
        return "selectParty";
    }
}