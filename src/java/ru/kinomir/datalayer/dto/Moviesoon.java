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
@XmlRootElement(name = "movie")
public class Moviesoon {
    @SerializedName("idshow")
    private final String idshow;
    @SerializedName("premieredate")
    private final String premieredate;
    @SerializedName("name")
    private final String name;
    @SerializedName("remark")
    private final String remark;
    @SerializedName("description")
    private final String description;
    @SerializedName("genre_name")
    private final String genre_name;
    
    public Moviesoon(){
        idshow = "";
        premieredate = "";
        name = "";
        remark = "";
        description = "";
        genre_name = "";
    }

    public Moviesoon(ResultSet rs) throws SQLException {
        idshow = Integer.toString(rs.getInt("idshow"));
        premieredate = rs.getString("premieredate");
        name = rs.getString("name");
        remark = rs.getString("remark");
        description = rs.getString("description");
        genre_name = rs.getString("genre_name");
    }

    @XmlAttribute(name = "idshow")
    public String getIdshow() {
        return idshow;
    }

    @XmlAttribute(name = "premieredate")
    public String getPremieredate() {
        return premieredate;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    @XmlAttribute(name = "remark")
    public String getRemark() {
        return remark;
    }

    @XmlAttribute(name = "description")
    public String getDescription() {
        return description;
    }

    @XmlAttribute(name = "genre_name")
    public String getGenre_name() {
        return genre_name;
    }
}
