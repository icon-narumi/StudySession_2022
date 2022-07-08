package com.example.pokemon.controller;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.form.AddPartnerForm;
import com.example.pokemon.form.PartnerForm;
import com.example.pokemon.service.PokemonService;
import com.example.pokemon.service.TrainerLevelService;
import com.example.pokemon.service.TrainerNameService;
import com.example.pokemon.service.TrainerService;
import com.example.pokemon.service.UpdateLevelService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddController {

    @Autowired
    TrainerService trainerService;
    @Autowired
    TrainerNameService trainerNameService;
    @Autowired
    PokemonService pokemonService;
    @Autowired
    UpdateLevelService updateLevelService;
    @Autowired
    TrainerLevelService trainerLevelService;

    @PostMapping(value = "/select/partner", params = "add")
    public String inputAddPartner(@ModelAttribute PartnerForm partnerForm, Model model) {

        Integer tId = partnerForm.gettId();
        System.out.println(tId);
        String trainer = trainerNameService.trainerName(tId);
        List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);

        AddPartnerForm addPartnerForm = new AddPartnerForm();
        addPartnerForm.setResultMessage("");
        addPartnerForm.setTrainer(trainer+ "のポケモン");
        addPartnerForm.setPartnerList(trainerPartnerList); // 手持ちリスト
        addPartnerForm.setPokemonList(pokemonService.selectPokemonWithNum()); // Noカラム追加されたm_pokemon
        addPartnerForm.settId(tId); 
        addPartnerForm.setSelectPokemonList(pokemonService.selectPokemon()); // 追加するポケモンのセレクトボックス
        addPartnerForm.setpId(null);
        addPartnerForm.setStrength("");
        model.addAttribute("addPartnerForm", addPartnerForm);
        return "addPartner";
    }

    // 手持ち追加したよ
    //これ何見て作ったんですか？茂木
    //ポケモンの動きすごいですね… 佐久間
    @PostMapping(value = "/add/return", params = "add")
    public String addPartner(@Valid @ModelAttribute AddPartnerForm addPartnerForm, BindingResult bindingResult, Model model) {
        
        Integer tId = addPartnerForm.gettId();
        String trainer = trainerNameService.trainerName(tId);
        List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);
        Integer pId = addPartnerForm.getpId();
        Integer strength = Integer.parseInt(addPartnerForm.getStrength());

        // 手持ちが6匹なら追加できない
        if(trainerPartnerList.size() >= 6) {
            addPartnerForm.setResultMessage("てもちがいっぱいだよ");
            addPartnerForm.setPartnerList(trainerPartnerList); // 手持ちリスト
        // バリデーション
        } else if(bindingResult.hasErrors()) {
            addPartnerForm.setTrainer(trainer+ "のポケモン");
            addPartnerForm.setPartnerList(trainerPartnerList);
            addPartnerForm.setPokemonList(pokemonService.selectPokemonWithNum());
            addPartnerForm.setSelectPokemonList(pokemonService.selectPokemon());
            return "addPartner";
        // 手持ち追加
        } else { 
            pokemonService.addPartner(tId, pId, strength); // 手持ち追加(DB登録)
            List<PartnerBean> trainerPartnerList2 = pokemonService.selectPartner(tId); //追加してからの手持ちリスト取得
            addPartnerForm.setResultMessage("てもちついか！");
            addPartnerForm.setPartnerList(trainerPartnerList2); // 手持ちリスト   
        } 
        addPartnerForm.setTrainer(trainer+ "のポケモン");
        addPartnerForm.setPokemonList(pokemonService.selectPokemonWithNum()); // Noカラム追加されたm_pokemon
        addPartnerForm.settId(tId); 
        addPartnerForm.setSelectPokemonList(pokemonService.selectPokemon()); // 追加するポケモンのセレクトボックス
        addPartnerForm.setpId(null);
        addPartnerForm.setStrength("");

        model.addAttribute("addPartnerForm", addPartnerForm);
        return "addPartner";
    }

    // 追加しないでそのまま前の画面に戻るよ
    @PostMapping(value = "/add/return", params = "return")
    public String returnPartner(@ModelAttribute AddPartnerForm addPartnerForm, Model model) {
        Integer tId = addPartnerForm.gettId();
        System.out.println(tId);
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
