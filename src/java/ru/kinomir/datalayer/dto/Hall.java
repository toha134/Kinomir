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
@XmlRootElement(name = "hall")
public class Hall {

    @SerializedName("IdSection")
    private final String IdSection;
    @SerializedName("Name")
    private final String Name;
    @SerializedName("Description")
    private final String Description;

    public Hall() {
        IdSection = "";
        Name = "";
        Description = "";
    }

    public Hall(ResultSet rs) throws SQLException {
        IdSection = Integer.toString(rs.getInt("IdSection"));
        Name = rs.getString("Name");
        Description = rs.getString("Description");
    }

    @XmlAttribute(name = "IdSection")
    public String getIdSection() {
        return IdSection;
    }

    @XmlAttribute(name = "Name")
    public String getName() {
        return Name;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return Description;
    }

}
