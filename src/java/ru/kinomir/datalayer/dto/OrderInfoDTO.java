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
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class OrderInfoDTO {

    private static String[] orderColumns = {"begintime", "brokerage", "timepayment",
        "orderexpiretime", "orderstate", "orderprice", "orderpaysum", "orderrecalltickets",
        "saledticketssum", "saledtickets", "ordertotalticketssum", "ordertotaltickets", "description", "idclient"};
    private static String[] performanceColumns = {"showname", "idperformance", "performancestarttime", "hall", "building"};
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
                    orderLine.put(colName, rs.getString(colName));
                }
                for (String colName : performanceColumns) {
                    orderLine.put(colName, rs.getString(colName));
                }
                for (String colName : placeColumns) {
                    orderLine.put(colName, rs.getString(colName));
                }
                orderInfo.add(orderLine);
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }
    
    public List<Map<String, String>> getOrderInfo(){
        return orderInfo;
    }
}
