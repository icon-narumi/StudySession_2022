package com.example.pokemon.controller;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.form.DelForm;
import com.example.pokemon.form.PartnerForm;
import com.example.pokemon.service.PokemonService;
import com.example.pokemon.service.TrainerLevelService;
import com.example.pokemon.service.TrainerNameService;
import com.example.pokemon.service.TrainerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DelController {

    @Autowired
    TrainerService trainerService;
    @Autowired
    TrainerNameService trainerNameService;
    @Autowired
    PokemonService pokemonService;
    @Autowired
    TrainerLevelService trainerLevelService;

     // 手持ち削除画面
     @PostMapping(value = "/select/partner", params = "del")
     public String selectDelPartner(@ModelAttribute PartnerForm partnerForm, Model model) {
 
         Integer tId = partnerForm.gettId();
         String trainer = trainerNameService.trainerName(tId);
         List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);
 
         DelForm delForm = new DelForm();
         delForm.setTrainer(trainer + "のポケモン");
         delForm.settId(tId);
         delForm.setPartnerList(trainerPartnerList);
         delForm.setResultMessage("");
         model.addAttribute("delForm", delForm);
         return "del";
     }
 
     // 削除ボタン押下
     @RequestMapping(value = "/del/return", params = "delete", method = RequestMethod.POST)
     public String delete(@RequestParam Integer delete, @ModelAttribute DelForm delForm, Model model) {
 
         // ※Integer delete にはidが入っている
 
         String resultMessage = "ポケモンをにがしたよ　バイバイ！";
         Integer tId = delForm.gettId();
         String trainer = trainerNameService.trainerName(tId);
         List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);
 
         boolean result = pokemonService.deletePartner(delete, trainerPartnerList);
         List<PartnerBean> trainerPartnerList2 = pokemonService.selectPartner(tId); // 削除してからの手持ちリスト取得
 
         if (!result) {
             resultMessage = "このポケモンはにがせないよ";
         }
 
         delForm.setTrainer(trainer + "のポケモン");
         delForm.settId(tId);
         delForm.setPartnerList(trainerPartnerList2);
         delForm.setResultMessage(resultMessage);
         model.addAttribute("delForm", delForm);
         return "del";
     }
 
     // 削除画面からそのまま戻る
     @PostMapping(value = "/del/return", params = "return")
     public String returnPartnerFromDel(@ModelAttribute DelForm delForm, Model model) {
         Integer tId = delForm.gettId();
         String trainer = trainerNameService.trainerName(tId);
         List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);
 
         PartnerForm partnerForm = new PartnerForm();
         partnerForm.setTrainerList(trainerService.selectTrainerMaster()); // トレーナーのセレクトボックス
         partnerForm.settId(tId);
         partnerForm.setTrainer(trainer + "のポケモン");
         partnerForm.setPokemonList(trainerPartnerList); // 手持ちリスト(追加後)
 
         model.addAttribute("partnerForm", partnerForm);
         return "partner";
     }
    
}
