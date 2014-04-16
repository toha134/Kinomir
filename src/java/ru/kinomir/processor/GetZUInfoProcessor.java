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

	private String nullToEmptyString(String str) {
		return str == null ? "" : str;
	}

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
				item.addAttribute("Description", nullToEmptyString(rs.getString("Description")));
				item.addAttribute("Address", nullToEmptyString(rs.getString("Address")));
				item.addAttribute("Phone", nullToEmptyString(rs.getString("Phone")));
				item.addAttribute("Metro", nullToEmptyString(rs.getString("Metro")));
				item.addAttribute("Path", nullToEmptyString(rs.getString("Path")));
				item.addAttribute("WorkingHours", nullToEmptyString(rs.getString("WorkingHours")));
				item.addAttribute("ShortName", nullToEmptyString(rs.getString("ShortName")));
				item.addAttribute("DisplayTitle", nullToEmptyString(rs.getString("DisplayTitle")));
			}
		} catch (SQLException ex) {
			throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
		} finally {
			SqlUtils.closeSQLObjects(rs, sp);
		}
	}
}
