/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class GetShowProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetShow ?, ?, ?, ?, ?, ?, ?");

            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDHALL) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDHALL)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDSHOWTYPE) != null) {
                try {
                    sp.setString(3, new String(params.get(KinomirManager.IDSHOWTYPE).getBytes("UTF-8"), "CP1251"));
                } catch (UnsupportedEncodingException ex) {
                    logger.error("Can't convert parametr to CP1251");
                }
            } else {
                sp.setNull(3, java.sql.Types.VARCHAR);
            }
            if (params.get(KinomirManager.IDGENRE) != null) {
                sp.setInt(4, Integer.parseInt(params.get(KinomirManager.IDGENRE)));
            } else {
                sp.setNull(4, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 5);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 6);
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(7, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(7, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
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
           SqlUtils.closeSQLObjects(rs, sp);
        }
    }
}
