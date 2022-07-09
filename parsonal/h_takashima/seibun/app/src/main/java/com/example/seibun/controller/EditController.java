package com.example.seibun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.seibun.entity.IngredientEntity;
import com.example.seibun.form.AdminForm;
import com.example.seibun.form.ConfirmForm;
import com.example.seibun.form.EditForm;
import com.example.seibun.service.EditService;
import com.example.seibun.service.ResultService;

@Controller
public class EditController {

    @Autowired
    private ResultService resultService;

    //■=■=■= 編集ボタン押下 編集画面初期 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(value = "/admin/edit", params = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute AdminForm adminForm, Model model) {

        //選択した行のキー項目を取得
        String selectName = adminForm.getSelectName();
        //
        EditForm editForm = new EditForm();
        //キー項目で取得したデータをeditEntityに格納する
        IngredientEntity editEntity = resultService.getSelectList(selectName);

        //editEntityからデータを取り出してinputに設定する
        editForm.setCategory(editEntity.getCategory());
        editForm.setName(editEntity.getName());
        editForm.setColor(editEntity.getColor());
        editForm.setCalorie(editEntity.getCalorie());
        editForm.setProtein(editEntity.getProtein());

        // viewにformをセット
        model.addAttribute("editForm",editForm );

        return "admin/edit";
    }



    //■=■=■= 確認ボタン押下 =■=■=■=■=■=■=■=■=■=■=■=■
    @PostMapping("/admin/edit/confirm")
    public String execute(@ModelAttribute EditForm editForm, Model model) {

        ConfirmForm confirmForm = new ConfirmForm();

        //管理画面で選択したデータをinputへ設定
        confirmForm.setPcategory(editForm.getPcategory());
        confirmForm.setPname(editForm.getPname());
        confirmForm.setCategory(editForm.getCategory());
        confirmForm.setName(editForm.getName());
        confirmForm.setColor(editForm.getColor());
        confirmForm.setCalorie(editForm.getCalorie());
        confirmForm.setProtein(editForm.getProtein());

        // viewにformをセット
        model.addAttribute("confirmForm",confirmForm );

        return "admin/confirm";
    }



    //■=■=■= キャンセル（編集画面へ戻る）ボタン押下 編集画面 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(value = "/admin/edit", params = "cancel", method = RequestMethod.POST)
    public String cancel(@ModelAttribute ConfirmForm confirmForm, Model model) {

        EditForm editForm = new EditForm();

        editForm.setPcategory(confirmForm.getPcategory());
        editForm.setPname(confirmForm.getPname());
        editForm.setCategory(confirmForm.getCategory());
        editForm.setName(confirmForm.getName());
        editForm.setColor(confirmForm.getColor());
        editForm.setCalorie(confirmForm.getCalorie());
        editForm.setProtein(confirmForm.getProtein());

        // viewにformをセット
        model.addAttribute("editForm",editForm );

        return "admin/edit";
    }




    //■=■=■= 登録ボタン押下 =■=■=■=■=■=■=■=■=■=■=■=■
    // @PostMapping("/admin")
    @RequestMapping(value = "/admin", params = "editadd", method = RequestMethod.POST)
    public String complete(@ModelAttribute ConfirmForm confirmForm, Model model) {

        //データの更新処理
        String resultMessage = "登録が完了しました。";

        IngredientEntity editEntity = new IngredientEntity();

        String Pcat  = confirmForm.getPcategory();
        String Pname = confirmForm.getPname();
    
        editEntity.setCategory(confirmForm.getCategory());
        editEntity.setName(confirmForm.getName());
        editEntity.setColor(confirmForm.getColor());
        editEntity.setCalorie(confirmForm.getCalorie());
        editEntity.setProtein(confirmForm.getProtein());
    
        boolean result = editService.editaddData(editEntity,Pcat,Pname);

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



    @Autowired
    private EditService editService;

    //■=■=■= 削除ボタン押下 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute AdminForm adminForm, Model model) {

        String resultMessage = "削除が完了しました。";
        String selectName = adminForm.getSelectName();

        //キー項目で取得したデータをeditEntityに格納する
        boolean result = editService.deleteData(selectName);
        if(!result) {
            resultMessage = "削除に失敗しました。";
        }

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