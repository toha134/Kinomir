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
public class MyWebMoviesoonProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_moviesoon ?, ?");

            if (params.get(KinomirManager.WEEKS) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.WEEKS)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
			if (params.get(KinomirManager.IDREGION) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDREGION)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();

            while (rs.next()) {
                Element item = el.addElement("movie");
                item.addAttribute("idshow", Integer.toString(rs.getInt("idshow")));
                item.addAttribute("premieredate", rs.getString("premieredate"));
                item.addAttribute("name", rs.getString("name"));
                item.addAttribute("remark", rs.getString("remark"));
                item.addAttribute("description", rs.getString("description"));
                item.addAttribute("genre_name", rs.getString("genre_name"));
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getMoviesons(conn, params);
    }
    
    
}
