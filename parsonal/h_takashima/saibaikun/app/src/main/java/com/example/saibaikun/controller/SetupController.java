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
import com.example.saibaikun.service.DateService;
import com.example.saibaikun.service.LoginService;
// import com.example.saibaikun.service.SaibaiService;
import com.example.saibaikun.service.SetupService;

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

    @Autowired
    SetupService setupService;

    @Autowired
    DateService dateService;

    // SaibaiService saibaiService;

    //つぎへボタン押下(index → index2)
    @RequestMapping(params = "to2nd", method = RequestMethod.POST)
    public String regist2(@ModelAttribute SetupForm setupForm, Model model) {

        SetupForm2 setupForm2 = new SetupForm2();

        //新規かログインかの判定をおこなう
        //入力されたかいぬしの名前で検索
        //該当データがあればuserIdにUSER_IDをセット、なければ0をセットする
        Integer userId = setupService.userCheck(setupForm.getUserName());
        System.out.println("★userid:"+userId);

        if ( userId > 0 ) {
            //ログイン用のキャラクターリストをセット
            //同時にさいばい台帳IDを取得しておく
            setupForm2.setCharacterList(loginService.getUserCharacterList(userId));
        }else{
            //新規用のキャラクターリストをセット
            setupForm2.setCharacterList(setupService.getCharacterList());
        }

        //hiddenで渡す
        //ログイン用
        setupForm2.setUserId(userId);
        //新規用
        setupForm2.setUserName(setupForm.getUserName()); 

        model.addAttribute("setupForm2", setupForm2);

        return "index2";
    }


    //新規のみ
    //つぎへボタン押下(index2 → index3)
    @RequestMapping(params = "to3rd", method = RequestMethod.POST)
    public String regist3(@ModelAttribute SetupForm2 setupForm2, Model model) {
        SetupForm3 setupForm3 = new SetupForm3();

        setupForm3.setUserName(setupForm2.getUserName());
        setupForm3.setCharacterId(setupForm2.getCharacterId());

        model.addAttribute("setupForm3", setupForm3);

        return "index3";
    }


    //登録済み
    //開始ボタン押下
    @RequestMapping(value = "/saibaikun", params = "login", method = RequestMethod.POST)
    public String start(@ModelAttribute SetupForm2 setupForm2, Model model) {

        //MainForm準備
        MainForm mainForm = new MainForm();

        //途中で取得したさいばい台帳IDを設定
        Integer saibaiDaichoId = setupForm2.getSaibaiDaichoId();
        //今日のアクション履歴を取得する
        String date = dateService.getDateYmd();


        //さいばい台帳からステータス情報を取得する
        //取得したステータス情報をMainFormに設定する
        mainForm.setStatus(loginService.getSaibaiStatus(saibaiDaichoId,date));

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

        //入力したデータを登録する
        //USER_ID_SEQを取得
        //SAIBAI_DAICHO_ID_SEQを取得する
        Integer userId = setupService.getUserId();
        Integer saibaiDaichoId = setupService.getDaichoId();

        //現在日付の取得
        String date = dateService.getDateYmd();
        System.out.println("★date------->:"+date);

        //T_USERへの登録準備
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(setupForm3.getUserName());
        userEntity.setUserId(userId);

        //T_SAIBAI_DAICHOへの登録準備
        SaibaiDaichoEntity saibaiDaichoEntity = new SaibaiDaichoEntity();
        saibaiDaichoEntity.setSaibaiDaichoId(saibaiDaichoId);
        saibaiDaichoEntity.setCharacterId(setupForm3.getCharacterId());
        saibaiDaichoEntity.setUserId(userId);
        saibaiDaichoEntity.setSaibaiName(setupForm3.getSaibaikunName());

        //T_LOGINへの登録準備
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        loginLogEntity.setUserId(userId);

        //T_ACTION_RRKへの登録準備
        ActionRrkEntity actionRrkEntity = new ActionRrkEntity();
        actionRrkEntity.setSaibaiDaichoId(saibaiDaichoId);
        actionRrkEntity.setActionYmd(date);


        //登録
        boolean result = setupService.execute(userEntity,saibaiDaichoEntity,actionRrkEntity);

        // boolean userResult = setUpService.addUserData(userEntity);
        // //T_SAIBAI_DAICHOへ登録
        // boolean saibaiResult = setUpService.addSaibaiData(saibaiDaichoEntity);
        // //T_LOGINへ登録
        // boolean loginResult = true;
        // // boolean loginResult = setUpService.addLoginLogData(saibaiDaichoEntity);
        // //T_ACTION_RRKへ登録
        // boolean actionResult = setUpService.addActionRrkData(actionRrkEntity);

        if (!result ) {
            return "error";
        }



        //メイン画面の初期表示


        // // viewにformをセット
        model.addAttribute("mainForm", mainForm);

        // /saibaikun/index.htmlを表示する
        return "/saibaikun/index";
    }

}