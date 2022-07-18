package com.example.saibaikun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.saibaikun.entity.SaibaiDaichoEntity;
import com.example.saibaikun.entity.UserEntity;
import com.example.saibaikun.form.MainForm;
import com.example.saibaikun.form.SetupForm;
import com.example.saibaikun.form.SetupForm2;
import com.example.saibaikun.form.SetupForm3;
import com.example.saibaikun.service.SetUpService;

@Controller
public class SetupController {

    //初期表示
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(Model model) {
        SetupForm setupForm = new SetupForm();

        model.addAttribute("setupForm", setupForm);

        return "index";
    }


    //つぎへボタン押下(index→index2)
    @RequestMapping(params = "to2nd", method = RequestMethod.POST)
    public String regist2(@ModelAttribute SetupForm setupForm, Model model) {

        SetupForm2 setupForm2 = new SetupForm2();

        String userName = setupForm.getUserName();
        boolean result = setUpService.userCheck(userName);

        if (result) {
            setupForm2.setUserCheck("true");
            System.out.println("あかさたな"+result);
        }
        
        setupForm2.setUserName(setupForm.getUserName());
        setupForm2.setCharacterList(setUpService.getCharacterList());

        model.addAttribute("setupForm2", setupForm2);

        return "index2";
    }

    //つぎへボタン押下(index2→index3)
    @RequestMapping(params = "to3rd", method = RequestMethod.POST)
    public String regist3(@ModelAttribute SetupForm2 setupForm2, Model model) {
        SetupForm3 setupForm3 = new SetupForm3();

        setupForm3.setUserName(setupForm2.getUserName());
        setupForm3.setCharacterId(setupForm2.getCharacterId());

        model.addAttribute("setupForm3", setupForm3);

        return "index3";
    }

    //開始ボタン押下（登録済み）
    @Autowired
    SetUpService setUpService;
    // @RequestMapping(value = "/saibaikun", params = "start", method = RequestMethod.POST)
    // public String start(@ModelAttribute SetupForm2 setupForm2, Model model) {

    //     MainForm mainForm = new MainForm();

    //     // // viewにformをセット
    //     model.addAttribute("mainForm", mainForm);

    //     // /saibaikun/index.htmlを表示する
    //     return "/saibaikun/index";
    // }

    //もどるボタン押下
    // @RequestMapping(params = "goBack", method = RequestMethod.POST)
    // public String back(@ModelAttribute SetupForm2 setupForm2, Model model) {

    //     return "index";
    // }


    //開始ボタン押下（新規）
    @RequestMapping(value = "/saibaikun", params = "start", method = RequestMethod.POST)
    public String regist(@ModelAttribute SetupForm3 setupForm3, Model model) {

        MainForm mainForm = new MainForm();

        UserEntity         userEntity         = new UserEntity();
        SaibaiDaichoEntity saibaiDaichoEntity = new SaibaiDaichoEntity();
        // ActionRrkEntity    actionRrkEntity    = new ActionRrkEntity();
        // LoginLogEntity     loginLogEntity     = new LoginLogEntity();

        userEntity.setUserName(setupForm3.getUserName());
        saibaiDaichoEntity.setCharacterId(setupForm3.getCharacterId());
        saibaiDaichoEntity.setSaibaiName(setupForm3.getSaibaikunName());

        boolean result = setUpService.addData(userEntity);

        if (!result) {
            return "error";
        }

        // // viewにformをセット
        model.addAttribute("mainForm", mainForm);

        // /saibaikun/index.htmlを表示する
        return "/saibaikun/index";
    }

}