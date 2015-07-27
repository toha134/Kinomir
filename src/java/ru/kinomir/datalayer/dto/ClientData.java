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
public class ClientData extends DataNode{

    @SerializedName("client")
    Client client;
    
    public ClientData(){
        
    }

    public ClientData(ResultSet rs) throws DataException, SQLException {
        if (rs != null) {
            try {
                if (rs.next()) {
                    if (rs.getString("IdClient") != null) {
                        client = new Client(rs);
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
    
    @XmlElement(name = "client")
    public Client getClient() {
        return client;
    }
}
