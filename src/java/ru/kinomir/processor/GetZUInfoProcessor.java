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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author user
 */
public class GetZUInfoProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_getzuinfo ?");
            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                throw new InvalidParameterException("Parameter '" + KinomirManager.IDBUILDING + "' not found!");
            }
            rs = sp.executeQuery();
            while (rs.next()) {
                Element item = el.addElement("Building");
                item.addAttribute("IdBuilding", Integer.toString(rs.getInt("IdBuilding")));
                item.addAttribute("Description", rs.getString("Description"));
                item.addAttribute("Address", rs.getString("Address"));
                item.addAttribute("Phone", rs.getString("Phone"));
                item.addAttribute("Metro", rs.getString("Metro"));
                item.addAttribute("Path", rs.getString("Path"));
                item.addAttribute("WorkingHours", rs.getString("WorkingHours"));
                item.addAttribute("ShortName", rs.getString("ShortName"));
                item.addAttribute("DisplayTitle", rs.getString("DisplayTitle"));
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }
}
