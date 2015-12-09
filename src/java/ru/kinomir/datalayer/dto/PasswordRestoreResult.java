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
public class PasswordRestoreResult extends DataNode {

    @SerializedName("Result")
    private String Result = "Error";
    @SerializedName("EmailCode")
    private String EmailCode = "";
    @SerializedName("CellularCode")
    private String CellularCode = "";
    @SerializedName("Error")
    private String Error = "";
    @SerializedName("ErrorDescription")
    private String ErrorDescription = "";

    @SerializedName("Email")
    private String Email;
    @SerializedName("NewPassword")
    private String NewPassword;
    @SerializedName("Cellular")
    private String Cellular;

    public PasswordRestoreResult() {
    }

    public PasswordRestoreResult(ResultSet rs) throws DataException, SQLException {
        if (rs != null) {
            try {
                if (rs.next()) {
                    Result = rs.getString("Result");
                    if (SqlUtils.hasColumn(rs, "EmailCode")) {
                        EmailCode = rs.getString("EmailCode");
                    }
                    if (SqlUtils.hasColumn(rs, "CellularCode")) {
                        CellularCode = rs.getString("CellularCode");
                    }
                    if (SqlUtils.hasColumn(rs, "Error")) {
                        Error = rs.getString("Error");
                    }
                    if (SqlUtils.hasColumn(rs, "ErrorDescription")) {
                        ErrorDescription = rs.getString("ErrorDescription");
                    }
                    
                    if (SqlUtils.hasColumn(rs, "Email")) {
                        Email = rs.getString("Email");
                    }
                    if (SqlUtils.hasColumn(rs, "Cellular")) {
                        Cellular = rs.getString("Cellular");
                    }
                    
                    if (SqlUtils.hasColumn(rs, "NewPassword")) {
                        NewPassword = rs.getString("NewPassword");
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

    @XmlAttribute(name = "EmailCode")
    public String getEmailCode() {
        return EmailCode;
    }

    @XmlAttribute(name = "CellularCode")
    public String getCellularCode() {
        return CellularCode;
    }

    @XmlAttribute(name = "Error")
    public String getError() {
        return Error;
    }

    @XmlAttribute(name = "ErrorDescription")
    public String getErrorDescription() {
        return ErrorDescription;
    }

    @XmlAttribute(name = "Email")
    public String getEmail() {
        return Email;
    }

    @XmlAttribute(name = "NewPassword")
    public String getNewPassword() {
        return NewPassword;
    }

    @XmlAttribute(name = "Cellular")
    public String getCellular() {
        return Cellular;
    }

}
