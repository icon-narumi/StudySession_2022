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

import com.example.petbird.entity.PetBirdEntity;
import com.example.petbird.entity.SpeciesEntity;
import com.example.petbird.form.ActionForm;
import com.example.petbird.form.DeleteForm;
import com.example.petbird.form.InputForm;
import com.example.petbird.form.LoginForm;
import com.example.petbird.form.PetBirdForm;
import com.example.petbird.form.SignUpForm;
import com.example.petbird.form.SuccessForm;
import com.example.petbird.form.UpdateForm;
import com.example.petbird.mapper.LoginMapper;
import com.example.petbird.mapper.PetBirdMapper;
import com.example.petbird.service.CastService;

@Controller
public class LoginController {

    @Autowired
    private PetBirdMapper petBirdMapper;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private CastService castService;

    // ログイン画面
    @PostMapping("/Login")
    public String loginExecute(@ModelAttribute LoginForm loginForm, BindingResult error, Model model) {
                
        model.addAttribute("loginForm", loginForm);
        return "login";     
    } 
    
    // ボタン選択
    @PostMapping("/action")
    public String actionExecute(@Valid @ModelAttribute LoginForm loginForm,BindingResult error, Model model) {

        ActionForm actionForm = new ActionForm();

        if(loginForm.getPassword() == null){

            model.addAttribute("loginForm", loginForm);
            return "login";

        }else if(loginForm.getPassword().equals("login")){
            
        }else{

            loginForm.setComment("パスワードが違います");
            model.addAttribute("loginForm", loginForm);
            return "login";
        }

            model.addAttribute("actionForm", actionForm);
            return "action";
    }

    @PostMapping("/action/login")
    public String actionLoginExecute(@ModelAttribute LoginForm loginForm,BindingResult error, Model model) {

        ActionForm actionForm = new ActionForm();

        model.addAttribute("actionForm", actionForm);
        return "action";
    }

    // 登録
    @PostMapping("/input")
    public String inputExecute(@ModelAttribute LoginForm loginForm, Model model) {

        InputForm inputForm = new InputForm();

        inputForm.setSpeciesList(petBirdMapper.speciesAll());
        inputForm.setSexList(petBirdMapper.sexAll());
        inputForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("inputForm", inputForm);
        return "input";
    }

    // 登録判定
    @PostMapping("/inputResult")
    public String inputResultExecute(@Valid @ModelAttribute InputForm inputForm, BindingResult error, Model model) {

    try{
        // エラーだったら
        if (error.hasErrors()) {
            
            inputForm.setSpeciesList(petBirdMapper.speciesAll());
            inputForm.setSexList(petBirdMapper.sexAll());
            inputForm.setColorList(petBirdMapper.colorAll());

            model.addAttribute("inputForm", inputForm);
            
            return "input";
        }

        // エラーが無かったら登録
        loginMapper.inputPetBird(inputForm.getSpecies(), inputForm.getSex(), inputForm.getColor(), inputForm.getLife(),
                inputForm.getCount(), inputForm.getPrice());
        inputForm.setComment("登録しました");
        
    }catch(Exception e){
            
            inputForm.setSpeciesList(petBirdMapper.speciesAll());
            inputForm.setSexList(petBirdMapper.sexAll());
            inputForm.setColorList(petBirdMapper.colorAll());

            inputForm.setComment("既に登録されている鳥です");
            
            return "input";

        }

        model.addAttribute("inputForm", inputForm);
        return "inputResult";
    }

    // 新規登録
    @RequestMapping(value = "/inputResult", params = "signUp", method = RequestMethod.POST)
    public String signUp(@RequestParam String signUp, Model model) {

        SignUpForm signUpForm = new SignUpForm();

        model.addAttribute("signUpForm", signUpForm);
        return "signUp";
    }

    // 新規登録結果
    @PostMapping("/signUpResult")
    public String signUpResultExecute(@Valid @ModelAttribute SignUpForm signUpForm, BindingResult error, Model model) {

        InputForm inputForm  = new InputForm();

        inputForm.setSpeciesList(petBirdMapper.speciesAll());
        inputForm.setSexList(petBirdMapper.sexAll());
        inputForm.setColorList(petBirdMapper.colorAll());

        // バリデーションのメッセージ出せない
        try {
            
                // エラーだったら
                if (error.hasErrors()) {

                    model.addAttribute("signUpForm", signUpForm);
                    return "signUp";
                    // エラーじゃなかったら
                } else {

                    loginMapper.inputSpecies(signUpForm.getInputSpecies());
                    signUpForm.setComment("新規登録しました");

                    //新規登録した鳥を登録画面に戻った際に予めセットしておく
                    //種類テーブルより対象の鳥のIDを抽出
                    SpeciesEntity speciesEntity = loginMapper.speciesId(signUpForm.getInputSpecies());
                    
                    //List<SpeciesEntity>型に変換してセット
                    inputForm.setSpeciesList(castService.listSpeciesChange(speciesEntity.getSpeciesname()));

                    model.addAttribute("inputForm", inputForm);
                }
              
        // もし種類名で一意制約違反が出たらNGへ,無かったら登録処理へ進む
        } catch (Exception e) {
            UpdateForm updateForm = new UpdateForm();
            System.out.println(e);
            System.out.println("!!一意制約違反発生");
            updateForm.setComment("既に登録されている鳥です");

            model.addAttribute("updateForm", updateForm);
            return "ng";
        }

        model.addAttribute("signUpForm", signUpForm);
        return "signUp";
    }

    // 更新対象選択
    @PostMapping("/updateSpecies")
    public String updateSpeciesExecute(@ModelAttribute UpdateForm updateForm, Model model) {

        updateForm.setSpeciesList(petBirdMapper.speciesAll());
        updateForm.setSexList(petBirdMapper.sexAll());
        updateForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("updateForm", updateForm);
        return "updateSpecies";
    }

    // 更新
    @PostMapping("/update")
    public String updateExecute(@ModelAttribute UpdateForm updateForm, Model model) {

        try{
            updateForm.setSpeciesList(petBirdMapper.speciesAll());
            updateForm.setSexList(petBirdMapper.sexAll());
            updateForm.setColorList(petBirdMapper.colorAll());

            PetBirdEntity petBirdEntity = loginMapper.updateSelectPetBird(updateForm.getSpecies(), updateForm.getSex(),updateForm.getColor());

            updateForm.setSpecies(petBirdEntity.getSpecies());
            updateForm.setSex(petBirdEntity.getSex());
            updateForm.setColor(petBirdEntity.getColor());
            updateForm.setLife(petBirdEntity.getLife());
            updateForm.setCount(petBirdEntity.getCount());
            updateForm.setPrice(petBirdEntity.getPrice());

        }catch(Exception e){
        
            updateForm.setComment("対象がありません");
            return "ng";
        
        }

        model.addAttribute("updateForm", updateForm);
        return "update";
    }

    @PostMapping("/updateResult")
    public String updateresultExecute(@ModelAttribute UpdateForm updateForm, Model model) {

        loginMapper.updatePetBird(updateForm.getSpecies(), updateForm.getSex(), updateForm.getColor(),
                updateForm.getLife(), updateForm.getCount(), updateForm.getPrice());
        updateForm.setComment("更新しました☆");

        model.addAttribute("updateForm", updateForm);
        return "updateResult";
    }

    // 削除
    @PostMapping("/delete")
    public String deleteExecute(@ModelAttribute UpdateForm updateForm, Model model) {

        DeleteForm deleteForm = new DeleteForm();

        deleteForm.setSpeciesList(petBirdMapper.speciesAll());
        deleteForm.setSexList(petBirdMapper.sexAll());
        deleteForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("deleteForm", deleteForm);
        return "delete";
    }

    // 完了
    @PostMapping("/success")
    public String successExecute(@ModelAttribute DeleteForm deleteForm, Model model) {
        
        UpdateForm updateForm = new UpdateForm();
        PetBirdEntity petBirdEntity = loginMapper.updateSelectPetBird(deleteForm.getSpecies(), deleteForm.getSex(),deleteForm.getColor());
    
        if(petBirdEntity==null){
           
            updateForm.setComment("対象がありません");
            model.addAttribute("updateForm", updateForm);
            return "ng";

        }else{

            loginMapper.deletePetBird(petBirdEntity.getSpecies(),petBirdEntity.getSex(),petBirdEntity.getColor());
            deleteForm.setComment("削除しました");
            
        }
        model.addAttribute("deleteForm", deleteForm);

        return "success";

    }
}
