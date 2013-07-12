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
public class GetClientInfoProcessor extends AbstractRequestProcessor {

    private final static String[] columns = {"idclient", "iddocument", "F", "I", "O", "address", "phone",
        "secaddress", "city", "email", "fax", "addstring", "Cellular", "BeginTime", "BarCode", "AccSkidka",
        "Description", "Description2", "Description3", "Birthday", "Login", "DocName", "OperationLimit", "OrderLife",
        "OrderLifeBeforePerformance", "IdSchBA", "ShowLimit", "PerformanceLimit", "UseAccSkidka", "DiskountName", "Percent", "isBlocFastSale"};

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        ResultSet rs = KinomirManager.getClientInfo(conn, params);
        try {
            while (rs.next()) {
                Element item = el.addElement("client");
                for (String column : columns) {
                    try {
                        item.addElement(column).addText(rs.getObject(column) == null ? "" : rs.getString(column));
                    } catch (SQLException ex) {
                        logger.error("Column '" + column + "' not found!", ex);
                    }
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
