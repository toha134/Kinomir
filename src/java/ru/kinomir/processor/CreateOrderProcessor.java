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
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.CreateOrderData;
import ru.kinomir.datalayer.dto.DataNode;

/**
 *
 * @author Admin
 */
public class CreateOrderProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        CreateOrderData orderData = KinomirManager.createOrder(conn, params, logger);
        Element item = el.addElement("order");
        item.addAttribute("idOrder", orderData.getOrder().getIdOrder());
        item.addAttribute("PlaceCount", orderData.getOrder().getPlaceCount());
        item.addAttribute("Price", orderData.getOrder().getPrice());
        item.addAttribute("MarkUp", orderData.getOrder().getMarkUp());
    }
    
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException{
        return KinomirManager.createOrder(conn, params, logger);
    }
}
