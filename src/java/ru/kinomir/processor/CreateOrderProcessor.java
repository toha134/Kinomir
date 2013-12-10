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
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.CreateOrderDTO;

/**
 *
 * @author Admin
 */
public class CreateOrderProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        CreateOrderDTO createOrderDTO = KinomirManager.createOrder(conn, params, logger);
        Element item = el.addElement("order");
        item.addAttribute("idOrder", createOrderDTO.getIdOrder());
        item.addAttribute("PlaceCount", createOrderDTO.getPlaceCount());
        item.addAttribute("Price", createOrderDTO.getPrice());
        item.addAttribute("MarkUp", createOrderDTO.getMarkUp());

    }
}
