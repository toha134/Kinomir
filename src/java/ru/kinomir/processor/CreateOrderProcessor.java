/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class CreateOrderProcessor extends AbstractRequestProcessor {



    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        ResultSet rs = KinomirManager.createOrder(conn, params, logger);
        try {
            while (rs.next()) {
                try {
                    rs.getString("idOrder");
                    Element item = el.addElement("order");
                    item.addAttribute("idOrder", rs.getString("idOrder"));
                    item.addAttribute("PlaceCount", rs.getString("PlaceCount"));
                    item.addAttribute("Price", rs.getString("Price"));
                    item.addAttribute("MarkUp", rs.getString("MarkUp"));
                } catch (Exception ex) {
                    Element item = el.addElement("error");
                    item.addAttribute("code", rs.getString("Error"));
                    item.addAttribute("text", rs.getString("ErrorDescription"));
                    break;
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
