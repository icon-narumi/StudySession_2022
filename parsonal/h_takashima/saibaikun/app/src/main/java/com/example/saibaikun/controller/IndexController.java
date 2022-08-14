package com.example.saibaikun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.saibaikun.bean.GetLoginInfoBean;
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
public class IndexController {

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

    //つぎへボタン押下（index → index2）
    @RequestMapping(params = "to2nd", method = RequestMethod.POST)
    public String regist2(@ModelAttribute SetupForm setupForm, Model model) {

        SetupForm2 setupForm2 = new SetupForm2();

        //新規かログインかの判定をおこなう
        //入力されたかいぬしの名前で検索
        //該当データがあればuserIdにUSER_IDをセット、なければ0をセットする
        Integer userId = setupService.userCheck(setupForm.getUserName());
        setupForm2.setCharacterList(setupService.getCharacterList());

        if ( userId > 0 ) {
            userId = setupService.userCheck2(setupForm.getUserName());

            //ログイン用のキャラクターリストをセット
            //同時にさいばい台帳IDを取得しておく
            List<GetLoginInfoBean> characterList = loginService.getUserCharacterList(userId);
            setupForm2.setCharacterList(characterList);
            setupForm2.setCharacterChecked(characterList.get(0).getCharacterId());

        }else{
            //新規用のキャラクターリストをセット
            setupForm2.setCharacterChecked(1);            
        }

        //hiddenで渡す
        //ログイン用
        setupForm2.setUserId(userId);
        //新規用
        setupForm2.setUserName(setupForm.getUserName()); 

        model.addAttribute("setupForm2", setupForm2);

        return "index2";
    }


    //つぎへボタン押下（新規）（index2 → index3）
    @RequestMapping(params = "to3rd", method = RequestMethod.POST)
    public String regist3(@ModelAttribute SetupForm2 setupForm2, Model model) {
    
        SetupForm3 setupForm3 = new SetupForm3();

        setupForm3.setUserName(setupForm2.getUserName());
        setupForm3.setCharacterId(setupForm2.getCharacterId());

        model.addAttribute("setupForm3", setupForm3);

        return "index3";
    }

    //もどる押下（index2 → index）
    @RequestMapping(params = "goBack", method = RequestMethod.POST)
    public String back(@ModelAttribute SetupForm2 setupForm2, Model model) {

        SetupForm setupForm = new SetupForm();

        setupForm.setUserName(setupForm2.getUserName());

        model.addAttribute("setupForm", setupForm);

        return "index";
    }

    //開始ボタン押下（新規）（index3 → main）
    @RequestMapping(value = "/saibaikun", params = "start", method = RequestMethod.POST)
    public String regist(@ModelAttribute SetupForm3 setupForm3, Model model) {

        MainForm mainForm = new MainForm();
    
        //本日（yyyy/MM/dd）
        String date = dateService.getDateYmd();
        //本日（yyyy/MM/dd HH:mm:ss.SSS）
        String datetime = dateService.getTimestamp();

        //入力したデータを登録する
        //USER_ID_SEQを取得
        //SAIBAI_DAICHO_ID_SEQを取得する
        Integer userId = setupService.getUserId();
        Integer saibaiDaichoId = setupService.getDaichoId();

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
        loginLogEntity.setRrkNo(1);
        loginLogEntity.setLoginTm(datetime);
        loginLogEntity.setZenkaiLoginTm(datetime);

        //T_ACTION_RRKへの登録準備
        ActionRrkEntity actionRrkEntity = new ActionRrkEntity();
        actionRrkEntity.setSaibaiDaichoId(saibaiDaichoId);
        actionRrkEntity.setActionYmd(date);


        //登録
        boolean result = setupService.execute(userEntity,saibaiDaichoEntity,loginLogEntity,actionRrkEntity);

        if (!result ) {
            return "error";
        }

        //メイン画面の初期表示
        //さいばい台帳からステータス情報を取得する
        //取得したステータス情報をMainFormに設定する
        mainForm.setStatus(loginService.getSaibaiStatus(saibaiDaichoId,date));
        mainForm.setSaibaiDaichoId(saibaiDaichoId);

        model.addAttribute("mainForm", mainForm);

        return "/saibaikun/index";
    }

    //もどるボタン押下（新規）（index3 → index2）
    @RequestMapping(params = "goBack2", method = RequestMethod.POST)
    public String back2(@ModelAttribute SetupForm3 setupForm3, Model model) {

        SetupForm2 setupForm2 = new SetupForm2();

        setupForm2.setCharacterList(setupService.getCharacterList());
        setupForm2.setUserName(setupForm3.getUserName());
        setupForm2.setCharacterChecked(setupForm3.getCharacterId());
        setupForm2.setUserId(0);

        model.addAttribute("setupForm2", setupForm2);

        return "index2";
    }
}