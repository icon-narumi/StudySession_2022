package com.example.seibun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.seibun.form.ResultForm;
import com.example.seibun.service.ResultService;

@Controller
public class SearchController {

    @Autowired
    private ResultService resultService;

    //初期表示
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(Model model) {

        ResultForm resultForm = new ResultForm();

        resultForm.setSearch("");
        resultForm.setSortList("category");
        resultForm.setSort("asc");
        resultForm.setResultCount("");

        model.addAttribute("resultForm", resultForm);

        return "result";
    }

    @PostMapping("/result")
    public String postExecute(@ModelAttribute ResultForm resultForm, Model model) {

        //検索ワード、ソート順（項目・昇降）を取得
        String search   = resultForm.getSearch();
        String sortList = resultForm.getSortList();
        String sort     = resultForm.getSort();

        String result = "";

        //検索
        resultForm.setList(resultService.getResultList(search,sortList,sort));

        int c = resultService.getResultList(search,sortList,sort).size();
        if( c == 0 ) {
            result = "該当データなし";
        }else{
            result = "";
        }

        resultForm.setResultCount(result);

        // viewにformをセット
        model.addAttribute("resultForm", resultForm);

        // result.htmlを表示する
        return "result";
    }

}