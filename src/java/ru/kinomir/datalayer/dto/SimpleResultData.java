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
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class SimpleResultData extends DataNode{
    
    @SerializedName("result")
    private SimpleResult result;
    
    public SimpleResultData(){
        
    }
    
    public SimpleResultData(ResultSet rs) throws SQLException{
        if (rs == null)
            throw new SQLException("No data for query");
        if (rs.next()) {
            try {
                result = new SimpleResult(rs);
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new SQLException("No data for query");
        }
    }

    @XmlElement(name = "result")
    public SimpleResult getResult() {
        return result;
    }
    
    
}
