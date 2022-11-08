package com.example.rental2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.LoginForm;
import com.example.rental2.form.customer.CustomerInformationForm;

@Controller
public class MainController {

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

    // 在庫管理管理TOP画面
    @PostMapping("/main/inventoryControl")
    public String inventoryControlExecute(@ModelAttribute CustomerInformationForm customerInformationForm,
            Model model) {

        // このなまえのHTMLに移動する
        return "InventoryControl";

    }

    // レンタル管理TOP画面
    @PostMapping("/main/rental")
    public String rentalExecute() {

        // レンタルTOPに移動
        return "rentaltop";
    }

    // 返却管理TOP画面
    @PostMapping("/main/return")
    public String returnExcecute() {

        // 返却TOPに移動
        return "returnTop";
    }
}