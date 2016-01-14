/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class OrderInfoDTO {

    private static String[] orderColumns = {"idorder", "begintime", "brokerage", "timepayment",
        "orderexpiretime", "orderstate", "orderprice", "orderpaysum", "orderrecalltickets",
        "saledticketssum", "saledtickets", "ordertotalticketssum", "ordertotaltickets", "description", "idclient", "paymentmethod", "amount", "paydocnum", "attributes", "rrn", "idpaymentmethod"};
    private static String[] performanceColumns = {"showname", "idperformance", "performancestarttime", "hall", "building", "idbuilding"};
    private static String[] placeColumns = {"idplace", "rownom", "placenom"};
    private List<Map<String, String>> orderInfo = new ArrayList<Map<String, String>>();

    public OrderInfoDTO(ResultSet rs) throws DataException, SQLException {
        if (rs == null) {
            throw new DataException("1", "No data for order");
        }
        try {
            while (rs.next()) {
                Map<String, String> orderLine = new HashMap<String, String>();
                for (String colName : orderColumns) {
                    if (SqlUtils.hasColumn(rs, colName))
                        orderLine.put(colName, rs.getString(colName));
                }
                if (rs.getInt("orderstate") != 3) {
                    for (String colName : performanceColumns) {
                        if (SqlUtils.hasColumn(rs, colName))
                            orderLine.put(colName, rs.getString(colName));
                    }
                    for (String colName : placeColumns) {
                        if (SqlUtils.hasColumn(rs, colName))
                            orderLine.put(colName, rs.getString(colName));
                    }
                }
                String description = rs.getString("description");
                if ((description != null) && (description.length() > 3)) {
                    description = description.substring(description.length() - 4);
                }
                orderLine.put("orderpass", description);
                orderInfo.add(orderLine);
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    public List<Map<String, String>> getOrderInfo() {
        return orderInfo;
    }
}
