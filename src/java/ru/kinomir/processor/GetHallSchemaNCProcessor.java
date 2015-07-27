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
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class GetHallSchemaNCProcessor extends AbstractRequestProcessor {
	
	private String nullToEmptyString(String str) {
		return str == null ? "" : str;
	}

	@Override
	protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
		PreparedStatement sp = null;
		ResultSet rs = null;
		try {
			sp = conn.prepareStatement("exec dbo.Wga_GetHallSchemaNC ?, ?, ?");
			if ((params.get(KinomirManager.IDHALL) != null) && (!"null".equalsIgnoreCase(params.get(KinomirManager.IDHALL)))) {
				sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDHALL)));
			} else {
				sp.setNull(1, java.sql.Types.INTEGER);
			}
			if ((params.get(KinomirManager.IDPERFORMANCE) != null) && (!"null".equalsIgnoreCase(params.get(KinomirManager.IDPERFORMANCE)))) {
				sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
			} else {
				sp.setNull(2, java.sql.Types.INTEGER);
			}
			if ((params.get(KinomirManager.IDPRICECATEGORY) != null) && (!"null".equalsIgnoreCase(KinomirManager.IDPRICECATEGORY))) {
				sp.setInt(3, Integer.parseInt(params.get(KinomirManager.IDPRICECATEGORY)));
			} else {
				sp.setNull(3, java.sql.Types.INTEGER);
			}
			// IdPlace, PosX, PosY, Description, State, Price, BookPercent
            rs = sp.executeQuery();
			boolean isFirst = true;
			while (rs.next()) {
				if (isFirst) {
					el = el.addElement("places");
					isFirst = false;
				}
				Element item = el.addElement("place");
				item.addAttribute("IdPlace", (rs.getObject("IdPlace") != null ? Integer.toString(rs.getInt("IdPlace")) : ""));
				item.addAttribute("PosX", Integer.toString(rs.getInt("PosX")));
				item.addAttribute("PosY", Integer.toString(rs.getInt("PosY")));
				item.addAttribute("Description", nullToEmptyString(rs.getString("Description")));
				item.addAttribute("State", nullToEmptyString(rs.getString("State")));
				item.addAttribute("Price", Double.toString(rs.getString("Price") != null ? rs.getDouble("Price") : 0D));
				item.addAttribute("BookPercent", Double.toString(rs.getString("BookPercent") != null ? rs.getDouble("BookPercent") : 0D));
			}
		} catch (SQLException ex) {
			throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
		} finally {
			SqlUtils.closeSQLObjects(rs, sp);
		}
	}

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getHallSchemaNC(conn, params);
    }
    
    
}
