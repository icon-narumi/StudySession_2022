package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.entity.inventory.InventoryControlEntity;
import com.example.rental2.form.inventory.InventoryAddForm;
import com.example.rental2.form.inventory.InventoryDeleteForm;
import com.example.rental2.form.inventory.InventorySelectForm;
import com.example.rental2.form.inventory.InventoryUpdateForm;
import com.example.rental2.form.inventory.InventoryUpdateResultForm;
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

    // 在庫管理追加初期画面
    @GetMapping(value = "/inventoryControl/insert")
    public String inventoryInsertExcecute(Model model) {

        InventoryAddForm inventoryAddForm = new InventoryAddForm();

        // 以下の処理をすることによりセレクトボックスを追加
        inventoryAddForm.setBigGenreList(inventoryService.selectBigGenreAll());
        inventoryAddForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        inventoryAddForm.setStatusList(inventoryService.selectStatusAll());

        model.addAttribute("inventoryAddForm", inventoryAddForm);
        return "inventoryAddition";
    }

    // 在庫追加処理
    @RequestMapping(value = "/customerInformation/insert", params = "insert", method = RequestMethod.POST)
    public String inventoryInsertprosece(@ModelAttribute InventoryAddForm inventoryAddForm, Model model) {

        inventoryService.insertByInventory(inventoryAddForm.getTitleName(), inventoryAddForm.getBigGenreId(),
                inventoryAddForm.getSmallGenreId(), inventoryAddForm.getTurns(), inventoryAddForm.getStatusId());

        return "InventoryControl";
    }

    // 更新初期画面
    @GetMapping(value = "/inventoryControl/update")
    public String inventoryUpdateExcecute(Model model) {

        InventoryUpdateForm inventoryUpdateForm = new InventoryUpdateForm();

        // 全てのデータを取り出してセット
        inventoryUpdateForm.setInventoryList(inventoryService.selectFieldAll());

        model.addAttribute("inventoryUpdateForm", inventoryUpdateForm);
        return "inventoryUpdate";
    }

    // 更新データ選択
    @RequestMapping(value = "/inventoryControl/update", params = "select", method = RequestMethod.POST)
    public String updateProcess(@ModelAttribute InventoryUpdateForm inventoryUpdateForm, Model model) {

        InventoryUpdateResultForm inventoryUpdateResultForm = new InventoryUpdateResultForm();
        // 選択したデータを１行だけ取り出す。 取り出したデータをひとまず何かに入れておく(CustomerEntity customerInformationに)

        // 取り出したトランザクションテーブルデータをformにセットする。
        inventoryUpdateResultForm.setBigGenreList(inventoryService.selectBigGenreAll());
        inventoryUpdateResultForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        inventoryUpdateResultForm.setStatusList(inventoryService.selectStatusAll());
        try {
            InventoryControlEntity inventoryControlEntity = inventoryService
                    .updateSelectByInventory(inventoryUpdateForm.getId());

            // 取り出したマスターデータをformにセット
            inventoryUpdateResultForm.setTitleName(inventoryControlEntity.getTitleName());
            inventoryUpdateResultForm.setBigGenreId(inventoryControlEntity.getBigGenreId());
            inventoryUpdateResultForm.setSmallGenreId(inventoryControlEntity.getSmallGenreId());
            inventoryUpdateResultForm.setTurns(inventoryControlEntity.getTurns());
            inventoryUpdateResultForm.setStatusId(inventoryControlEntity.getStatusId());
            inventoryUpdateResultForm.setId(inventoryControlEntity.getId());

        } catch (NullPointerException e) {

            return "customerSelectError";
        }
        model.addAttribute("inventoryUpdateResultForm", inventoryUpdateResultForm);
        return "inventoryUpdateResult";
    }

    // 更新処理
    @RequestMapping(value = "/inventoryInformation/update/result", method = RequestMethod.POST)
    public String updateResult(@ModelAttribute InventoryUpdateResultForm inventoryUpdateResultForm, Model model) {

        inventoryService.updateByInventory(inventoryUpdateResultForm.getTitleName(),
                inventoryUpdateResultForm.getBigGenreId(), inventoryUpdateResultForm.getSmallGenreId(),
                inventoryUpdateResultForm.getTurns(), inventoryUpdateResultForm.getStatusId(),inventoryUpdateResultForm.getId());
        return "InventoryControl";
    }

    // 削除初期画面
    @GetMapping(value = "/inventoryControl/delete")
    public String inventoryDeleteExcecute(Model model) {

        InventoryDeleteForm inventoryDeleteForm = new InventoryDeleteForm();

        // 以下の処理をすることによりセレクトボックスを追加
        inventoryDeleteForm.setBigGenreList(inventoryService.selectBigGenreAll());
        inventoryDeleteForm.setSmallGenreList(inventoryService.selectSmallGenreAll());
        inventoryDeleteForm.setStatusList(inventoryService.selectStatusAll());

        // 全てのデータを取り出してセット
        inventoryDeleteForm.setInventoryList(inventoryService.selectFieldAll());

        model.addAttribute("inventoryDeleteForm", inventoryDeleteForm);

        return "inventoryDelete";
    }

    // 削除処理画面
    @RequestMapping(value = "/inventoryControl/delete", params = "delete", method = RequestMethod.POST)
    public String inventoryDeleteProsece(InventoryDeleteForm inventoryDeleteForm, Model model) {

        inventoryService.deleteByInventory(inventoryDeleteForm.getId());

        return "InventoryControl";
    }

}
