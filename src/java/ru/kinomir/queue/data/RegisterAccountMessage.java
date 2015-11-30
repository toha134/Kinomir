/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.queue.data;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Антон
 */
public class RegisterAccountMessage {

    private String action;
    private List<Account> account = new ArrayList<Account>();
    private Date date;
    private String token;
    @SerializedName("email_activation_code")
    private String emailActivationCode;
    @SerializedName("cellular_activation_code")
    private String cellularActivationCode;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
    
    public void addAccount(Account newAccount){
        account.add(newAccount);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailActivationCode() {
        return emailActivationCode;
    }

    public void setEmailActivationCode(String emailActivationCode) {
        this.emailActivationCode = emailActivationCode;
    }

    public String getCellularActivationCode() {
        return cellularActivationCode;
    }

    public void setCellularActivationCode(String cellularActivationCode) {
        this.cellularActivationCode = cellularActivationCode;
    }
}
