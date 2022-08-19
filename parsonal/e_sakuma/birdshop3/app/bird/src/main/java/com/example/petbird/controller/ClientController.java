package com.example.petbird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petbird.form_client.BuyForm;
import com.example.petbird.form_client.BuyResultForm;
import com.example.petbird.form_client.SearchForm;
import com.example.petbird.form_client.SelectForm;
import com.example.petbird.form_login.PetBirdForm;

@Controller
public class ClientController {
    

    @PostMapping("/petbird/search")
    public String petbirdLoginExecute(@ModelAttribute PetBirdForm petBirdForm, BindingResult error, Model model) {
                
        SearchForm searchForm = new SearchForm();

        model.addAttribute("searctForm", searchForm);
        return "c_search";     
    }

    @PostMapping("/search/select")
    public String searchSelectExecute(@ModelAttribute SearchForm searchForm, BindingResult error, Model model) {
                
        SelectForm selectForm = new SelectForm();

        model.addAttribute("selectForm", selectForm);
        return "c_select";     
    }

    @PostMapping("/search/select/buy")
    public String searchSelectBuyExecute(@ModelAttribute SelectForm selectForm, BindingResult error, Model model) {
                
        BuyForm buyForm = new BuyForm();

        model.addAttribute("buyForm", buyForm);
        return "c_buy";     
    }

    @PostMapping("/search/select/buy/buyResult")
    public String searchSelectBuyResultExecute(@ModelAttribute BuyForm buyForm, BindingResult error, Model model) {
                
        
        model.addAttribute("buyForm",buyForm);
        return "c_buyResult";     
    }

}
