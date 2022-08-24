package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.inventory.InventoryAddForm;
import com.example.rental2.form.inventory.InventorySelectForm;
import com.example.rental2.service.InventoryService;


//在庫管理用コントローラー
@Controller
public class InventoryControler {

    @Autowired
    private InventoryService inventoryService;

    // 在庫一覧画面初期画面
    @GetMapping("/inventoryControl/select")
    public String inventorySelectExcecute(Model model) {

        InventorySelectForm inventorySelectForm = new InventorySelectForm();
        // 検索条件を設定各項目
        inventorySelectForm.setBigGenreList(inventoryService.selectBigGenreAll());
        inventorySelectForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        inventorySelectForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("inventorySelectForm", inventorySelectForm);
        return "InventorySelect";
    }

    // 在庫検索処理
    @RequestMapping(value = "/inventoryControl/select/result", params = "select", method = RequestMethod.POST)
    public String inventorySelectprosece(@ModelAttribute InventorySelectForm inventorySelectForm, Model model) {

        /// * inventorySelectForm.setInventoryList(inventoryService.selectAll());*/

        inventorySelectForm
                .setInventoryList(inventoryService.selectByInventoryInformation(inventorySelectForm.getTitleName(),
                        inventorySelectForm.getBigGenreId(), inventorySelectForm.getSmallGenreId()));

        // 検索条件を設定各項目
        inventorySelectForm.setBigGenreList(inventoryService.selectBigGenreAll());
        inventorySelectForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        inventorySelectForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("inventorySelectForm", inventorySelectForm);

        return "InventorySelect";
    }

    //在庫管理追加初期画面
    @GetMapping(value="/inventoryControl/insert")
    public String inventoryInsertExcecute(Model model) {
        
        InventoryAddForm inventoryAddForm = new InventoryAddForm();

        inventoryAddForm.setBigGenreList(inventoryService.selectBigGenreAll());
        inventoryAddForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        inventoryAddForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("inventoryAddForm",inventoryAddForm);
        return "inventoryAddition";
    }
 
    //在庫追加処理
    @RequestMapping(value = "/inventoryControl/insert" , params="insert",method = RequestMethod.POST)
    public void inventoryInsertprosece(@ModelAttribute InventoryAddForm inventoryAddForm,Model model){

        

    }
}
