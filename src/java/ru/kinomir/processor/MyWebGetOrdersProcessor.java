/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;
import ru.kinomir.datalayer.KinomirManager;

/**
 *
 * @author Admin
 */
public class MyWebGetOrdersProcessor extends AbstractRequestProcessor {

    private final static String[] orderColumns = {"idorder", "ordercreatetime", "brokerage", "timepayment",
        "orderexpiretime", "orderstate", "orderprice", "orderpaysum", "orderrecalltickets", "saledticketssum",
        "saledtickets", "ordertotalticketssum", "ordertotaltickets", "name", "firstname", "patronymic", "phone", "email", "fax", "cellular", "birthday"};
    private final static String[] performanceColumns = {"showname", "idperformance", "performancestarttime", "hall", "building"};
    private final static String[] placeColumns = {"idplace", "rownom", "placenom"};

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        ResultSet rs = KinomirManager.getOrders(conn, params, logger, df);
        try {
            while (rs.next()) {
                Node node = el.selectSingleNode(String.format("//order[@IdOrder='%s']", rs.getString("idorder")));
                Element item = null;
                if (node != null) {
                    item = (Element) node;
                    node = null;
                } else {
                    item = el.addElement("order");
                    item.addAttribute("IdOrder", rs.getString("idorder"));
                    for (String colName : orderColumns) {
                        if (colName.equals("ordertotalticketssum")) {
                            item.addAttribute(colName, rs.getString(colName) == null ? "0" : Double.toString(rs.getDouble(colName)));
                        } else {
                            item.addAttribute(colName, rs.getString(colName) == null ? "" : rs.getString(colName));
                        }
                    }
                }
                node = item.selectSingleNode(String.format("performance[@idperformance='%s']", rs.getString("idperformance")));
                if (node != null) {
                    item = (Element) node;
                } else {
                    item = item.addElement("performance");
                    for (String colName : performanceColumns) {
                        item.addAttribute(colName, rs.getString(colName));
                    }
                }
                item = item.addElement("place");
                for (String colName : placeColumns) {
                    item.addAttribute(colName, rs.getString(colName));
                }
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
