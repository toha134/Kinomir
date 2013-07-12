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

/**
 *
 * @author Admin
 */
public class MyWebMoviesoonProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        

        ResultSet rs = KinomirManager.getMoovieSoon(conn, params);
        try {
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
            if (rs != null) {
                rs.close();
            }
        }
    }
}
