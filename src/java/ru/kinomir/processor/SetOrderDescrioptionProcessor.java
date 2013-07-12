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
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class SetOrderDescrioptionProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        ResultSet rs = KinomirManager.setOrderDescription(conn, params);
        try {
            if (rs != null) {
                while (rs.next()) {
                    Element item = el.addElement("result");
                    item.addAttribute("Result", rs.getString("Result"));
                    item.addAttribute("ResultDescription", rs.getString("ResultDescription"));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
