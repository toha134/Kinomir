/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
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
public class GetPerformanceNewProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        // idperformance,idshow,begintime,endtime,ddd,zal,kinoteatr_id,
        // kinoteatr,idhall,zonename,price,FreePlaces,ShowName,Duration,
        // PremiereDate,DateLastDisplay,IdGenre,GenreName
        logger.debug("Start query");
        ResultSet rs = KinomirManager.getPerformanceNew(conn, params, logger, df);
        if (rs.getWarnings() != null) {
            logger.warn("Sql query stack: " + rs.getWarnings().getMessage(), rs.getWarnings().getCause());
        }
        try {
            Element item = null;
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                logger.debug("proccess next row");
                Node node = el.selectSingleNode(String.format("//show[@idshow='%s']", Integer.toString(rs.getInt("idshow"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = el.addElement("show");
                    item.addAttribute("idshow", Integer.toString(rs.getInt("idshow")));
                    item.addAttribute("PremiereDate", outDateFormat.format(rs.getTimestamp("PremiereDate", new GregorianCalendar())));
                    item.addAttribute("DateLastDisplay", outDateFormat.format(rs.getTimestamp("DateLastDisplay", new GregorianCalendar())));
                    //item.addAttribute("IdShowType", rs.getString("IdShowType"));
                    item.addAttribute("ShowName", rs.getString("ShowName"));
                    item.addAttribute("IdGenre", rs.getString("IdGenre"));
                    item.addAttribute("GenreName", rs.getString("GenreName"));
                    item.addAttribute("Duration", Integer.toString(rs.getInt("Duration")));
                }
                node = item.selectSingleNode(String.format("kinoteatr[@kinoteatr_id=%s and @idhall=%s]", Integer.toString(rs.getInt("kinoteatr_id")), Integer.toString(rs.getInt("idhall"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = item.addElement("kinoteatr");
                    item.addAttribute("kinoteatr_id", Integer.toString(rs.getInt("kinoteatr_id")));
                    item.addAttribute("idhall", Integer.toString(rs.getInt("idhall")));
                    item.addAttribute("kinoteatr", rs.getString("kinoteatr"));
                    item.addAttribute("zal", rs.getString("zal"));
                }

                node = item.selectSingleNode(String.format("performance[@idperformance=%s]", Integer.toString(rs.getInt("idperformance"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = item.addElement("performance");
                    item.addAttribute("idperformance", Integer.toString(rs.getInt("idperformance")));
                    item.addAttribute("begintime", outDateFormat.format(rs.getTimestamp("begintime", new GregorianCalendar())));
                    try {
                        item.addAttribute("endtime", rs.getObject("endtime") != null ? outDateFormat.format(rs.getTimestamp("endtime", new GregorianCalendar())) : "");
                    } catch (Exception ex) {
                        item.addAttribute("endtime", "");
                    }
                    item.addAttribute("FreePlaces", Integer.toString(rs.getInt("FreePlaces")));
                    item.addAttribute("is3d", Integer.toString(rs.getInt("ddd")));
                }

                item = item.addElement("price");
                item.addAttribute("idzone", Integer.toString(rs.getInt("idzone")));
                item.addAttribute("price", Double.toString(rs.getDouble(("price"))));
                item.addAttribute("zonename", rs.getString("zonename"));


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
