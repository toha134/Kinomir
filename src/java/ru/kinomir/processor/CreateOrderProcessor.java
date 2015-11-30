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
import ru.kinomir.queue.QueueSender;
import ru.kinomir.queue.data.NewOrderInfo;
import ru.kinomir.queue.data.NewOrderMessage;
import ru.kinomir.tools.StringTools;

/**
 *
 * @author Admin
 */
public class CreateOrderProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {

        CreateOrderData orderData = KinomirManager.createOrder(conn, params, logger);
        Element item = el.addElement("order");
        item.addAttribute("idOrder", orderData.getOrder().getIdOrder());
        item.addAttribute("PlaceCount", orderData.getOrder().getPlaceCount());
        item.addAttribute("Price", orderData.getOrder().getPrice());
        item.addAttribute("MarkUp", orderData.getOrder().getMarkUp());
        sendToQueues(orderData);
    }

    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, DataException {
        CreateOrderData orderData = KinomirManager.createOrder(conn, params, logger);
        sendToQueues(orderData);
        return orderData;
    }

    private void sendToQueues(CreateOrderData orderData) {
        if (orderData != null && !StringTools.isEmpty(orderData.getOrder().getIdOrder())) {
            NewOrderInfo orderInfo = new NewOrderInfo();
            orderInfo.setDescription("");
            orderInfo.setIdOrder(orderData.getOrder().getIdOrder());
            orderInfo.setMarkUp(orderData.getOrder().getMarkUp());
            orderInfo.setPlaceCount(orderData.getOrder().getPlaceCount());
            orderInfo.setPrice(orderData.getOrder().getPrice());
            NewOrderMessage message = new NewOrderMessage();
            message.getOrder().add(orderInfo);
            sendToQueue(message, QUEUE_SMS);
            sendToQueue(message, QUEUE_EMAIL);
        }
    }
}
