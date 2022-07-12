package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.CustomerAddForm;
import com.example.rental2.form.CustomerInformationForm;
import com.example.rental2.form.LoginForm;
import com.example.rental2.service.RegiService;

@Controller
public class MainController {

    @Autowired
    private RegiService regiService;

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String init(Model model) {

        LoginForm loginForm = new LoginForm();
        loginForm.setStaffID("");
        loginForm.setPassword("");
        model.addAttribute("loginForm", loginForm);

        return "login2";
    }

    // TOP画面
    @PostMapping("/afterLogin")
    public String afterExecute(@ModelAttribute LoginForm loginForm, Model model) {

        loginForm.setStaffID("");
        loginForm.setPassword("");

        // 商品リストを再度設定する
        // loginForm.setList(regiService.getItemList());

        // VeiwにFormをセットする
        model.addAttribute("loginForm", loginForm);

        // shop.htmlを表示する
        return "top";
    }

    // 顧客管理TOP画面
    @PostMapping("/main/customerInformation")
    public String customerExecute(@ModelAttribute CustomerInformationForm customerInformationForm, Model model) {

        // このなまえのHTMLに移動する
        return "customerInformation";

    }



    // 在庫管理管理画面
    @PostMapping("/main/InventoryControl")
    public String inventoryControlExecute(@ModelAttribute CustomerInformationForm customerInformationForm,
            Model model) {

        // このなまえのHTMLに移動する
        return "Inventorycontrol";

    }

}