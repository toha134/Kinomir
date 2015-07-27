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
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;

/**
 *
 * @author Admin
 */
public class GetHallProcessor extends AbstractRequestProcessor {

    private static final String BEGINTIME = "BEGINTIME";
    private static final String ENDTIME = "ENDTIME";

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        PreparedStatement sp = conn.prepareStatement("exec dbo.Wga_GetHalls ?, ?, ?");
        if (params.get("IDBUILDING") != null) {
            sp.setInt(1, Integer.parseInt(params.get("IDBUILDING")));
        } else {
            sp.setNull(1, java.sql.Types.INTEGER);
        }
        try {
            if (params.get(BEGINTIME) != null) {
                if (params.get(BEGINTIME).contains(":")) {
                    sp.setTimestamp(2, new Timestamp(df.parse(params.get(BEGINTIME)).getTime()));
                } else {
                    sp.setTimestamp(2, new Timestamp(df.parse(params.get(BEGINTIME) + " 00:00:00").getTime()));
                }
            } else {
                sp.setNull(2, java.sql.Types.DATE);
            }
        } catch (Exception ex) {
            logger.error("Can't parse value for '" + BEGINTIME + "'", ex);
            sp.setNull(2, java.sql.Types.DATE);
        }
        try {
            if (params.get(ENDTIME) != null) {
                if (params.get(ENDTIME).contains(":")) {
                    sp.setTimestamp(3, new Timestamp(df.parse(params.get(ENDTIME)).getTime()));
                } else {
                    sp.setTimestamp(3, new Timestamp(df.parse(params.get(ENDTIME) + " 23:59:59").getTime()));
                }
            } else {
                sp.setNull(3, java.sql.Types.DATE);
            }
        } catch (ParseException ex) {
            logger.error("Can't parse value for '" + ENDTIME + "'", ex);
            sp.setNull(3, java.sql.Types.DATE);
        }
        ResultSet rs = sp.executeQuery();
        try {
            while (rs.next()) {
                Element item = el.addElement("hall");
                item.addAttribute("IdSection", Integer.toString(rs.getInt("IdSection")));
                item.addAttribute("Name", rs.getString("Name"));
                item.addAttribute("Description", rs.getString("Description"));
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (sp != null) {
                sp.close();
            }
        }

    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getHall(conn, params, logger, df);
    }
    
    
}
