package com.example.rental2.controller;

import org.hibernate.validator.internal.metadata.descriptor.ReturnValueDescriptorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.CustomerAddForm;
import com.example.rental2.form.CustomerDeleteForm;
import com.example.rental2.service.CustomerRegistService;

//顧客情報管理コントローラー

@Controller
public class CustomerInformationController {

    @Autowired
    private CustomerRegistService customerRegistService;

    // 新規登録画面
    @GetMapping("/customerInformation/add")
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
    public String addProsece(@ModelAttribute CustomerAddForm customerAddForm, Model model) {

        customerRegistService.insertByCustomer(customerAddForm.getCustomerName(), customerAddForm.getPhoneNumber(),
                customerAddForm.getAge(), customerAddForm.getGender(), customerAddForm.getAddress());

        // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerAddForm.setAgelist(customerRegistService.selectAgeAll());
        customerAddForm.setGenderlist(customerRegistService.selectGenderAll());

        model.addAttribute("customerAddForm", customerAddForm);
        return "customerInformation";
    }

    // 削除初期画面
    @GetMapping("/customerInformation/delete")
    public String deleteExecute(Model model) {

        CustomerDeleteForm customerDeleteForm = new CustomerDeleteForm();

        // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerDeleteForm.setAgelist(customerRegistService.selectAgeAll());
        customerDeleteForm.setGenderlist(customerRegistService.selectGenderAll());
        customerDeleteForm.setList(customerRegistService.selectByCustomerInformation());

        model.addAttribute("customerDeleteForm", customerDeleteForm);
        return "customerDelete";
    }


    //削除処理
    @RequestMapping(value = "/customerInformation/delete", params = "delete", method = RequestMethod.POST)
    public String deleteProsece(@ModelAttribute CustomerDeleteForm customerDeleteForm, Model model){

        customerDeleteForm.setAgelist(customerRegistService.selectAgeAll());
        customerDeleteForm.setGenderlist(customerRegistService.selectGenderAll());
        customerDeleteForm.setList(customerRegistService.selectByCustomerInformation());


        model.addAttribute("customerDeleteForm",customerDeleteForm );
        return "customerInformation";
    }
}
