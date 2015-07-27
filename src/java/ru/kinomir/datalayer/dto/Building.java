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
import ru.kinomir.tools.StringTools;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "Building")
public class Building {

    @SerializedName("IdBuilding")
    private final String IdBuilding;
    @SerializedName("Description")
    private final String Description;
    @SerializedName("Address")
    private final String Address;
    @SerializedName("Phone")
    private final String Phone;
    @SerializedName("Metro")
    private final String Metro;
    @SerializedName("Path")
    private final String Path;
    @SerializedName("WorkingHours")
    private final String WorkingHours;
    @SerializedName("ShortName")
    private final String ShortName;
    @SerializedName("DisplayTitle")
    private final String DisplayTitle;
    
    public Building(){
        IdBuilding = "";
        Description = "";
        Address = "";
        Phone = "";
        Metro = "";
        Path = "";
        WorkingHours = "";
        ShortName = "";
        DisplayTitle = "";
    }

    public Building(ResultSet rs) throws SQLException {
        IdBuilding = Integer.toString(rs.getInt("IdBuilding"));
        Description = StringTools.nullToEmptyString(rs.getString("Description"));
        Address = StringTools.nullToEmptyString(rs.getString("Address"));
        Phone = StringTools.nullToEmptyString(rs.getString("Phone"));
        Metro = StringTools.nullToEmptyString(rs.getString("Metro"));
        Path = StringTools.nullToEmptyString(rs.getString("Path"));
        WorkingHours = StringTools.nullToEmptyString(rs.getString("WorkingHours"));
        ShortName = StringTools.nullToEmptyString(rs.getString("ShortName"));
        DisplayTitle = StringTools.nullToEmptyString(rs.getString("DisplayTitle"));
    }

    @XmlAttribute(name = "IdBuilding")
    public String getIdBuilding() {
        return IdBuilding;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlAttribute(name = "Address")
    public String getAddress() {
        return Address;
    }

    @XmlAttribute(name = "Phone")
    public String getPhone() {
        return Phone;
    }

    @XmlAttribute(name = "Metro")
    public String getMetro() {
        return Metro;
    }

    @XmlAttribute(name = "Path")
    public String getPath() {
        return Path;
    }

    @XmlAttribute(name = "WorkingHours")
    public String getWorkingHours() {
        return WorkingHours;
    }

    @XmlAttribute(name = "ShortName")
    public String getShortName() {
        return ShortName;
    }

    @XmlAttribute(name = "DisplayTitle")
    public String getDisplayTitle() {
        return DisplayTitle;
    }
}
