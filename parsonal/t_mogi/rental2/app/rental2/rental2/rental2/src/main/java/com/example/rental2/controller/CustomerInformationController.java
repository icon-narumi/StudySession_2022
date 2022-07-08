package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.form.CustomerAddForm;
import com.example.rental2.form.CustomerInformationForm;
import com.example.rental2.service.CustomerRegistService;

@Controller
public class CustomerInformationController {

    @Autowired
    private CustomerRegistService customerRegistService;

        // 新規登録画面
    @PostMapping("/customerInformation/add")
        public String customerRegistrationExecute(@ModelAttribute CustomerInformationForm customerInformationForm,
                Model model) {
    
            // 新規登録画面のHTMLに移動する
            return "customerRegistration";
    
        }


    // 新規登録処理
    @RequestMapping(value = "/customerInformation/add", params = "add", method = RequestMethod.POST)
    public String addPropsece(@ModelAttribute CustomerAddForm customerAddForm, Model model) {

        customerRegistService.insertByCustomer(customerAddForm.getCustomerName(), customerAddForm.getPhoneNumber(),
                                               customerAddForm.getAge(), customerAddForm.getGender(), customerAddForm.getAddress());

                                               

        customerAddForm.setAgelist(customerRegistService.selectAgeAll());
        

        return "customerInformation";
    }
}
