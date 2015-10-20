/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.datalayer.dto.OrderInfoDTO;

/**
 *
 * @author user
 */
public class MyWebGetOrderInfoProcessor extends AbstractRequestProcessor {

    private static String[] orderColumns = {"begintime", "brokerage", "timepayment",
        "orderexpiretime", "orderstate", "orderprice", "orderpaysum", "orderrecalltickets",
        "saledticketssum", "saledtickets", "ordertotalticketssum", "ordertotaltickets", "description", "idclient", "orderpass"};
    private static String[] performanceColumns = {"showname", "idperformance", "performancestarttime", "hall", "building"};
    private static String[] placeColumns = {"idplace", "rownom", "placenom"};

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {
        OrderInfoDTO orderInfoDTO = null;

        orderInfoDTO = KinomirManager.getOrderInfo(conn, params);
        for (Map<String, String> orderLine : orderInfoDTO.getOrderInfo()) {
            Node node = el.selectSingleNode(String.format("//order[@IdOrder='%s']", orderLine.get("idorder")));
            Element item = null;
            if (node != null) {
                item = (Element) node;
                node = null;
            } else {
                item = el.addElement("order");
                item.addAttribute("IdOrder", orderLine.get("idorder"));
                for (String colName : orderColumns) {
                    if (colName.equals("ordertotalticketssum")) {
                        item.addAttribute(colName, orderLine.get(colName) == null ? "0" : Double.toString(Double.parseDouble(orderLine.get(colName))));
                    } else {
                        item.addAttribute(colName, orderLine.get(colName) == null ? "" : orderLine.get(colName));
                    }
                }
            }
            node = item.selectSingleNode(String.format("performance[@idperformance='%s']", orderLine.get("idperformance")));
            if (node != null) {
                item = (Element) node;
            } else {
                item = item.addElement("performance");
                for (String colName : performanceColumns) {
                    item.addAttribute(colName, orderLine.get(colName));
                }
            }
            item = item.addElement("place");
            for (String colName : placeColumns) {
                item.addAttribute(colName, orderLine.get(colName));
            }
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getOrderInfoData(conn, params);
    }

}
