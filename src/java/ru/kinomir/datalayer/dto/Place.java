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
@XmlRootElement(name = "place")
public class Place {

    @SerializedName("IdPlace")
    private final String IdPlace;
    @SerializedName("State")
    private final String State;
    @SerializedName("Price")
    private final String Price;
    @SerializedName("PlaceDescription")
    private final String PlaceDescription;
    @SerializedName("SectionName")
    private final String SectionName;
    @SerializedName("PlaceRow")
    private final String PlaceRow;
    @SerializedName("PlacePlace")
    private final String PlacePlace;
    @SerializedName("BookPercent")
    private final String BookPercent;
    @SerializedName("PosX")
    private final String PosX;
    @SerializedName("PosY")
    private final String PosY;
    @SerializedName("IdSection")
    private final String IdSection;
    @SerializedName("IdClient")
    private String IdClient;
    @SerializedName("idorder")
    private String idorder;

    public Place() {
        IdPlace = "";
        State = "";
        Price = "";
        PlaceDescription = "";
        SectionName = "";
        PlaceRow = "";
        PlacePlace = "";
        BookPercent = "";
        PosX = "";
        PosY = "";
        IdSection = "";
        IdClient = "";
        idorder = "";
    }

    public Place(ResultSet rs) throws SQLException {
        IdPlace = Integer.toString(rs.getInt("IdPlace"));
        State = rs.getString("State");
        Price = rs.getString("Price");
        PlaceDescription = rs.getString("PlaceDescription");
        SectionName = rs.getString("SectionName");
        PlaceRow = rs.getString("PlaceRow");
        PlacePlace = rs.getString("PlacePlace");
        BookPercent = rs.getString("BookPercent");
        PosX = rs.getString("PosX");
        PosY = rs.getString("PosY");
        IdSection = rs.getString("IdSection");
        try {
            IdClient = rs.getString("IdClient");
        } catch (SQLException ex) {
            IdClient = "";
        }
        try {
            idorder = rs.getString("idorder");
        } catch (SQLException ex) {
            idorder = "";
        }
    }

    @XmlAttribute(name = "IdPlace")
    public String getIdPlace() {
        return IdPlace;
    }

    @XmlAttribute(name = "State")
    public String getState() {
        return State;
    }

    @XmlAttribute(name = "Price")
    public String getPrice() {
        return Price;
    }

    @XmlAttribute(name = "PlaceDescription")
    public String getPlaceDescription() {
        return PlaceDescription;
    }

    @XmlAttribute(name = "SectionName")
    public String getSectionName() {
        return SectionName;
    }

    @XmlAttribute(name = "PlaceRow")
    public String getPlaceRow() {
        return PlaceRow;
    }

    @XmlAttribute(name = "PlacePlace")
    public String getPlacePlace() {
        return PlacePlace;
    }

    @XmlAttribute(name = "BookPercent")
    public String getBookPercent() {
        return BookPercent;
    }

    @XmlAttribute(name = "PosX")
    public String getPosX() {
        return PosX;
    }

    @XmlAttribute(name = "PosY")
    public String getPosY() {
        return PosY;
    }

    @XmlAttribute(name = "IdSection")
    public String getIdSection() {
        return IdSection;
    }

    @XmlAttribute(name = "IdClient")
    public String getIdClient() {
        return IdClient;
    }

    @XmlAttribute(name = "idorder")
    public String getidorder() {
        return idorder;
    }

}
