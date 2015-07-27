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
@XmlRootElement(name = "Genre")
public class Genre {
    
    @SerializedName("IdGenre")
    String IdGenre;
    @SerializedName("GenreName")
    String GenreName;
    
    public Genre(){
        
    }
    
    public Genre(ResultSet rs) throws SQLException{
        IdGenre = rs.getString("IdGenre");
        GenreName = rs.getString("GenreName");
    }

    @XmlAttribute(name = "IdGenre")
    public String getIdGenre() {
        return IdGenre;
    }

    @XmlAttribute(name = "GenreName")
    public String getGenreName() {
        return GenreName;
    }
    
    
    
}
