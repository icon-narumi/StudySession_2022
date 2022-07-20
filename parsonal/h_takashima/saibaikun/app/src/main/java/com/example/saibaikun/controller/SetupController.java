package com.example.saibaikun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.saibaikun.entity.ActionRrkEntity;
import com.example.saibaikun.entity.LoginLogEntity;
import com.example.saibaikun.entity.SaibaiDaichoEntity;
import com.example.saibaikun.entity.UserEntity;
import com.example.saibaikun.form.MainForm;
import com.example.saibaikun.form.SetupForm;
import com.example.saibaikun.form.SetupForm2;
import com.example.saibaikun.form.SetupForm3;
import com.example.saibaikun.service.LoginService;
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


    @Autowired
    LoginService loginService;

    //つぎへボタン押下(index→index2)
    @RequestMapping(params = "to2nd", method = RequestMethod.POST)
    public String regist2(@ModelAttribute SetupForm setupForm, Model model) {

        SetupForm2 setupForm2 = new SetupForm2();

        //入力されたかいぬしの名前で検索（件数カウント）
        Integer userId = setUpService.userCheck(setupForm.getUserName());
        System.out.println("userid:"+userId);
        String userCheck = "false";

        //IDが取得できればtrueをわたす（登録済み判定）
        if ( userId > 0 ) {
            userCheck = "true";
            setupForm2.setCharacterList(loginService.getUserCharacterList(userId));
        }else{
            setupForm2.setCharacterList(setUpService.getCharacterList());
        }

        setupForm2.setUserId(userId);
        setupForm2.setUserCheck(userCheck);
        setupForm2.setUserName(setupForm.getUserName());


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
    @RequestMapping(value = "/saibaikun", params = "login", method = RequestMethod.POST)
    public String start(@ModelAttribute SetupForm2 setupForm2, Model model) {

        MainForm mainForm = new MainForm();

        // // viewにformをセット
        model.addAttribute("mainForm", mainForm);

        // /saibaikun/index.htmlを表示する
        return "/saibaikun/index";
    }


    /////////// いったん無視する
    //もどるボタン押下
    // @RequestMapping(params = "goBack", method = RequestMethod.POST)
    // public String back(@ModelAttribute SetupForm2 setupForm2, Model model) {

    //     return "index";
    // }
    /////////// いったん無視する


    //開始ボタン押下（新規）
    @RequestMapping(value = "/saibaikun", params = "start", method = RequestMethod.POST)
    public String regist(@ModelAttribute SetupForm3 setupForm3, Model model) {

        MainForm mainForm = new MainForm();

        //USER_IDを取得
        Integer userId = setUpService.getUserId();
        Integer daichoId = setUpService.getUserId();

        //T_USER
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(setupForm3.getUserName());
        userEntity.setUserId(userId);

        //T_SAIBAI_DAICHO
        SaibaiDaichoEntity saibaiDaichoEntity = new SaibaiDaichoEntity();
        saibaiDaichoEntity.setSaibaiDaichoId(daichoId);
        saibaiDaichoEntity.setCharacterId(setupForm3.getCharacterId());
        saibaiDaichoEntity.setUserId(userId);
        saibaiDaichoEntity.setSaibaiName(setupForm3.getSaibaikunName());

        //T_LOGIN
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        loginLogEntity.setUserId(userId);

        //T_ACTION_RRK
        ActionRrkEntity actionRrkEntity = new ActionRrkEntity();
        actionRrkEntity.setSaibaiDaichoId(daichoId);


        //T_USERへ登録
        boolean userResult = setUpService.addUserData(userEntity);
        //T_SAIBAI_DAICHOへ登録
        boolean saibaiResult = setUpService.addSaibaiData(saibaiDaichoEntity);
        //T_LOGINへ登録
        boolean loginResult = true;
        // boolean loginResult = setUpService.addLoginLogData(saibaiDaichoEntity);
        //T_ACTION_RRKへ登録
        boolean actionResult = true;
        // boolean actionResult = setUpService.addActionRrkData(saibaiDaichoEntity);

        if (!userResult || !saibaiResult || !loginResult || !actionResult ) {
            return "error";
        }


        // // viewにformをセット
        model.addAttribute("mainForm", mainForm);

        // /saibaikun/index.htmlを表示する
        return "/saibaikun/index";
    }

}