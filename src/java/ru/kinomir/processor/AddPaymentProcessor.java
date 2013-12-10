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
import ru.kinomir.datalayer.dto.AddPaymentResultDTO;

/**
 *
 * @author Admin
 */
public class AddPaymentProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        AddPaymentResultDTO payResult = KinomirManager.addPayment(conn, params, logger);
        Element item = el.addElement("result");
        item.addAttribute("Result", payResult.getResult());
        item.addAttribute("ResultDescription", payResult.getResultDescription());
        el.addElement("idpayment", payResult.getPaymentId());


    }
}
