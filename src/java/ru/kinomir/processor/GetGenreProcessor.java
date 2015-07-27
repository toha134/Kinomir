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
public class GetGenreProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
		PreparedStatement sp = null;
		if (params.containsKey(KinomirManager.IDREGION)){
			sp = conn.prepareStatement("exec dbo.Wga_GetGenre ?");
		} else {
			sp = conn.prepareStatement("exec dbo.Wga_GetGenre");
		}
        if (params.containsKey(KinomirManager.IDREGION)){
			sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDREGION)));
		}
        ResultSet rs = sp.executeQuery();
        try {
            while (rs.next()) {
                Element item = el.addElement("Genre");
                item.addAttribute("IdGenre", rs.getObject("IdGenre") == null ? "" : rs.getObject("IdGenre").toString());
                item.addAttribute("GenreName", rs.getString("GenreName") == null ? "" : rs.getString("GenreName"));
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getGenreData(conn, params);
    }
    
    
}
