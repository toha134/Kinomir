/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class AuthResult extends DataNode{

    @SerializedName("Token")
    private String token;
    
    public AuthResult(){
        
    }

    public AuthResult(ResultSet rs) throws SQLException{
        if (rs == null)
            throw new SQLException("No data for query");
        try {
            if (rs.next()) {
                token = rs.getString("Token");
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    @XmlElement(name = "token")
    public String getToken() {
        return token;
    }
}
