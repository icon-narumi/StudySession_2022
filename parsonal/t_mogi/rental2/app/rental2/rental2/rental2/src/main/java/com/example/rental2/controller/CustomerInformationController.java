package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.CustomerAddForm;
import com.example.rental2.form.CustomerInformationForm;
import com.example.rental2.service.CustomerRegistService;

//顧客情報管理コントローラー

@Controller
public class CustomerInformationController {

    @Autowired
    private CustomerRegistService customerRegistService;

    // 新規登録画面
    @GetMapping("/customerInformation/add" )
    public String customerRegistrationExecute(Model model) {

        CustomerAddForm customerAddForm = new CustomerAddForm();

        
                // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerAddForm.setAgelist(customerRegistService.selectAgeAll());
        customerAddForm.setGenderlist(customerRegistService.selectGenderAll());
        customerAddForm.setList(customerRegistService.selectByCustomerInformation());

        model.addAttribute("customerAddForm", customerAddForm);
        // 新規登録画面のHTMLに移動する
        return "customerRegistration";

    }

    // 新規登録処理
    @RequestMapping(value = "/customerInformation/add", params = "add", method = RequestMethod.POST)
    public String addPropsece(@ModelAttribute CustomerAddForm customerAddForm, Model model) {

        customerRegistService.insertByCustomer(customerAddForm.getCustomerName(), customerAddForm.getPhoneNumber(),
                customerAddForm.getAge(), customerAddForm.getGender(), customerAddForm.getAddress());

        // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerAddForm.setAgelist(customerRegistService.selectAgeAll());
        customerAddForm.setGenderlist(customerRegistService.selectGenderAll());

        model.addAttribute("customerAddForm", customerAddForm);
        return "customerInformation";
    }
}
