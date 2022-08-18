package com.example.petbird.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.checkerframework.checker.units.qual.Length;

public class LoginForm {
    
    @NotBlank(message = "空欄です")
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
