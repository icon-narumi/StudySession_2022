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

@Controller
public class ActionController {

    @Autowired
    DateService dateService;

    @Autowired
    ActionService actionService;

    // アクション：そうじ
    @RequestMapping(value = "/saibaikun/action",  params = "clean", method = RequestMethod.POST)
    public String actionExecute(@ModelAttribute MainForm mainForm, Model model) {

        ActionForm actionForm = new ActionForm();
        boolean result = true;

        Integer saibaiDaichoId = mainForm.getSaibaiDaichoId();
        String date = dateService.getDateYmd();


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

}