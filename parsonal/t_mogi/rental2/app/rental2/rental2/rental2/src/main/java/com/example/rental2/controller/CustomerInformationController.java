package com.example.rental2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rental2.entity.customer.CustomerEntity;
import com.example.rental2.form.customer.CustomerAddForm;
import com.example.rental2.form.customer.CustomerDeleteForm;
import com.example.rental2.form.customer.CustomerUpdateForm;
import com.example.rental2.form.customer.CustomerUpdateResultForm;
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

        // formから取ってきた値を追加処理
        customerRegistService.insertByCustomer(customerAddForm.getCustomerName(), customerAddForm.getPhoneNumber(),
                customerAddForm.getAgeId(), customerAddForm.getGender(), customerAddForm.getAddress());

        // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerAddForm.setAgelist(customerRegistService.selectAgeAll());
        customerAddForm.setGenderlist(customerRegistService.selectGenderAll());

        model.addAttribute("customerAddForm", customerAddForm);
        return "customerInformation";
    }

    // 削除初期画面
    @GetMapping("/customerInformation/delete")
    public String deleteExecute(Model model) {

        // 初期画面なのでFormをインスタンス生成
        CustomerDeleteForm customerDeleteForm = new CustomerDeleteForm();

        // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerDeleteForm.setAgelist(customerRegistService.selectAgeAll());
        customerDeleteForm.setGenderlist(customerRegistService.selectGenderAll());
        customerDeleteForm.setList(customerRegistService.selectByCustomerInformation());

        model.addAttribute("customerDeleteForm", customerDeleteForm);
        return "customerDelete";
    }

    // 削除処理
    @RequestMapping(value = "/customerInformation/delete", params = "delete", method = RequestMethod.POST)
    public String deleteProsece(@ModelAttribute CustomerDeleteForm customerDeleteForm, Model model) {

        customerDeleteForm.setAgelist(customerRegistService.selectAgeAll());
        customerDeleteForm.setGenderlist(customerRegistService.selectGenderAll());
        customerDeleteForm.setList(customerRegistService.selectByCustomerInformation());

        // IDで選んだキャラクターを削除処理
        customerRegistService.deleteByCustomerInformation(customerDeleteForm.getId());
        model.addAttribute("customerDeleteForm", customerDeleteForm);
        return "customerInformation";
    }

    // 更新初期画面
    @GetMapping("/customerInformation/update")
    public String update(Model model) {
        // 初期画面なのでインスタンス生成
        CustomerUpdateForm customerUpdateForm = new CustomerUpdateForm();

        // 年齢テーブルと、ジャンルテーブルのリストをFormに格納
        customerUpdateForm.setAgelist(customerRegistService.selectAgeAll());
        customerUpdateForm.setGenderlist(customerRegistService.selectGenderAll());
        customerUpdateForm.setList(customerRegistService.selectByCustomerInformation());

        model.addAttribute("customerUpdateForm", customerUpdateForm);

        return "customerUpdate";
    }

    // 更新データ選択処理
    @RequestMapping(value = "/customerInformation/update", params = "select", method = RequestMethod.POST)
    public String updateProcess(@ModelAttribute CustomerUpdateForm customerUpdateForm, Model model) {

        CustomerUpdateResultForm customerUpdateResultForm = new CustomerUpdateResultForm();
        // 選択したデータを１行だけ取り出す。 取り出したデータをひとまず何かに入れておく(CustomerEntity customerInformationに)

        // 取り出したトランザクションテーブルデータをformにセットする。
        customerUpdateResultForm.setAgelist(customerRegistService.selectAgeAll());
        customerUpdateResultForm.setGenderlist(customerRegistService.selectGenderAll());

        try {
            CustomerEntity customerInformation = customerRegistService
                    .updateSelectBycustomerInformation(customerUpdateForm.getId());

            // 取り出したマスターデータをformにセット
            customerUpdateResultForm.setPhoneNumber(customerInformation.getPhoneNumber());
            customerUpdateResultForm.setCustomerName(customerInformation.getcustomerName());
            customerUpdateResultForm.setAddress(customerInformation.getAddress());
            customerUpdateResultForm.setAgeId(customerInformation.getAgeId());
            customerUpdateResultForm.setGender(customerInformation.getGender());
            customerUpdateResultForm.setId(customerInformation.getId());

        } catch (NullPointerException e) {

            return "customerSelectError";
        }
        model.addAttribute("customerUpdateResultForm", customerUpdateResultForm);
        return "customerUpdateResult";
    }

    // 更新処理
    @RequestMapping(value = "/customerInformation/update/result", method = RequestMethod.POST)
    public String updateResult(@ModelAttribute CustomerUpdateResultForm customerUpdateResultForm, Model model) {

        // 入力した値をFormに入れて更新処理
        customerRegistService.updateBycustomerInformation(customerUpdateResultForm.getCustomerName(),
                customerUpdateResultForm.getPhoneNumber(), customerUpdateResultForm.getAgeId(),
                customerUpdateResultForm.getGender(), customerUpdateResultForm.getAddress(),
                customerUpdateResultForm.getId());

        return "customerInformation";
    }

}
