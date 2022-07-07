package com.example.pokemon.controller;

import com.example.pokemon.bean.PartnerBean;
import com.example.pokemon.form.PartnerForm;
import com.example.pokemon.form.SelectTrainerForm;
import com.example.pokemon.service.PokemonService;
import com.example.pokemon.service.ResultMessageService;
import com.example.pokemon.service.BattleService;
import com.example.pokemon.service.TrainerLevelService;
import com.example.pokemon.service.LevelService;
import com.example.pokemon.service.TrainerNameService;
import com.example.pokemon.service.TrainerService;
import com.example.pokemon.service.TypeService;
import com.example.pokemon.service.UpdateLevelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    TrainerService trainerService;
    @Autowired
    TrainerNameService trainerNameService;
    @Autowired
    PokemonService pokemonService;
    @Autowired
    BattleService strengthService;
    @Autowired
    ResultMessageService resultMessageService;
    @Autowired
    LevelService levelService;
    @Autowired
    UpdateLevelService updateLevelService;
    @Autowired
    TrainerLevelService trainerLevelService;
    @Autowired
    TypeService typeService;

    // ホーム画面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init() {
        return "home";
    }

    // 手持ちポケモンを見る（トレーナー選択）
    @PostMapping("/select/trainer")
    public String partner(Model model) {
        SelectTrainerForm selectTrainerForm = new SelectTrainerForm();
        selectTrainerForm.setTrainerList(trainerService.selectTrainerMaster()); //トレーナーのセレクトボックス
        selectTrainerForm.settId(null);

        model.addAttribute("selectTrainerForm", selectTrainerForm);
        return "selectTrainer";
    }
    
    // 手持ち表示
    @PostMapping("/partner")
    public String partner(@ModelAttribute SelectTrainerForm selectTrainerForm, Model model) {
        Integer tId = selectTrainerForm.gettId();
        String trainer = trainerNameService.trainerName(tId);
        List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId); // No.カラム追加した手持ちリストを持ってくる

        PartnerForm partnerForm = new PartnerForm();
        partnerForm.setTrainerList(trainerService.selectTrainerMaster()); // トレーナーのセレクトボックス
        partnerForm.settId(tId);
        partnerForm.setTrainer(trainer + "のポケモン");
        partnerForm.setPokemonList(trainerPartnerList); // 手持ちリスト

        model.addAttribute("partnerForm", partnerForm);
        return "partner";
    } 

    // 手持ちポケモンを見る（再度リスト表示）
    @PostMapping(value = "/select/partner", params = "veiw")
    public String selectPartner(@ModelAttribute PartnerForm partnerForm, Model model) {
        Integer tId = partnerForm.gettId();
        String trainer = trainerNameService.trainerName(tId);
        List<PartnerBean> trainerPartnerList = pokemonService.selectPartner(tId); // No.カラム追加した手持ちリストを持ってくる

        partnerForm.setTrainerList(trainerService.selectTrainerMaster()); // トレーナーのセレクトボックス
        partnerForm.settId(tId);
        partnerForm.setTrainer(trainer + "のポケモン");
        partnerForm.setPokemonList(trainerPartnerList); // 手持ちリスト

        model.addAttribute("partnerForm", partnerForm);
        return "partner";
    }

}