package com.example.seibun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.seibun.form.AdminForm;
import com.example.seibun.form.ResultForm;
import com.example.seibun.service.ResultService;

@Controller
public class AdminController {

    @Autowired
    private ResultService resultService;

    //■=■=■= 管理画面初期表示 =■=■=■=■=■=■=■=■=■=■=■=■
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String init(@ModelAttribute ResultForm resultForm, Model model) {

        AdminForm adminForm = new AdminForm();

        String sortList = "category";
        String sort     = "asc";
        String search   = "";

        adminForm.setList(resultService.getResultList(search,sortList,sort));
        model.addAttribute("adminForm", adminForm);

        return "admin/index";
    }



}