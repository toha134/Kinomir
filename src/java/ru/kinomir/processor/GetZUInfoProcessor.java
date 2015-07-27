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
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.tools.StringTools;
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
                item.addAttribute("Description", StringTools.nullToEmptyString(rs.getString("Description")));
                item.addAttribute("Address", StringTools.nullToEmptyString(rs.getString("Address")));
                item.addAttribute("Phone", StringTools.nullToEmptyString(rs.getString("Phone")));
                item.addAttribute("Metro", StringTools.nullToEmptyString(rs.getString("Metro")));
                item.addAttribute("Path", StringTools.nullToEmptyString(rs.getString("Path")));
                item.addAttribute("WorkingHours", StringTools.nullToEmptyString(rs.getString("WorkingHours")));
                item.addAttribute("ShortName", StringTools.nullToEmptyString(rs.getString("ShortName")));
                item.addAttribute("DisplayTitle", StringTools.nullToEmptyString(rs.getString("DisplayTitle")));
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getZUInfo(conn, params);
    }

}
