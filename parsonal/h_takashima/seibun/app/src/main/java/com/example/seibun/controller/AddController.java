package com.example.seibun.controller;

// import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.seibun.entity.IngredientEntity;
import com.example.seibun.form.AddForm;
import com.example.seibun.form.AdminForm;
import com.example.seibun.form.ConfirmForm;
import com.example.seibun.service.AddService;
import com.example.seibun.service.ResultService;

@Controller
public class AddController {

    @Autowired
    private ResultService resultService;

    //■=■=■= 新規追加ボタン押下 登録画面初期 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(value = "/admin/add", params = "add", method = RequestMethod.POST)
    public String edit(@ModelAttribute AdminForm adminForm, Model model) {

        AddForm addForm = new AddForm();

        // viewにformをセット
        model.addAttribute("addForm",addForm );

        return "admin/add";
    }



    //■=■=■= 確認ボタン押下 =■=■=■=■=■=■=■=■=■=■=■=■
    @PostMapping("/admin/add/confirm")
    public String execute(@ModelAttribute AddForm addForm, Model model) {

        ConfirmForm confirmForm = new ConfirmForm();

        //管理画面で選択したデータをinputへ設定
        confirmForm.setCategory(addForm.getCategory());
        confirmForm.setName(addForm.getName());
        confirmForm.setColor(addForm.getColor());
        confirmForm.setCalorie(addForm.getCalorie());
        confirmForm.setProtein(addForm.getProtein());

        // viewにformをセット
        model.addAttribute("confirmForm",confirmForm );

        return "admin/confirm";
    }



    //■=■=■= キャンセル（編集画面へ戻る）ボタン押下 登録画面 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(value = "/admin/add", params = "cancel", method = RequestMethod.POST)
    public String cancel(@ModelAttribute ConfirmForm confirmForm, Model model) {

        AddForm addForm = new AddForm();

        addForm.setCategory(confirmForm.getCategory());
        addForm.setName(confirmForm.getName());
        addForm.setColor(confirmForm.getColor());
        addForm.setCalorie(confirmForm.getCalorie());
        addForm.setProtein(confirmForm.getProtein());

        // viewにformをセット
        model.addAttribute("addForm",addForm );

        return "admin/add";
    }




    @Autowired
    private AddService addService;

    //■=■=■= 登録ボタン押下 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(value = "/admin", params = "addadd", method = RequestMethod.POST)
    public String complete(@ModelAttribute ConfirmForm confirmForm, Model model) {

        //データの更新処理
        String resultMessage = "登録が完了しました。";

        IngredientEntity editEntity = new IngredientEntity();

    
        editEntity.setCategory(confirmForm.getCategory());
        editEntity.setName(confirmForm.getName());
        editEntity.setColor(confirmForm.getColor());
        editEntity.setCalorie(confirmForm.getCalorie());
        editEntity.setProtein(confirmForm.getProtein());
    
        boolean result = addService.addData(editEntity);

        if(!result) {
            resultMessage = "登録に失敗しました。";
        }

        //登録結果の画面表示処理
        AdminForm adminForm = new AdminForm();

        String sortList = "category";
        String sort     = "asc";
        String search   = "";

        adminForm.setList(resultService.getResultList(search,sortList,sort));
        adminForm.setResultMessage(resultMessage);
        
        // viewにformをセット
        model.addAttribute("adminForm", adminForm);

        // /admin/index.htmlを表示する
        return "admin/index";
    }
}