/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class GetPlacesProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        ResultSet rs = KinomirManager.getPlaces(conn, params);
        try {
            while (rs.next()) {
                Element item = el.addElement("place");
                item.addAttribute("IdPlace", Integer.toString(rs.getInt("IdPlace")));
                item.addAttribute("State", rs.getString("State"));
                item.addAttribute("Price", rs.getString("Price"));
                item.addAttribute("PlaceDescription", rs.getString("PlaceDescription"));
                item.addAttribute("SectionName", rs.getString("SectionName"));
                item.addAttribute("PlaceRow", rs.getString("PlaceRow"));
                item.addAttribute("PlacePlace", rs.getString("PlacePlace"));
                item.addAttribute("BookPercent", rs.getString("BookPercent"));
                item.addAttribute("PosX", rs.getString("PosX"));
                item.addAttribute("PosY", rs.getString("PosY"));
                item.addAttribute("IdSection", rs.getString("IdSection"));
                try {
                    item.addAttribute("IdClient", rs.getString("IdClient"));
                } catch (SQLException ex) {
                    logger.error("Column not found", ex);
                    item.addAttribute("IdClient", "");
                }
                try {
                    item.addAttribute("idorder", rs.getString("idorder"));
                } catch (SQLException ex) {
                    logger.error("Column not found", ex);
                    item.addAttribute("idorder", "");
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
