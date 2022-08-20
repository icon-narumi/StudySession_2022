package com.example.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.pokemon.form.SelectTrainerForm;
import com.example.pokemon.service.mapper.MtrainerService;

@Controller
public class MainController {

    @Autowired
    MtrainerService mtrainerService;

    // ホーム画面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init() {
        return "home";
    }

    // 手持ちポケモンを見る（トレーナー選択）
    @PostMapping("/select/trainer")
    public String trainer(Model model) {
        SelectTrainerForm selectTrainerForm = new SelectTrainerForm();
        selectTrainerForm.setMtrainerList(mtrainerService.selectMtrainer()); //トレーナーのセレクトボックス
        selectTrainerForm.settId(null);

        model.addAttribute("selectTrainerForm", selectTrainerForm);
        return "selectTrainer";
    }

}