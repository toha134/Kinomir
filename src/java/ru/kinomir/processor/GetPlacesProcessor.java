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
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class GetPlacesProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.get(KinomirManager.IDCLIENT) != null) {
                sp = conn.prepareStatement("exec dbo.Wga_GetPlaces ?, ?, ?, ?");
                sp.setInt(4, Integer.parseInt(params.get(KinomirManager.IDCLIENT)));
            } else {
                sp = conn.prepareStatement("exec Wga_GetPlacesNC ?, ?, ?");
            }
            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setLong(1, Long.parseLong(params.get(KinomirManager.IDPERFORMANCE)));
            }
            if ((params.get(KinomirManager.WITHPRICE) != null) && !(params.get(KinomirManager.WITHPRICE).equalsIgnoreCase("null"))) {
                sp.setShort(2, Short.parseShort(params.get(KinomirManager.WITHPRICE)));
            } else {
                sp.setNull(2, java.sql.Types.SMALLINT);
            }
            if ((params.get(KinomirManager.ALLPLACES) != null) && !(params.get(KinomirManager.ALLPLACES).equalsIgnoreCase("null"))) {
                sp.setShort(3, Short.parseShort(params.get(KinomirManager.ALLPLACES)));
            } else {
                sp.setNull(3, java.sql.Types.SMALLINT);
            }
            rs = sp.executeQuery();
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
                    logger.error("Column not found" + ex.getMessage());
					logger.debug("Column not found", ex);
                    item.addAttribute("idorder", "");
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
           SqlUtils.closeSQLObjects(rs, sp);
        }
    }
}
