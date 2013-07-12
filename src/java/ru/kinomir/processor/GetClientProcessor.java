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

/**
 *
 * @author Admin
 */
public class GetClientProcessor extends AbstractRequestProcessor {

    private final static String[] columns = {"IdClient", "IdDocument", "Name", "Description", "Type", "Address",
        "Phone", "INN", "RS", "AcountBank", "KS", "BIK", "OKONH", "OKPO", "SecAddress", "City",
        "Email", "Fax", "FirstName", "Patronymic", "AddString", "IsStopped", "IsHidden", "IdCurInvoice", "Cellular",
        "OrderNumber", "InstId", "IdExtClient", "Foto", "CardNumber", "EndTime", "BeginTime", "CardState", "PrevIdClient",
        "Initiator", "CardDescription", "CounterTSTransfer", "BarCode", "Balans", "AvansFromZU", "AccSkidka", "PName",
        "Description2", "Description3", "Description4", "kpid", "Birthday", "CouldSaleAlienTickets", "SecretKey", "IdUserCardOperation",
        "DataTimeCardOper", "NoteCauseOperCard", "Login"};

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        PreparedStatement sp = conn.prepareStatement("exec dbo.Wga_GetClient ?");
        if (params.get("IDBUILDING") != null) {
            sp.setString(1, params.get("EMAIL"));
        } else {
            sp.setNull(1, java.sql.Types.VARCHAR);
        }
        ResultSet rs = sp.executeQuery();
        try {
            while (rs.next()) {
                Element item = el.addElement("client");
                for (String column : columns) {
                    item.addElement(column, rs.getObject(column) == null ? "" : rs.getObject(column).toString());
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (sp != null) {
                sp.close();
            }
        }
    }
}
