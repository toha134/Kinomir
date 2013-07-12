/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class GetPerformanceProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        // IdPerformance, DateTime, BuildingName, HallName, ShowName, GenreName, Duration, MinPrice, MaxPrice, FreePlace, 
        // IdBuilding, IdHall, IdShowType, IdGenre, IdShow
        logger.debug("Start query");
        ResultSet rs =KinomirManager.getPerformance(conn, params, logger, df);
        if (rs.getWarnings() != null) {
            logger.warn("Sql query stack: " + rs.getWarnings().getMessage(), rs.getWarnings().getCause());
        }
        try {
            Element item = null;
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                logger.debug("proccess next row");
                Node node = el.selectSingleNode(String.format("//show[@IdShow='%s']", Integer.toString(rs.getInt("IdShow"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = el.addElement("show");
                    item.addAttribute("IdShow", Integer.toString(rs.getInt("IdShow")));
                    item.addAttribute("IdGenre", Integer.toString(rs.getInt("IdGenre")));
                    item.addAttribute("IdShowType", rs.getString("IdShowType"));
                    item.addAttribute("ShowName", rs.getString("ShowName"));
                    item.addAttribute("GenreName", rs.getString("GenreName"));
                    item.addAttribute("Duration", Integer.toString(rs.getInt("Duration")));
                }
                node = item.selectSingleNode(String.format("building[@IdBuilding=%s and @IdHall=%s]", Integer.toString(rs.getInt("IdBuilding")), Integer.toString(rs.getInt("IdHall"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = item.addElement("building");
                    item.addAttribute("IdBuilding", Integer.toString(rs.getInt("IdBuilding")));
                    item.addAttribute("IdHall", Integer.toString(rs.getInt("IdHall")));
                    item.addAttribute("BuildingName", rs.getString("BuildingName"));
                    item.addAttribute("HallName", rs.getString("HallName"));
                }

                //<performance IdPerformance="" DateTime="" ShowName="" GenreName="" Duration="" MinPrice="" MaxPrice="" FreePlace="" />
                item = item.addElement("performance");
                item.addAttribute("IdPerformance", Integer.toString(rs.getInt("IdPerformance")));

                item.addAttribute("DateTime", outDateFormat.format(rs.getTimestamp("DateTime", new GregorianCalendar())));
                item.addAttribute("MinPrice", Double.toString(rs.getDouble(("MinPrice"))));
                item.addAttribute("MaxPrice", Double.toString(rs.getDouble(("MaxPrice"))));
                item.addAttribute("FreePlace", Integer.toString(rs.getInt("FreePlace")));

            }
            logger.debug("end processing rows");
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
