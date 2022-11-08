package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.returnItem.ReturnSelectForm;
import com.example.rental2.service.InventoryService;

@Controller
public class ReturnControler {

    @Autowired
    private InventoryService inventoryService;

    // 返却検索画面
    @GetMapping("/main/return/select")
    public String rentalProcess(Model model) {

        ReturnSelectForm returnSelectForm = new ReturnSelectForm();
        // 検索条件を設定各項目
        returnSelectForm.setBigGenreList(inventoryService.selectBigGenreAll());
        returnSelectForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        returnSelectForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("returnSelectForm", returnSelectForm);

        return "returnSelect";
    }

    // 返却在庫検索処理
    @RequestMapping(value = "/main/return/select/result", params = "select", method = RequestMethod.POST)
    public String inventorySelectProsece(@ModelAttribute ReturnSelectForm returnSelectForm, Model model) {

        /// * inventorySelectForm.setInventoryList(inventoryService.selectAll());*/

        returnSelectForm
                .setInventoryList(inventoryService.selectReturnField(returnSelectForm.getTitleName(),
                        returnSelectForm.getBigGenreId(), returnSelectForm.getSmallGenreId(),
                        returnSelectForm.getStatusId()));

        // 検索条件を設定各項目
        returnSelectForm.setBigGenreList(inventoryService.selectBigGenreAll());
        returnSelectForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        returnSelectForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("returnSelectForm", returnSelectForm);

        return "returnSelect";
    }

    // 在庫返却処理
    @RequestMapping(value = "/main/return/select/result", params = "return", method = RequestMethod.POST)
    public String inventoryReturnlProsece(@ModelAttribute ReturnSelectForm returnSelectForm, Model model) {

        /// * inventorySelectForm.setInventoryList(inventoryService.selectAll());*/

        inventoryService.updateByReturnInventory(returnSelectForm.getRentalSetList());

        return "returnTop";
    }

}