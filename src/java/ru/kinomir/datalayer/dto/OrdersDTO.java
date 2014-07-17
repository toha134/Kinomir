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
public class OrdersDTO {

	private final static String[] orderColumns = {"idorder", "ordercreatetime", "brokerage", "timepayment",
		"orderexpiretime", "orderstate", "orderprice", "orderpaysum", "orderrecalltickets", "saledticketssum",
		"saledtickets", "ordertotalticketssum", "ordertotaltickets", "name", "firstname", "patronymic", "phone", "email", "fax", "cellular", "birthday", "orderdescription", "orderpass"};
	private final static String[] performanceColumns = {"showname", "idperformance", "performancestarttime", "hall", "building", "idshow"};
	private final static String[] placeColumns = {"idplace", "rownom", "placenom"};
	private List<Map<String, String>> ordersInfo = new ArrayList<Map<String, String>>();

	public OrdersDTO(ResultSet rs) throws DataException, SQLException {
		if (rs == null) {
			throw new DataException("2", "No data");
		}
		try {
			while (rs.next()) {
				try {
					throw new DataException(rs.getString("Error"), rs.getString("ErrorDescription"));
				} catch (SQLException ex) {
				}
				Map<String, String> orderLine = new HashMap<String, String>();
				for (String colName : orderColumns) {
					try {
						orderLine.put(colName, rs.getString(colName));
					} catch (SQLException ex) {
					}
				}
				for (String colName : performanceColumns) {
					try {

						orderLine.put(colName, rs.getString(colName));
					} catch (SQLException ex) {
					}

				}
				for (String colName : placeColumns) {
					try {

						orderLine.put(colName, rs.getString(colName));
					} catch (SQLException ex) {
					}

				}
				ordersInfo.add(orderLine);
			}

		} catch (SQLException ex) {
			throw SqlUtils.convertErrorToException(rs, ex);
		}
	}

	public List<Map<String, String>> getOrdersInfo() {
		return ordersInfo;
	}
}
