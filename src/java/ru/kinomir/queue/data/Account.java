/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.queue.data;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Антон
 */
public class Account {

    @SerializedName("login")
    private String login;
    @SerializedName("name")
    private String name;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("patronymic")
    private String patronymic;
    @SerializedName("phone")
    private String phone;
    @SerializedName("cellular")
    private String cellular;
    @SerializedName("email")
    private String email;
    @SerializedName("city")
    private String city;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("password")
    private String password;
    @SerializedName("agreement")
    private String agreement;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellular() {
        return cellular;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

}
