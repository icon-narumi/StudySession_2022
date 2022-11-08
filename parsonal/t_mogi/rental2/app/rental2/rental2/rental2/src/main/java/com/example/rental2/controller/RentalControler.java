package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.rental.RentalSelectForm;
import com.example.rental2.service.InventoryService;

@Controller
public class RentalControler {

    @Autowired
    private InventoryService inventoryService;

    // レンタル検索画面
    @GetMapping("/main/rental/select")
    public String rentalProcess(Model model) {

        RentalSelectForm rentalSelectForm = new RentalSelectForm();
        // 検索条件を設定各項目
        rentalSelectForm.setBigGenreList(inventoryService.selectBigGenreAll());
        rentalSelectForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        rentalSelectForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("rentalSelectForm", rentalSelectForm);

        return "rentalSelect";

    }

    // レンタル在庫検索処理
    @RequestMapping(value = "/main/rental/select/result", params = "select", method = RequestMethod.POST)
    public String inventorySelectProsece(@ModelAttribute RentalSelectForm rentalSelectForm, Model model) {

        /// * inventorySelectForm.setInventoryList(inventoryService.selectAll());*/

        rentalSelectForm
                .setInventoryList(inventoryService.selectRentalField(rentalSelectForm.getTitleName(),
                        rentalSelectForm.getBigGenreId(), rentalSelectForm.getSmallGenreId(),
                        rentalSelectForm.getStatusId()));

        // 検索条件を設定各項目
        rentalSelectForm.setBigGenreList(inventoryService.selectBigGenreAll());
        rentalSelectForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        rentalSelectForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("rentalSelectForm", rentalSelectForm);

        return "rentalSelect";
    }

    // レンタル処理
    @RequestMapping(value = "/main/rental/select/result", params = "rental", method = RequestMethod.POST)
    public String inventoryRentalProsece(@ModelAttribute RentalSelectForm rentalSelectForm, Model model) {

        /// * inventorySelectForm.setInventoryList(inventoryService.selectAll());*/

        inventoryService.updateByRentalInventory(rentalSelectForm.getRentalSetList());

        return "rentaltop";
    }

}
