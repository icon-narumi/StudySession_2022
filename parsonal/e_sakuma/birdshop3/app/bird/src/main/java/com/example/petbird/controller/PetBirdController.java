package com.example.petbird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.petbird.form_login.PetBirdForm;
import com.example.petbird.mapper.M_MasterMapper;
import com.example.petbird.mapper.T_ClientMapper;

@Controller
public class PetBirdController {
    @Autowired
    private M_MasterMapper masterMapper;

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
        public String init(Model model) {

            PetBirdForm petBirdForm = new PetBirdForm();

            petBirdForm.setSpeciesList(masterMapper.speciesAll());
            petBirdForm.setSexList(masterMapper.sexAll());
            petBirdForm.setColorList(masterMapper.colorAll());
            

        model.addAttribute("petBirdForm",petBirdForm);
        return "petbird";
    }

}