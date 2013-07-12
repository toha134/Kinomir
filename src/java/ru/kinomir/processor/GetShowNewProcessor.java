/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.io.UnsupportedEncodingException;
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
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class GetShowNewProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        ResultSet rs = KinomirManager.getShowNew(conn, params, logger, df);
        try {
            // idshow	showname	age_limit	producer	painter	actors	duration	remark	
            // description	idgenre	genrename	if_prem	PremiereDate	datelastdisplay	nameeng	pu_number
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                Element item = el.addElement("show");
                item.addAttribute("idshow", Integer.toString(rs.getInt("idshow")));
                item.addAttribute("idgenre", Integer.toString(rs.getInt("idgenre")));
                item.addAttribute("genrename", rs.getString("genrename"));
                item.addAttribute("showname", rs.getString("showname"));
                item.addAttribute("age_limit", rs.getString("age_limit") == null ? "" : rs.getString("age_limit"));
                item.addAttribute("duration", Integer.toString(rs.getInt("duration")));
                item.addAttribute("producer", rs.getString("producer"));
                item.addAttribute("painter", rs.getString("painter"));
                item.addAttribute("remark", rs.getString("remark"));
                item.addAttribute("description", rs.getString("description"));
                item.addAttribute("actors", rs.getString("actors"));
                item.addAttribute("if_prem", Integer.toString(rs.getInt("if_prem")));
                item.addAttribute("PremiereDate", outDateFormat.format(rs.getTimestamp("PremiereDate", new GregorianCalendar())));
                item.addAttribute("datelastdisplay", outDateFormat.format(rs.getTimestamp("datelastdisplay", new GregorianCalendar())));
                item.addAttribute("nameeng", rs.getString("nameeng") == null ? "" : rs.getString("nameeng"));
                item.addAttribute("pu_number", rs.getString("pu_number") == null ? "" : rs.getString("pu_number"));
				item.addAttribute("creator", rs.getString("creator") == null ? "" : rs.getString("creator"));
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
