package com.example.crud.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardDto {
    private Integer id;

    private String type;

    private String number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
