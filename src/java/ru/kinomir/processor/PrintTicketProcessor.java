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
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class PrintTicketProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        ResultSet rs = null;
        PreparedStatement sp = null;

        String[] ticketFields = {"IdTicketOperation", "IdPerformance", "IdPlace", "Price", "Discount", "Series",
            "TickNum", "datetime", "HallName", "BuildingName", "Row", "Place", "ShowName", "agelimit"};

        try {
            sp = conn.prepareStatement("exec dbo.WgA_PrintTicket ?");

            if (params.get(KinomirManager.IDORDER) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDORDER)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            Element item = null;
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                item = el.addElement("ticket");
                for (String attr : ticketFields) {
                    if ("datetime".equals(attr)) {
                        item.addAttribute("datetime", outDateFormat.format(rs.getTimestamp("datetime", new GregorianCalendar())));
                    } else {
                        if (rs.getString(attr) != null) {
                            item.addAttribute(attr, rs.getString(attr));
                        } else {
                            item.addAttribute(attr, "");
                        }
                    }
                }
            }
            logger.debug("end processing rows");
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }
}
