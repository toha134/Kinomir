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
public class NewOrderMessage {

    @SerializedName("action")
    private final String action = "created_order";
    @SerializedName("order")
    private List<NewOrderInfo> order = new ArrayList<NewOrderInfo>();
    @SerializedName("date")
    private Date date = new Date();

    public List<NewOrderInfo> getOrder() {
        return order;
    }

    public void setOrder(List<NewOrderInfo> order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

}
