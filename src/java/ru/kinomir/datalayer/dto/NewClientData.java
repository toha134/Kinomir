/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class NewClientData extends DataNode{
    @SerializedName("result")
    NewClientResult client;
    
    public NewClientData(){
        
    }

    public NewClientData(ResultSet rs) throws DataException, SQLException {
        if (rs != null) {
            try {
                if (rs.next()) {
                    if (rs.getString("IdClient") != null) {
                        client = new NewClientResult(rs);
                    } else {
                        throw new DataException("1", "No data");
                    }
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    @XmlElement(name = "result")
    public NewClientResult getClient() {
        return client;
    }
    
    
    
}
