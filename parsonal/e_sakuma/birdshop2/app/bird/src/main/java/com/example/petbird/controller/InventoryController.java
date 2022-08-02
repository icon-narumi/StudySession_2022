package com.example.petbird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petbird.form.InventoryForm;
import com.example.petbird.mapper.PetBirdMapper;

@Controller
public class InventoryController {
    @Autowired
    private PetBirdMapper petBirdMapper;
    
    @PostMapping("/ordered")
    public String deleteExecute(@ModelAttribute InventoryForm backOrderedForm, Model model) {

        backOrderedForm.setComment("入荷待ち一覧");        

        model.addAttribute("backOrderedForm",backOrderedForm);
        return "backordered";
    }
    
}
