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
import org.dom4j.Node;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class GetPerformanceProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetPerformance ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
            // @IdBuilding int, @IdHall int, @IdShow int, @IdShowType varchar(2), @IdGenre int, 
            // @BeginTime datetime, @EndTime datetime, @AllFields tinyint, @AsId bit
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
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(3, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDSHOWTYPE) != null) {
                sp.setString(4, params.get(KinomirManager.IDSHOWTYPE));
            } else {
                sp.setNull(4, java.sql.Types.VARCHAR);
            }
            if (params.get(KinomirManager.IDGENRE) != null) {
                sp.setInt(5, Integer.parseInt(params.get(KinomirManager.IDGENRE)));
            } else {
                sp.setNull(5, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 6);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 7);
            if (params.get(KinomirManager.ALLFIELDS) != null) {
                sp.setInt(8, Integer.parseInt(params.get(KinomirManager.ALLFIELDS)));
            } else {
                sp.setInt(8, 1);
            }
            if (params.get(KinomirManager.ASID) != null) {
                sp.setInt(9, Integer.parseInt(params.get(KinomirManager.ASID)));
            } else {
                sp.setInt(9, 1);
            }
            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setInt(10, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
            } else {
                sp.setNull(10, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            Element item = null;
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                logger.debug("proccess next row");
                Node node = el.selectSingleNode(String.format("//show[@IdShow='%s']", Integer.toString(rs.getInt("IdShow"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = el.addElement("show");
                    item.addAttribute("IdShow", Integer.toString(rs.getInt("IdShow")));
                    item.addAttribute("IdGenre", Integer.toString(rs.getInt("IdGenre")));
                    item.addAttribute("IdShowType", rs.getString("IdShowType"));
                    item.addAttribute("ShowName", rs.getString("ShowName"));
                    item.addAttribute("GenreName", rs.getString("GenreName"));
                    item.addAttribute("Duration", Integer.toString(rs.getInt("Duration")));
                }
                node = item.selectSingleNode(String.format("building[@IdBuilding=%s and @IdHall=%s]", Integer.toString(rs.getInt("IdBuilding")), Integer.toString(rs.getInt("IdHall"))));
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = item.addElement("building");
                    item.addAttribute("IdBuilding", Integer.toString(rs.getInt("IdBuilding")));
                    item.addAttribute("IdHall", Integer.toString(rs.getInt("IdHall")));
                    item.addAttribute("BuildingName", rs.getString("BuildingName"));
                    item.addAttribute("HallName", rs.getString("HallName"));
                }

                //<performance IdPerformance="" DateTime="" ShowName="" GenreName="" Duration="" MinPrice="" MaxPrice="" FreePlace="" />
                item = item.addElement("performance");
                item.addAttribute("IdPerformance", Integer.toString(rs.getInt("IdPerformance")));
                item.addAttribute("DateTime", outDateFormat.format(rs.getTimestamp("DateTime", new GregorianCalendar())));
                item.addAttribute("MinPrice", Double.toString(rs.getDouble(("MinPrice"))));
                item.addAttribute("MaxPrice", Double.toString(rs.getDouble(("MaxPrice"))));
                item.addAttribute("FreePlace", Integer.toString(rs.getInt("FreePlace")));

            }
            logger.debug("end processing rows");
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getPerformance(conn, params, logger, df);
    }
    
    
}
