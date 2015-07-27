/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "show")
public class Show {
    @SerializedName("hall")
    List<ShowData.Hall> halls = new ArrayList<ShowData.Hall>();

    @SerializedName("IdShow")
    private final String IdShow;
    @SerializedName("IdGenre")
    private final String IdGenre;
    @SerializedName("IdShowType")
    private final String IdShowType;
    @SerializedName("ShowName")
    private final String ShowName;
    @SerializedName("Duration")
    private final String Duration;
    @SerializedName("Produser")
    private final String Produser;
    @SerializedName("Description")
    private final String Description;
    @SerializedName("Cast")
    private final String Cast;

    public Show() {
        IdShow = "";
        IdGenre = "";
        IdShowType = "";
        ShowName = "";
        Duration = "";
        Produser = "";
        Description = "";
        Cast = "";
    }

    public Show(ResultSet rs) throws SQLException {
        IdShow = Integer.toString(rs.getInt("IdShow"));
        IdGenre = Integer.toString(rs.getInt("IdGenre"));
        IdShowType = rs.getString("IdShowType");
        ShowName = rs.getString("ShowName");
        Duration = Integer.toString(rs.getInt("Duration"));
        Produser = rs.getString("Producer");
        Description = rs.getString("Description");
        Cast = rs.getString("Cast");
    }

    @XmlAttribute(name = "IdShow")
    public String getIdShow() {
        return IdShow;
    }

    @XmlAttribute(name = "IdGenre")
    public String getIdGenre() {
        return IdGenre;
    }

    @XmlAttribute(name = "IdShowType")
    public String getIdShowType() {
        return IdShowType;
    }

    @XmlAttribute(name = "ShowName")
    public String getShowName() {
        return ShowName;
    }

    @XmlAttribute(name = "Duration")
    public String getDuration() {
        return Duration;
    }

    @XmlAttribute(name = "Produser")
    public String getProduser() {
        return Produser;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlAttribute(name = "Cast")
    public String getCast() {
        return Cast;
    }

    void addHall(ShowData.Hall hall) {
        halls.add(hall);
    }
    
    @XmlElement(name = "hall")
    public List<ShowData.Hall> getHalls(){
        return halls;
    }

}
