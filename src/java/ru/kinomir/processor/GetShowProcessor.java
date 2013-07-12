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
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class GetShowProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        ResultSet rs = KinomirManager.getShow(conn, params, logger, df);
        try {
            // IdShow, IdGenre, IdShowType, ShowName, Duration, 
            // IdBuilding, IdHall, Producer, Description, Cast
            while (rs.next()) {
                Node node = el.selectSingleNode(String.format("//show[@IdShow=%s]", Integer.toString(rs.getInt("IdShow"))));
                Element item = null;
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = el.addElement("show");
                    item.addAttribute("IdShow", Integer.toString(rs.getInt("IdShow")));
                    item.addAttribute("IdGenre", Integer.toString(rs.getInt("IdGenre")));
                    item.addAttribute("IdShowType", rs.getString("IdShowType"));
                    item.addAttribute("ShowName", rs.getString("ShowName"));
                    item.addAttribute("Duration", Integer.toString(rs.getInt("Duration")));
                    item.addAttribute("Produser", rs.getString("Producer"));
                    item.addAttribute("Description", rs.getString("Description"));
                    item.addAttribute("Cast", rs.getString("Cast"));
                }
                item = item.addElement("hall");
                item.addAttribute("IdBuilding", Integer.toString(rs.getInt("IdBuilding")));
                item.addAttribute("IdHall", Integer.toString(rs.getInt("IdHall")));


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
