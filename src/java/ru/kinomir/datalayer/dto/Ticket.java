/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "ticket")
public class Ticket {

    @SerializedName("IdTicketOperation")
    private final String IdTicketOperation;
    @SerializedName("IdPerformance")
    private final String IdPerformance;
    @SerializedName("IdPlace")
    private final String IdPlace;
    @SerializedName("Price")
    private final String Price;
    @SerializedName("Discount")
    private final String Discount;
    @SerializedName("Series")
    private final String Series;
    @SerializedName("TickNum")
    private final String TickNum;
    @SerializedName("datetime")
    private final String datetime;
    @SerializedName("HallName")
    private final String HallName;
    @SerializedName("BuildingName")
    private final String BuildingName;
    @SerializedName("Row")
    private final String Row;
    @SerializedName("Place")
    private final String Place;
    @SerializedName("ShowName")
    private final String ShowName;
    @SerializedName("agelimit")
    private final String agelimit;

    public Ticket() {
        IdTicketOperation = "";
        IdPerformance = "";
        IdPlace = "";
        Price = "";
        Discount = "";
        Series = "";
        TickNum = "";
        datetime = "";
        HallName = "";
        BuildingName = "";
        Row = "";
        Place = "";
        ShowName = "";
        agelimit = "";
    }

    public Ticket(ResultSet rs) throws SQLException {
        IdTicketOperation = rs.getString("IdTicketOperation");
        IdPerformance = rs.getString("IdPerformance");
        IdPlace = rs.getString("IdPlace");
        Price = rs.getString("Price");
        Discount = rs.getString("Discount");
        Series = rs.getString("Series");
        TickNum = rs.getString("TickNum");
        datetime = rs.getString("datetime");
        HallName = rs.getString("HallName");
        BuildingName = rs.getString("BuildingName");
        Row = rs.getString("Row");
        Place = rs.getString("Place");
        ShowName = rs.getString("ShowName");
        agelimit = rs.getString("agelimit");
    }

    @XmlAttribute(name = "IdTicketOperation")
    public String getIdTicketOperation() {
        return IdTicketOperation;
    }

    @XmlAttribute(name = "IdPerformance")
    public String getIdPerformance() {
        return IdPerformance;
    }

    @XmlAttribute(name = "IdPlace")
    public String getIdPlace() {
        return IdPlace;
    }

    @XmlAttribute(name = "Price")
    public String getPrice() {
        return Price;
    }

    @XmlAttribute(name = "Discount")
    public String getDiscount() {
        return Discount;
    }

    @XmlAttribute(name = "Series")
    public String getSeries() {
        return Series;
    }

    @XmlAttribute(name = "TickNum")
    public String getTickNum() {
        return TickNum;
    }

    @XmlAttribute(name = "datetime")
    public String getdatetime() {
        return datetime;
    }

    @XmlAttribute(name = "HallName")
    public String getHallName() {
        return HallName;
    }

    @XmlAttribute(name = "BuildingName")
    public String getBuildingName() {
        return BuildingName;
    }

    @XmlAttribute(name = "Row")
    public String getRow() {
        return Row;
    }

    @XmlAttribute(name = "Place")
    public String getPlace() {
        return Place;
    }

    @XmlAttribute(name = "ShowName")
    public String getShowName() {
        return ShowName;
    }

    @XmlAttribute(name = "agelimit")
    public String getagelimit() {
        return agelimit;
    }

}
