/*
 * To change this template, choose Tools | Templates
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
public class SimpleResult {

    @SerializedName("Result")
    private final String result;
    @SerializedName("ResultDescription")
    private final String resultDescription;
    
    public SimpleResult(){
        result = "";
        resultDescription = "";
    }

    public SimpleResult(ResultSet rs) throws SQLException {
        result = rs.getString("Result");
        resultDescription = rs.getString("ResultDescription");
    }

    @XmlAttribute(name = "Result")
    public String getResult() {
        return result;
    }

    @XmlAttribute(name = "ResultDescription")
    public String getResultDescription() {
        return resultDescription;
    }
}
