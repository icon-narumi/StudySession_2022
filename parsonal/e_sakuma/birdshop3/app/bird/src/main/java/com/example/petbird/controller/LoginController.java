package com.example.petbird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.petbird.form_login.ActionForm;
import com.example.petbird.form_login.DeleteForm;
import com.example.petbird.form_login.DeleteResultForm;
import com.example.petbird.form_login.InputForm;
import com.example.petbird.form_login.LoginForm;
import com.example.petbird.form_login.SignUpForm;
import com.example.petbird.form_login.SignUpResultForm;
import com.example.petbird.form_login.UpdateForm;
import com.example.petbird.form_login.UpdateResultForm;
import com.example.petbird.form_login.UpdateSpeciesForm;

@Controller
public class LoginController {
    
    @PostMapping("/petbird/login")
    public String petbirdLoginExecute(@ModelAttribute LoginForm loginForm, BindingResult error, Model model) {
                
        model.addAttribute("loginForm", loginForm);
        return "login";     
    }
    @PostMapping("/petbird/login/action")
    public String petbirdLoginActironExecute(@ModelAttribute LoginForm loginForm, BindingResult error, Model model) {
                
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
    public String inputSignUpSignUpResultExecute(@ModelAttribute SignUpForm signUpForm, BindingResult error, Model model) {
        
        
        model.addAttribute("signUpForm", signUpForm);
        return "l_signUpResult";     
    }

    @PostMapping("/input/inputResult")
    public String inputInputResultExecute(@ModelAttribute InputForm inputForm, BindingResult error, Model model) {
                
        model.addAttribute("inputForm", inputForm);
        return "l_inputResult";     
    }

    @PostMapping("/action/updateSpecies")
    public String actionUpdateSpeciesExecute(@ModelAttribute UpdateSpeciesForm updateSpeciesForm, BindingResult error, Model model) {
                
        model.addAttribute("updateSpeciesForm", updateSpeciesForm);
        return "l_updateSpecies";     
    }

    @PostMapping("/action/updateSpecies/update")
    public String actionUpdateSpeciesUpdateExecute(@ModelAttribute UpdateSpeciesForm updateSpeciesForm, BindingResult error, Model model) {
            UpdateForm updateForm = new UpdateForm();
        model.addAttribute("updateForm", updateForm);
        return "l_update";     
    }

    @PostMapping("/action/updateSpecies/update/updateResult")
    public String actionUpdateSpeciesUpdateExecute(@ModelAttribute UpdateForm updateForm, BindingResult error, Model model) {
        UpdateResultForm updateResultForm = new UpdateResultForm();
        model.addAttribute("updateResultForm", updateResultForm);
        return "l_updateResult";     
    }

    @PostMapping("/action/delete")
    public String actionDeleteExecute(@ModelAttribute DeleteForm deleteForm, BindingResult error, Model model) {
        DeleteResultForm deleteResultForm = new DeleteResultForm();
        model.addAttribute("deleteResultForm", deleteResultForm);
        return "l_delete";     
    }

    @PostMapping("/action/delete/deleteResult")
    public String actionDeleteDeleteResultExecute(@ModelAttribute DeleteResultForm deleteResultForm, BindingResult error, Model model) {
        
        model.addAttribute("deleteResultForm", deleteResultForm);
        return "l_deleteResult";     
    }
    

}
