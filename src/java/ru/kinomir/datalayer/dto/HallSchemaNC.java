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
import static ru.kinomir.tools.StringTools.nullToEmptyString;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "place")
public class HallSchemaNC {
    @SerializedName("IdPlace")
    private final String IdPlace;
    @SerializedName("PosX")
    private final String PosX;
    @SerializedName("PosY")
    private final String PosY;
    @SerializedName("Description")
    private final String Description;
    @SerializedName("State")
    private final String State;
    @SerializedName("Price")
    private final String Price;
    @SerializedName("BookPercent")
    private final String BookPercent;
    
    public HallSchemaNC(){
        IdPlace = "";
		PosX = "";
		PosY = "";
		Description = "";
		State = "";
		Price = "";
		BookPercent = "";
    }
    
    public HallSchemaNC(ResultSet rs) throws SQLException{
        IdPlace = (rs.getObject("IdPlace") != null ? Integer.toString(rs.getInt("IdPlace")) : "");
		PosX = Integer.toString(rs.getInt("PosX"));
		PosY = Integer.toString(rs.getInt("PosY"));
		Description = nullToEmptyString(rs.getString("Description"));
		State = nullToEmptyString(rs.getString("State"));
		Price = Double.toString(rs.getString("Price") != null ? rs.getDouble("Price") : 0D);
		BookPercent = Double.toString(rs.getString("BookPercent") != null ? rs.getDouble("BookPercent") : 0D);
    }

    @XmlAttribute(name = "IdPlace")
    public String getIdPlace() {
        return IdPlace;
    }

    @XmlAttribute(name = "PosX")
    public String getPosX() {
        return PosX;
    }

    @XmlAttribute(name = "PosY")
    public String getPosY() {
        return PosY;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlAttribute(name = "State")
    public String getState() {
        return State;
    }

    @XmlAttribute(name = "Price")
    public String getPrice() {
        return Price;
    }

    @XmlAttribute(name = "BookPercent")
    public String getBookPercent() {
        return BookPercent;
    }
    
}
