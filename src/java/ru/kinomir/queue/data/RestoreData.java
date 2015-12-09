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
public class RestoreData {

    @SerializedName("action")
    String action;
    @SerializedName("password")
    String password;
    @SerializedName("address")
    String address;
    @SerializedName("new_password")
    String newPassword;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
