package com.example.pokemon.controller;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.form.PartnerForm;
import com.example.pokemon.form.UpdateForm;
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
public class UpdateController {

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

    // レベル更新画面
    @PostMapping(value = "/select/partner", params = "update")
    public String inputUpdatePartner(@ModelAttribute PartnerForm partnerForm, Model model) {

        Integer tId = partnerForm.gettId();
        String trainer = trainerNameService.trainerName(tId);
        List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);

        UpdateForm updateForm = new UpdateForm();
        updateForm.setResultMessage("");
        updateForm.setTrainer(trainer + "のポケモン");
        updateForm.settId(tId);
        updateForm.setStrength("");
        updateForm.setPartnerList(trainerPartnerList); //手持ちリスト
        updateForm.setSelectPokemonList(trainerPartnerList); //セレクトボックス
        updateForm.setId(null);

        model.addAttribute("updateForm", updateForm);
        return "update";
    }

    // レベル更新
    @PostMapping(value = "/update/return", params = "update")
    public String updatePartner(@Valid @ModelAttribute UpdateForm updateForm, BindingResult bindingResult, Model model) {
        
        Integer tId = updateForm.gettId();
        String trainer = trainerNameService.trainerName(tId);
        List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId);
        Integer id = updateForm.getId();
        Integer strength = Integer.parseInt(updateForm.getStrength());
    
        // バリデーション
        if(bindingResult.hasErrors()) {
            updateForm.setTrainer(trainer+ "のポケモン");
            updateForm.setPartnerList(trainerPartnerList);
            updateForm.setSelectPokemonList(trainerPartnerList); //セレクトボックス
            return "update";
        }

        pokemonService.updateStrength(strength, id);// レベル更新(DB登録)
        List<PartnerBean> trainerPartnerList2 = pokemonService.selectPartner(tId); //更新してからの手持ちリスト取得

        updateForm.setResultMessage("レベルがかわったよ");
        updateForm.setTrainer(trainer + "のポケモン");
        updateForm.settId(tId);
        updateForm.setStrength("");
        updateForm.setPartnerList(trainerPartnerList2); //手持ちリスト
        updateForm.setSelectPokemonList(trainerPartnerList); //セレクトボックス
        updateForm.setId(null);

        model.addAttribute("updateForm", updateForm);
        return "update";
    }

    // 更新しないでそのまま前の画面に戻るよ
    @PostMapping(value = "/update/return", params = "return")
    public String returnPartnerFromUpdate(@ModelAttribute UpdateForm updateForm, Model model) {
        Integer tId = updateForm.gettId();
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
