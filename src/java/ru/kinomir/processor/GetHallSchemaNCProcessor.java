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
public class GetHallSchemaNCProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        

        ResultSet rs = KinomirManager.getHallSchemaNC(conn, params);
        // IdPlace, PosX, PosY, Description, State, Price, BookPercent
        try {
            boolean isFirst = true;
            while (rs.next()) {
                if (isFirst) {
                    el = el.addElement("places");
                    isFirst = false;
                }
                Element item = el.addElement("place");
                item.addAttribute("IdPlace", (rs.getObject("IdPlace") != null ? Integer.toString(rs.getInt("IdPlace")) : ""));
                item.addAttribute("PosX", Integer.toString(rs.getInt("PosX")));
                item.addAttribute("PosY", Integer.toString(rs.getInt("PosY")));
                item.addAttribute("Description", rs.getString("Description"));
                item.addAttribute("State", rs.getString("State"));
                item.addAttribute("Price", Double.toString(rs.getDouble("Price")));
                item.addAttribute("BookPercent", Double.toString(rs.getDouble("BookPercent")));
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
