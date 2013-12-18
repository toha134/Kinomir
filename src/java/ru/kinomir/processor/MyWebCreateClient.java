/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Element;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class MyWebCreateClient extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {
//          @IdDocument declare @IdDocument T_ID_Small declare @Login varchar(16) declare @Name varchar(64) declare @FirstName varchar(36) declare 
//          @Patronymic varchar(36) declare @Phone varchar(20) declare @Cellular varchar(20) declare @Email varchar(64) declare @City T_NameString 
//        declare @Birthday T_DATE_TIME  
//        select @IdDocument = null select @Login = null select @Name = null select @FirstName = null select @Patronymic = null 
//        select @Phone = null select @Cellular = null select @Email = null select @City = null select @Birthday = null  
//        execute dbo.MyWeb_CreateClient @IdDocument, @Login, @Name, @FirstName, @Patronymic, @Phone, @Cellular, @Email, @City, @Birthday  
//                ответ: OK: @IdClient as IdClient, @o_Name as Name 
//                       Error:    ErrorCode as Error,Description as ErrorDescription 
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("dbo.MyWeb_CreateClient ?, ?, ?, ?, ?, ?, ?, ? , ?, ?");
            if (!"".equals(params.get("IdDocument"))) {
                sp.setShort(1, Short.parseShort(params.get("IdDocument")));
            } else {
                sp.setNull(1, java.sql.Types.SMALLINT);
            }
            if (params.get("Login") != null) {
                sp.setString(2, params.get("Login"));
            } else {
                sp.setNull(2, java.sql.Types.VARCHAR);
            }
            if (params.get("Name") != null) {
                sp.setString(3, params.get("Name"));
            } else {
                sp.setNull(3, java.sql.Types.VARCHAR);
            }
            if (params.get("FirstName") != null) {
                sp.setString(4, params.get("FirstName"));
            } else {
                sp.setNull(4, java.sql.Types.VARCHAR);
            }
            if (params.get("Patronymic") != null) {
                sp.setString(5, params.get("Patronymic"));
            } else {
                sp.setNull(5, java.sql.Types.VARCHAR);
            }
            if (params.get("Phone") != null) {
                sp.setString(6, params.get("Phone"));
            } else {
                sp.setNull(6, java.sql.Types.VARCHAR);
            }
            if (params.get("Cellular") != null) {
                sp.setString(7, params.get("Cellular"));
            } else {
                sp.setNull(7, java.sql.Types.VARCHAR);
            }
            if (params.get("Email") != null) {
                sp.setString(8, params.get("Email"));
            } else {
                sp.setNull(8, java.sql.Types.VARCHAR);
            }
            if (params.get("City") != null) {
                sp.setString(9, params.get("City"));
            } else {
                sp.setNull(9, java.sql.Types.VARCHAR);
            }
            if (params.get("Birthday") != null) {
                try {
                    sp.setTimestamp(10, new Timestamp(df.parse(params.get("Birthday")).getTime()));
                } catch (ParseException ex) {
                    sp.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
                }
            } else {
                sp.setNull(10, java.sql.Types.TIMESTAMP);
            }
            rs = sp.executeQuery();
            while (rs.next()) {
                Element item = el.addElement("result");
                item.addAttribute("IdClient", Integer.toString(rs.getInt("IdClient")));
                item.addAttribute("Name", rs.getString("Name"));
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }
}
