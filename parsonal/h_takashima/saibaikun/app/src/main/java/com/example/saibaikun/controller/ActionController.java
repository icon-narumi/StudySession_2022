package com.example.saibaikun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.saibaikun.form.ActionForm;
import com.example.saibaikun.form.MainForm;
import com.example.saibaikun.service.ActionService;
import com.example.saibaikun.service.DateService;
import com.example.saibaikun.service.LoginService;

@Controller
public class ActionController {

    @Autowired
    DateService dateService;
    @Autowired
    ActionService actionService;
    @Autowired
    LoginService loginService;



    // // アクション：ごはん
    // @RequestMapping(value = "/saibaikun/action",  params = "meal", method = RequestMethod.POST)
    // public String actionExecute1(@ModelAttribute MainForm mainForm, Model model) {

    //     ActionForm actionForm = new ActionForm();
    //     boolean result = true;

    //     Integer saibaiDaichoId = mainForm.getSaibaiDaichoId();

    //     //ごはん
    //     Integer actionCount1 = actionService.actionNumCheck(saibaiDaichoId,date);
    //     if(actionCount1 < 3) {
    //         actionCount1 = actionCount1+1;
    //         result = actionService.updateAction1(saibaiDaichoId,actionCount1,date);
    //     }
    //     if(!result){
    //         return "error";
    //     }

    //     actionForm.setSaibaiDaichoId(saibaiDaichoId);

    //     // // viewにformをセット
    //     model.addAttribute("actionForm", actionForm);

    //     // /saibaikun/action.htmlを表示する
    //     return "/saibaikun/action";
    // }

    // アクション：そうじ
    @RequestMapping(value = "/saibaikun/action",  params = "clean", method = RequestMethod.POST)
    public String actionExecute2(@ModelAttribute MainForm mainForm, Model model) {

        ActionForm actionForm = new ActionForm();
        String date = dateService.getDateYmd();
        boolean result = true;

        Integer saibaiDaichoId = mainForm.getSaibaiDaichoId();

        //そうじ
        Integer actionCount2 = actionService.actionNumCheck(saibaiDaichoId,date);
        if(actionCount2 < 3) {
            actionCount2 = actionCount2+1;
            result = actionService.updateAction2(saibaiDaichoId,actionCount2,date);
        }
        if(!result){
            return "error";
        }

        actionForm.setSaibaiDaichoId(saibaiDaichoId);

        // // viewにformをセット
        model.addAttribute("actionForm", actionForm);

        // /saibaikun/action.htmlを表示する
        return "/saibaikun/action";
    }

    // // アクション：あそび
    // @RequestMapping(value = "/saibaikun/action",  params = "play", method = RequestMethod.POST)
    // public String actionExecute3(@ModelAttribute MainForm mainForm, Model model) {

    //     ActionForm actionForm = new ActionForm();
    //     boolean result = true;

    //     Integer saibaiDaichoId = mainForm.getSaibaiDaichoId();

    //     //あそび
    //     Integer actionCount3 = actionService.actionNumCheck(saibaiDaichoId,date);
    //     if(actionCount3 < 1) {
    //         actionCount3 = actionCount3+1;
    //         result = actionService.updateAction3(saibaiDaichoId,actionCount3,date);
    //     }
    //     if(!result){
    //         return "error";
    //     }

    //     actionForm.setSaibaiDaichoId(saibaiDaichoId);

    //     // // viewにformをセット
    //     model.addAttribute("actionForm", actionForm);

    //     // /saibaikun/action.htmlを表示する
    //     return "/saibaikun/action";
    // }

    // アクション後 メイン画面
    @RequestMapping( value = "/saibaikun", params = "returnMain", method = RequestMethod.POST )
    public String returnMain(@ModelAttribute ActionForm actionForm, Model model) {

        MainForm mainForm = new MainForm();
        String date = dateService.getDateYmd();

        Integer saibaiDaichoId = actionForm.getSaibaiDaichoId();

        mainForm.setStatus(loginService.getSaibaiStatus(saibaiDaichoId,date));
        mainForm.setSaibaiDaichoId(saibaiDaichoId);

        // // viewにformをセット
        model.addAttribute("mainForm", mainForm);

        // /saibaikun/index.htmlを表示する
        return "/saibaikun/index";
    }

}