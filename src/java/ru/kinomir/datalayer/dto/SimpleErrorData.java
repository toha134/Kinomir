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
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class SimpleErrorData extends DataNode {

    @SerializedName("error")
    private String error;
    @SerializedName("errorDescription")
    private String errorDescription;
    
    public SimpleErrorData(){
        
    }

    public SimpleErrorData(ResultSet rs) throws SQLException {
        if (rs == null) {
            throw new SQLException("No data for query");
        }
        try {
            while (rs.next()) {
                error = rs.getString("Error");
                errorDescription = rs.getString("ErrorDescription");
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    @XmlAttribute(name = "Error")
    public String getError() {
        return error;
    }

    @XmlAttribute(name = "ErrorDescription")
    public String getErrorDescription() {
        return errorDescription;
    }
}
