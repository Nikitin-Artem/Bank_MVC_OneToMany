package com.example.crud.controller.dto;

import com.example.crud.model.CreditCard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDto {

    private int id;

    private String firstName;

    private String lastName;

    private Double money;

    private List<CreditCard> creditCards;

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
