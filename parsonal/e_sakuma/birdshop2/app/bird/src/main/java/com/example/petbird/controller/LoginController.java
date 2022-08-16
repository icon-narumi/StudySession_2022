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
import com.example.petbird.form.SuccessForm;
import com.example.petbird.form.UpdateForm;
import com.example.petbird.mapper.LoginMapper;
import com.example.petbird.mapper.PetBirdMapper;

@Controller
public class LoginController {

    @Autowired
    private PetBirdMapper petBirdMapper;
    @Autowired
    private LoginMapper loginMapper;
    //ログイン画面
    @PostMapping("/Login")
    public String loginExecute(@ModelAttribute PetBirdForm petBirdForm, Model model) {

        LoginForm loginForm = new LoginForm();
        
        model.addAttribute("loginForm", loginForm);
        return "login";
    }
    //ボタン選択
    @PostMapping("/action")
    public String actionExecute(@ModelAttribute LoginForm loginForm, Model model) {

        ActionForm actionForm = new ActionForm();

        model.addAttribute("actionForm", actionForm);
        return "action";
    }
    //登録
    @PostMapping("/input")
    public String inputExecute(@ModelAttribute ActionForm actionForm, Model model) {

        InputForm inputForm = new InputForm();

        inputForm.setSpeciesList(petBirdMapper.speciesAll());
        inputForm.setSexList(petBirdMapper.sexAll());
        inputForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("inputForm", inputForm);
        return "input";
    }
    //登録判定
    @PostMapping("/inputResult")
    public String inputResultExecute(@Valid @ModelAttribute InputForm inputForm,BindingResult error, Model model) {
        
        
            //寿命・個体数・金額が空欄でなかったら
            if(inputForm.getLife() != null || inputForm.getCount() != null || inputForm.getPrice() != null){
                   
                if(error.hasErrors()){
                    
                inputForm.setSpeciesList(petBirdMapper.speciesAll());
                inputForm.setSexList(petBirdMapper.sexAll());
                inputForm.setColorList(petBirdMapper.colorAll());

                    return "input";

                //エラーが無かったら登録
                }else{

                    /*新しい品種が登録されたらテーブルから探し出す文
                    List<SpeciesEntity> newSpecies = loginMapper.newSpecies(inputForm.getInputSpecies());
                    
                    for (SpeciesEntity nS : newSpecies) {
                        inputForm.setSpecies(nS.getId());
                    } */
                    
                    loginMapper.inputPetBird(inputForm.getSpecies(),inputForm.getSex(),inputForm.getColor(),inputForm.getLife(),inputForm.getCount(),inputForm.getPrice());
                    inputForm.setComment("登録しました");
                    
                }
            //空欄だったら再表示
            }else{

                inputForm.setSpeciesList(petBirdMapper.speciesAll());
                inputForm.setSexList(petBirdMapper.sexAll());
                inputForm.setColorList(petBirdMapper.colorAll());
                
                return "input";

            }

        
        model.addAttribute("inputForm", inputForm);
        return "input";
    }
    //新規登録
    @RequestMapping(value = "/inputResult", params = "signUp", method = RequestMethod.POST)
    public String signUp(@RequestParam String signUp,Model model) {
    
        InputForm inputForm = new InputForm();
        
        model.addAttribute("inputForm", inputForm);
        return "signUp";
    }
    //新規登録結果
    @PostMapping("/signUpResult")
    public String signUpResultExecute(@ModelAttribute InputForm inputForm,BindingResult error, Model model) {

        inputForm.setSpeciesList(petBirdMapper.speciesAll());
        inputForm.setSexList(petBirdMapper.sexAll());
        inputForm.setColorList(petBirdMapper.colorAll());
        
            //バリデーションのメッセージ出せない
            try{
            //空欄じゃない
            if(inputForm.getInputSpecies() != null){
                //エラーだったら
                if(error.hasErrors()){
                    inputForm.setComment("登録できませんでした");
                    return "inputResult";
                //エラーじゃなかったら
                }else{
                    loginMapper.inputSpecies(inputForm.getInputSpecies());
                    inputForm.setComment("登録しました");        
                }
            //空欄だったら
            }else{
                inputForm.setComment("登録できませんでした");
                    return "inputResult";
            }
            //もし種類名で一意制約違反が出たらNGへ,無かったら登録処理へ進む  
            }catch(Exception e){

                System.out.println(e);
                System.out.println("!!一意制約違反発生");
                inputForm.setComment("既に登録されている鳥です");

                return "ng";
            }
        
        model.addAttribute("inputForm", inputForm);
        return "signUpResult";
    }

    //更新対象選択
    @PostMapping("/updateSpecies")
    public String updateSpeciesExecute(@ModelAttribute ActionForm actionForm, Model model) {

        UpdateForm updateForm = new UpdateForm();

        List<SpeciesEntity> speciesLine = petBirdMapper.speciesAll();
        updateForm.setSpeciesList(petBirdMapper.speciesAll());

        for (SpeciesEntity line : speciesLine) {
            updateForm.setSpecies(line.getSpeciesname());
        }
        
        model.addAttribute("updateForm", updateForm);
        return "updateSpecies";
    }

    //更新
    @PostMapping("/update")
    public String updateExecute(@ModelAttribute UpdateForm updateForm, Model model) {
        
        updateForm.setSpeciesList(petBirdMapper.speciesAll());
        updateForm.setSexList(petBirdMapper.sexAll());
        updateForm.setColorList(petBirdMapper.colorAll());
        //Stringで受取った値をIntegerに変換して検索する
        SpeciesEntity speciesList = loginMapper.speciesId(updateForm.getSpecies());
        PetBirdEntity updateBird = loginMapper.updatePetBird(speciesList.getId());

        model.addAttribute("updateForm", updateForm);
        return "update";
    }

    //削除
    @PostMapping("/delete")
    public String deleteExecute(@ModelAttribute UpdateForm updateForm, Model model) {
        
        DeleteForm deleteForm = new DeleteForm();

        deleteForm.setSpeciesList(petBirdMapper.speciesAll());
        deleteForm.setSexList(petBirdMapper.sexAll());
        deleteForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("deleteForm", deleteForm);
        return "delete";
    }
    //完了
    @PostMapping("/success")
    public String successExecute(@ModelAttribute InputForm inputForm, Model model) {

        SuccessForm successForm = new SuccessForm();

        successForm.setComment("完了しました");

        model.addAttribute("successForm", successForm);
        
        return "success";
        
    }
}
