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
public class NewOrderInfo {

    @SerializedName("PlaceCount")
    String PlaceCount;
    @SerializedName("Price")
    String Price;
    @SerializedName("idOrder")
    String idOrder;
    @SerializedName("MarkUp")
    String MarkUp;
    @SerializedName("Description")
    String Description;

    public String getPlaceCount() {
        return PlaceCount;
    }

    public void setPlaceCount(String PlaceCount) {
        this.PlaceCount = PlaceCount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getMarkUp() {
        return MarkUp;
    }

    public void setMarkUp(String MarkUp) {
        this.MarkUp = MarkUp;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
