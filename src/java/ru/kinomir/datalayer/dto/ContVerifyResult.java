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
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class ContVerifyResult extends DataNode {
        @SerializedName("Result")
    private String Result = "Error";
    @SerializedName("Token")
    private String Token = "";
    @SerializedName("Error")
    private String Error = "";
    @SerializedName("ErrorDescription")
    private String ErrorDescription = "";
    @SerializedName("clientData")
    private DataNode clientData;

    public ContVerifyResult() {
    }

    public ContVerifyResult(ResultSet rs) throws DataException, SQLException {
        if (rs != null) {
            try {
                if (rs.next()) {
                    Result = rs.getString("Result");
                    
                    if (SqlUtils.hasColumn(rs, "Error")) {
                        Error = rs.getString("Error");
                    }
                    if (SqlUtils.hasColumn(rs, "ErrorDescription")) {
                        ErrorDescription = rs.getString("ErrorDescription");
                    }
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    @XmlAttribute(name = "Result")
    public String getResult() {
        return Result;
    }

    @XmlAttribute(name = "Token")
    public String getToken() {
        return Token;
    }
    
    @XmlAttribute(name = "Error")
    public String getError() {
        return Error;
    }

    @XmlAttribute(name = "ErrorDescription")
    public String getErrorDescription() {
        return ErrorDescription;
    }

    public void setClientData(DataNode clientData) {
        this.clientData = clientData;
    }

    @XmlAttribute(name = "clientData")
    public DataNode getClientData() {
        return clientData;
    }

}
