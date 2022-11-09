package com.example.petbird.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.petbird.bean.UnitIdBean;
import com.example.petbird.entity_master.SpeciesEntity;
import com.example.petbird.entity_petbird.PetBirdEntity;
import com.example.petbird.form_login.ActionForm;
import com.example.petbird.form_login.DeleteAllListForm;
import com.example.petbird.form_login.DeleteForm;
import com.example.petbird.form_login.DeleteResultForm;
import com.example.petbird.form_login.InputForm;
import com.example.petbird.form_login.LoginForm;
import com.example.petbird.form_login.NgForm;
import com.example.petbird.form_login.SignUpForm;
import com.example.petbird.form_login.UpdateForm;
import com.example.petbird.form_login.UpdateResultForm;
import com.example.petbird.form_login.UpdateSpeciesAllListForm;
import com.example.petbird.form_login.UpdateSpeciesForm;
import com.example.petbird.mapper.M_MasterMapper;
import com.example.petbird.mapper.T_LoginMapper;
import com.example.petbird.service.MakeStrPetBirdBeanService;

@Controller
public class LoginController {
    @Autowired
    private M_MasterMapper masterMapper;
    @Autowired
    private T_LoginMapper loginMapper;
    @Autowired
    private MakeStrPetBirdBeanService makeStrPetBirdBeanService;
    
    @PostMapping("/petbird/login")
    public String petbirdLoginExecute(@ModelAttribute LoginForm loginForm, BindingResult error, Model model) {
                
        model.addAttribute("loginForm", loginForm);
        return "login";     
    }
    @PostMapping("/petbird/login/action")
    public String petbirdLoginActironExecute(@ModelAttribute LoginForm loginForm, BindingResult error, Model model) {
        
        if(loginForm.getPassword() == null){

            model.addAttribute("loginForm", loginForm);
            return "login";

        }else if(loginForm.getPassword().equals("login")){
            
        }else{

            loginForm.setComment("パスワードが違います");
            model.addAttribute("loginForm", loginForm);
            return "login";
        }

        model.addAttribute("loginForm", loginForm);
        return "l_action";     
    }
    
    @PostMapping("/action")
    public String actironExecute(@ModelAttribute ActionForm actionForm, BindingResult error, Model model) {
                
        model.addAttribute("actionForm", actionForm);
        return "l_action";     
    }

    @PostMapping("/action/input")
    public String actironInputExecute(@ModelAttribute InputForm inputForm, BindingResult error, Model model) {
                
        inputForm.setSpeciesList(masterMapper.speciesAll());
        inputForm.setSexList(masterMapper.sexAll());
        inputForm.setColorList(masterMapper.colorAll());

        model.addAttribute("inputForm", inputForm);
        return "l_input";     
    }

    // 新規登録
    @RequestMapping(value = "/input/inputResult", params = "signUp", method = RequestMethod.POST)
    public String signUp(@RequestParam String signUp, Model model) {

        SignUpForm signUpForm = new SignUpForm();

        model.addAttribute("signUpForm", signUpForm);
        return "l_signUp";
    }

    @PostMapping("/input/signUp/signUpResult")
    public String inputSignUpSignUpResultExecute(@Valid @ModelAttribute SignUpForm signUpForm, BindingResult error, Model model) {
        
        InputForm inputForm  = new InputForm();

        inputForm.setSpeciesList(masterMapper.speciesAll());
        inputForm.setSexList(masterMapper.sexAll());
        inputForm.setColorList(masterMapper.colorAll());

        try {
            
                // エラーだったら
                if (error.hasErrors()) {

                    model.addAttribute("signUpForm", signUpForm);
                    return "l_signUp";
                    // エラーじゃなかったら
                } else {

                    loginMapper.inputSpecies(signUpForm.getInputSpecies());
                    signUpForm.setComment("新規登録しました");

                    //新規登録した鳥を登録画面に戻った際に予めセットしておく
                    //種類テーブルより対象の鳥のIDを抽出
                    SpeciesEntity speciesEntity = loginMapper.speciesId(signUpForm.getInputSpecies());
                    
                    //List<SpeciesEntity>型に変換してセット
                    inputForm.setSpeciesList(makeStrPetBirdBeanService.listSpeciesChange(speciesEntity.getSpeciesname()));
                    model.addAttribute("inputForm", inputForm);
                }
            } catch (Exception e) {
                NgForm ngForm = new NgForm(); 
                System.out.println(e);
                System.out.println("!!一意制約違反発生");
                ngForm.setComment("既に登録されている鳥です");
    
                model.addAttribute("ngForm", ngForm);
                return "l_ng";
            }
                
        model.addAttribute("signUpForm", signUpForm);
        return "l_signUp";     
    }

    @PostMapping("/input/inputResult")
    public String inputInputResultExecute(@Valid @ModelAttribute InputForm inputForm, BindingResult error, Model model) {
                
        try{
            // エラーだったら
            if (error.hasErrors()) {
                
                inputForm.setSpeciesList(masterMapper.speciesAll());
                inputForm.setSexList(masterMapper.sexAll());
                inputForm.setColorList(masterMapper.colorAll());
    
                model.addAttribute("inputForm", inputForm);
                
                return "l_input";
            }
    
            // エラーが無かったら登録
            loginMapper.inputPetBird(inputForm.getSpecies(), inputForm.getSex(), inputForm.getColor(), inputForm.getLife(),
                    inputForm.getCount(), inputForm.getPrice());
            inputForm.setComment("登録しました");
            
        }catch(Exception e){
                
                inputForm.setSpeciesList(masterMapper.speciesAll());
                inputForm.setSexList(masterMapper.sexAll());
                inputForm.setColorList(masterMapper.colorAll());
    
                inputForm.setComment("既に登録されている鳥です");
                
                return "l_input";
    
            }

        model.addAttribute("inputForm", inputForm);
        return "l_inputResult";     
    }

    @PostMapping("/action/updateSpecies")
    public String actionUpdateSpeciesExecute(@ModelAttribute UpdateSpeciesForm updateSpeciesForm, BindingResult error, Model model) {
                
        updateSpeciesForm.setSpeciesList(masterMapper.speciesAll());
        updateSpeciesForm.setSexList(masterMapper.sexAll());
        updateSpeciesForm.setColorList(masterMapper.colorAll());

        model.addAttribute("updateSpeciesForm", updateSpeciesForm);
        return "l_updateSpecies";     
    }
    
    //鳥情報検索一覧
    @RequestMapping(value = "/action/updateSpecies/update", params = "updateSpeciesAllList", method = RequestMethod.POST)
    public String updateSpeciesAllList(@ModelAttribute UpdateSpeciesForm updateSpeciesForm, Model model) {
    
        UpdateSpeciesAllListForm updateSpeciesAllListForm =new UpdateSpeciesAllListForm();

        updateSpeciesAllListForm.setSpecies(updateSpeciesForm.getSpecies());
        updateSpeciesAllListForm.setSex(updateSpeciesForm.getSex());
        updateSpeciesAllListForm.setColor(updateSpeciesForm.getColor());

        updateSpeciesAllListForm.setSpeciesList(masterMapper.speciesAll());
        updateSpeciesAllListForm.setSexList(masterMapper.sexAll());
        updateSpeciesAllListForm.setColorList(masterMapper.colorAll());
        
       
        List<UnitIdBean> unitIdBeanList = loginMapper.petBirdAllList();
        updateSpeciesAllListForm.setStrPetBirdBeanList(makeStrPetBirdBeanService.strChangeList(unitIdBeanList));

        model.addAttribute("updateSpeciesAllListForm", updateSpeciesAllListForm);
        return "l_updateSpeciesAllList";     
    }

    @PostMapping("/action/updateSpecies/update")
    public String actionUpdateSpeciesUpdateExecute(@ModelAttribute UpdateSpeciesForm updateSpeciesForm, BindingResult error, Model model) {
            UpdateForm updateForm = new UpdateForm();

            try{
                updateForm.setSpeciesList(masterMapper.speciesAll());
                updateForm.setSexList(masterMapper.sexAll());
                updateForm.setColorList(masterMapper.colorAll());
    
                PetBirdEntity petBirdEntity = loginMapper.updateSelectPetBird(updateSpeciesForm.getSpecies(), updateSpeciesForm.getSex(),updateSpeciesForm.getColor());
    
                updateForm.setSpecies(petBirdEntity.getSpecies());
                updateForm.setSex(petBirdEntity.getSex());
                updateForm.setColor(petBirdEntity.getColor());
                updateForm.setLife(petBirdEntity.getLife());
                updateForm.setCount(petBirdEntity.getCount());
                updateForm.setPrice(petBirdEntity.getPrice());
    
            }catch(Exception e){
                NgForm ngForm = new NgForm();
                ngForm.setComment("対象がありません");
                
                model.addAttribute("ngForm", ngForm);
                return "l_ng";
            }

        model.addAttribute("updateForm", updateForm);
        return "l_update";     
    }

    @PostMapping("/action/updateSpecies/update/updateResult")
    public String actionUpdateSpeciesUpdateExecute(@ModelAttribute UpdateForm updateForm, BindingResult error, Model model) {
        
        UpdateResultForm updateResultForm = new UpdateResultForm();

        loginMapper.updatePetBird(updateForm.getSpecies(), updateForm.getSex(), updateForm.getColor(),
                updateForm.getLife(), updateForm.getCount(), updateForm.getPrice());
                updateResultForm.setComment("更新しました☆");

        model.addAttribute("updateResultForm", updateResultForm);
        return "l_updateResult";     
    }

    @PostMapping("/action/delete")
    public String actionDeleteExecute(@ModelAttribute DeleteForm deleteForm, BindingResult error, Model model) {
        
        deleteForm.setSpeciesList(masterMapper.speciesAll());
        deleteForm.setSexList(masterMapper.sexAll());
        deleteForm.setColorList(masterMapper.colorAll());

        model.addAttribute("deleteForm", deleteForm);
        return "l_delete";     
    }

    @RequestMapping(value = "/action/delete", params = "deleteAllList", method = RequestMethod.POST)
    public String deleteAllList(@ModelAttribute DeleteForm deleteForm, Model model) {
    
        DeleteAllListForm deleteAllListForm =new DeleteAllListForm();

        deleteAllListForm.setSpecies(deleteForm.getSpecies());
        deleteAllListForm.setSex(deleteForm.getSex());
        deleteAllListForm.setColor(deleteForm.getColor());

        deleteAllListForm.setSpeciesList(masterMapper.speciesAll());
        deleteAllListForm.setSexList(masterMapper.sexAll());
        deleteAllListForm.setColorList(masterMapper.colorAll());
       
        List<UnitIdBean> unitIdBeanList = loginMapper.petBirdAllList();
        deleteAllListForm.setStrPetBirdBeanList(makeStrPetBirdBeanService.strChangeList(unitIdBeanList));

        model.addAttribute("deleteAllListForm", deleteAllListForm);
        return "l_deleteAllList";     
    }

    @PostMapping("/action/delete/deleteResult")
    public String actionDeleteDeleteResultExecute(@ModelAttribute DeleteForm deleteForm, BindingResult error, Model model) {
        
        DeleteResultForm deleteResultForm = new DeleteResultForm();

        PetBirdEntity petBirdEntity = loginMapper.updateSelectPetBird(deleteForm.getSpecies(), deleteForm.getSex(),deleteForm.getColor());
    
        if(petBirdEntity==null){
            NgForm ngForm = new NgForm();
            ngForm.setComment("対象がありません");
            model.addAttribute("ngForm", ngForm);
            return "l_ng";

        }else{

            loginMapper.deletePetBird(petBirdEntity.getSpecies(),petBirdEntity.getSex(),petBirdEntity.getColor());
            
            deleteResultForm.setComment("削除しました");
            
        }

        model.addAttribute("deleteResultForm", deleteResultForm);
        return "l_deleteResult";     
    }
    

}
