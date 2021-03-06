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
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class GetPerfInfoProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        ResultSet rs = null;
        PreparedStatement sp = null;

        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetPerfInfo ?");

            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
            } else {
                throw new InvalidParameterException("Parameter '" + KinomirManager.IDPERFORMANCE + "' not found!");
            }
            rs = sp.executeQuery();

            // DateTime	ShowName	Producer	Painter	Description	Remark	Actors	pu_number	
            // NameEng	ShowType	GenreName	Hall	HallDesc	BuildingName Duration Premieredate
            Element item = null;
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                item = el.addElement("performance");
                item.addAttribute("DateTime", outDateFormat.format(rs.getTimestamp("DateTime", new GregorianCalendar())));
                item.addAttribute("ShowName", rs.getString("ShowName"));
                item.addAttribute("Producer", rs.getString("Producer"));
                item.addAttribute("Painter", rs.getString("Painter"));
                item.addAttribute("Description", rs.getString("Description"));
                item.addAttribute("Remark", rs.getString("Remark"));
                item.addAttribute("Actors", rs.getString("Actors"));
                item.addAttribute("pu_number", rs.getString("pu_number"));
                item.addAttribute("NameEng", rs.getString("NameEng"));
                item.addAttribute("ShowType", rs.getString("ShowType"));
                item.addAttribute("GenreName", rs.getString("GenreName"));
                item.addAttribute("Hall", rs.getString("Hall"));
                item.addAttribute("HallDesc", rs.getString("HallDesc"));
                item.addAttribute("BuildingName", rs.getString("BuildingName"));
                item.addAttribute("Duration", rs.getString("Duration"));
                item.addAttribute("Premieredate", rs.getString("Premieredate"));
            }
            logger.debug("end processing rows");
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getPerfInfo(conn, params);
    }

}
