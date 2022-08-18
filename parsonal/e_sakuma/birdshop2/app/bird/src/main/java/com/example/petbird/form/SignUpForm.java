package com.example.petbird.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignUpForm {
    
    Integer id;
    
    @Pattern(regexp = "^[\\u30a0-\\u30ff]+$", message = "全角カタカナのみ可")
    @NotBlank(message = "鳥の種類を入力してください")
    String inputSpecies;
    String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInputSpecies() {
        return inputSpecies;
    }

    public void setInputSpecies(String inputSpecies) {
        this.inputSpecies = inputSpecies;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
}
