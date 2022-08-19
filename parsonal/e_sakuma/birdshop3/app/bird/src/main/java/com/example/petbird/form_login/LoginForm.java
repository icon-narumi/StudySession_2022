package com.example.petbird.form_login;

import javax.validation.constraints.NotBlank;

public class LoginForm {
    
    //@NotBlank(message = "空欄です")
    String password;

    String comment;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
