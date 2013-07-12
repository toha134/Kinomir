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
public class AddPaymentProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        
        ResultSet rs = KinomirManager.addPayment(conn, params);
        try {
            while (rs.next()) {
                Element item = el.addElement("result");
                item.addAttribute("Result", rs.getString("Result"));
                item.addAttribute("ResultDescription", rs.getString("ResultDescription"));
                el.addElement("idpayment", rs.getString("IdPayment"));
            }
        } catch (SQLException ex){
            if (ex.getSQLState().equals("S0022")){
                throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
            } else {
                throw ex;
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
