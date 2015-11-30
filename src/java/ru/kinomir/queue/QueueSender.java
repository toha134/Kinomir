/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.queue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.lang.reflect.Modifier;

import ru.kinomir.tools.KinomirLog;
import ru.kinomir.tools.StringTools;

/**
 *
 * @author Антон
 */
public class QueueSender {

    private static final KinomirLog logger = KinomirLog.getLogger(QueueSender.class);

    private static final QueueSender instance = new QueueSender();

    private QueueSender() {

    }

    public static QueueSender getInstance() {
        return instance;
    }

    public synchronized void sendToQueue(Object data, String queueName, String queueHost, String userName, String password, String port, String virtualHost) {
        Channel channel = null;
        Connection connection = null;
        try {
            logger.info("Send message to queue");
            ConnectionFactory factory = new ConnectionFactory();
            if (!StringTools.isEmpty(userName)) {
                factory.setUsername(userName);
            }
            if (!StringTools.isEmpty(password)) {
                factory.setPassword(password);
            }
            if (!StringTools.isEmpty(port)) {
                try {
                    factory.setPort(Integer.parseInt(port));
                } catch (NumberFormatException ignore) {

                }
            }
            if (!StringTools.isEmpty(virtualHost)) {
                factory.setVirtualHost(virtualHost);
            }
            factory.setHost(queueHost);
            
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            String message = convertToString(data);
            channel.basicPublish("", queueName, null, message.getBytes());
            logger.info("Message was sent");
        } catch (Exception ex) {
            logger.error("Uneble send message: " + convertToString(data));
            logger.debug(ex.getMessage(), ex);
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ignore) {

            }
        }
    }

    private static String convertToString(Object message) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
        return gson.toJson(message);
    }

}
