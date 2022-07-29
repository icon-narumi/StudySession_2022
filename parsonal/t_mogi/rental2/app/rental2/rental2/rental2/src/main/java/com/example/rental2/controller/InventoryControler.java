package com.example.rental2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rental2.form.InventorySelectForm;

@Controller
public class InventoryControler {

    // 在庫一覧表示
    @GetMapping("/inventoryControl/select")
    public String inventoryAdditionExcecute(Model model) {

        return "InventorySelect";
    }

    //在庫検索処理
    @RequestMapping("/inventoryControl/select/result")
    public String inventoryAdditionprosece(@ModelAttribute InventorySelectForm inventorySelectForm){

        

        return "";
    }
}
