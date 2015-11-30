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
@XmlRootElement(name = "result")
public class NewClientResult {

    @SerializedName("IdClient")
    String IdClient;
    @SerializedName("Name")
    String Name;
    @SerializedName("Token")
    String Token;
    @SerializedName("EmailCode")
    String EmailCode;
    @SerializedName("CellularCode")
    String CellularCode;

    public NewClientResult() {

    }

    public NewClientResult(ResultSet rs) throws SQLException {
        IdClient = rs.getString("IdClient");
        Name = rs.getString("Name");
        Token = rs.getString("Token");
        EmailCode = rs.getString("EmailCode");
        CellularCode = rs.getString("CellularCode");
    }

    @XmlAttribute(name = "IdClient")
    public String getIdClient() {
        return IdClient;
    }

    @XmlAttribute(name = "Name")
    public String getName() {
        return Name;
    }

    @XmlAttribute(name="Token")
    public String getToken() {
        return Token;
    }

    @XmlAttribute(name="EmailCode")
    public String getEmailCode() {
        return EmailCode;
    }

    @XmlAttribute(name="CellularCode")
    public String getCellularCode() {
        return CellularCode;
    }

}
