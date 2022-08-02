package com.example.petbird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petbird.form.ActionForm;
import com.example.petbird.form.InputForm;
import com.example.petbird.form.LoginForm;
import com.example.petbird.form.PetBirdForm;
import com.example.petbird.form.SuccessForm;
import com.example.petbird.mapper.PetBirdMapper;

@Controller
public class LoginController {

    @Autowired
    private PetBirdMapper petBirdMapper;

    @PostMapping("/Login")
    public String loginExecute(@ModelAttribute PetBirdForm petBirdForm, Model model) {

        LoginForm loginForm = new LoginForm();

        
        
        model.addAttribute("loginForm", loginForm);
        return "login";

    }

    @PostMapping("/action")
    public String actionExecute(@ModelAttribute LoginForm loginForm, Model model) {

        ActionForm actionForm = new ActionForm();

        model.addAttribute("actionForm", actionForm);
        return "action";

    }

    @PostMapping("/input")
    public String inputExecute(@ModelAttribute ActionForm actionForm, Model model) {

        InputForm inputForm = new InputForm();

        inputForm.setSexList(petBirdMapper.sexAll());
        inputForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("inputForm", inputForm);
        return "input";

    }

    @PostMapping("/update")
    public String updateExecute(@ModelAttribute ActionForm actionForm, Model model) {

        InputForm inputForm = new InputForm();

        inputForm.setSexList(petBirdMapper.sexAll());
        inputForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("inputForm", inputForm);
        return "input";

    }

    @PostMapping("/success")
    public String successExecute(@ModelAttribute InputForm inputForm, Model model) {

        SuccessForm successForm = new SuccessForm();

        successForm.setComment("完了しました");

        model.addAttribute("successForm", successForm);
        
        return "success";
        
    }
}
