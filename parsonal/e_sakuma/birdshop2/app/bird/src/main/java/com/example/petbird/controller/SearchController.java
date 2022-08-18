package com.example.petbird.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.petbird.form.PetBirdForm;
import com.example.petbird.form.SearchForm;
import com.example.petbird.mapper.PetBirdMapper;
import com.example.petbird.service.CastService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class SearchController {
    @Autowired
    private PetBirdMapper petBirdMapper;
    @Autowired
    private CastService castService;
    
    //最初の画面
    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
        public String init(Model model) {

            PetBirdForm petBirdForm = new PetBirdForm();
            petBirdForm.setSpeciesList(petBirdMapper.speciesAll());
            petBirdForm.setSexList(petBirdMapper.sexAll());
            petBirdForm.setColorList(petBirdMapper.colorAll());

        model.addAttribute("petBirdForm",petBirdForm);
        return "petbird";
    }

    @PostMapping("/search")
    public String searchExecute(@ModelAttribute SearchForm searchForm, Model model) {

        searchForm.setSpeciesList(petBirdMapper.speciesAll());
        searchForm.setSexList(petBirdMapper.sexAll());
        searchForm.setColorList(petBirdMapper.colorAll());

        if(searchForm.getSpecies() != null){

         searchForm.setBeanList(castService.strList((petBirdMapper.selectBird(searchForm.getSpecies(),searchForm.getSex(),searchForm.getColor(),searchForm.getPrice()))));

        }else if(searchForm.getSex() != null){

         searchForm.setBeanList(castService.strList((petBirdMapper.selectBird(searchForm.getSpecies(),searchForm.getSex(),searchForm.getColor(),searchForm.getPrice()))));

        }else if(searchForm.getColor() != null){

         searchForm.setBeanList(castService.strList((petBirdMapper.selectBird(searchForm.getSpecies(),searchForm.getSex(),searchForm.getColor(),searchForm.getPrice()))));

        }else{
            searchForm.setComment("何かしら選択してください");
            return "coution";
        }

        model.addAttribute("searchForm",searchForm);
        return "search";
    }
  
    

}
