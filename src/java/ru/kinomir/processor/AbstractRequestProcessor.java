/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import ru.kinomir.format.ResultFormatter;
import ru.kinomir.format.FormatterFactory;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.queue.QueueSender;
import ru.kinomir.tools.KinomirLog;

/**
 *
 * @author Admin
 */
public abstract class AbstractRequestProcessor {

    protected static final String QUEUE_EMAIL = "email";
    protected static final String QUEUE_SMS = "sms";
    protected static final String QUEUE_TICKET = "ticket";
    /**
     *
     */
    protected KinomirLog logger;
    private Map<String, String> initParams;

    public void setLogger(KinomirLog logger) {
        this.logger = logger;
    }
    SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();

    public String processQuery(Connection conn, Map<String, String> params, Map<String, String> initParams) throws Exception {
        this.initParams = initParams;
        logger.info("Start prcessing with " + this.getClass().getName());
        df.applyPattern("MM/dd/yyyy HH:mm:ss");
        if ((params.get("FORMAT") != null)) {
            ResultFormatter formatter = FormatterFactory.getFormatter(params.get("FORMAT"));
            String result;
            try {
                DataNode data = getData(conn, params);
                logger.info("Process success");
                result = formatter.format(data);

            } catch (SQLException ex) {
                logger.error("Processing error: " + ex.getMessage());
                logger.debug("Processing error: " + ex.getMessage(), ex);
                result = formatter.format(ex);
            } catch (DataException ex) {
                logger.error("Processing error: " + ex.getMessage());
                logger.debug("Processing error: " + ex.getMessage(), ex);
                result = formatter.format(ex);
            } catch (Exception ex) {
                logger.error("Processing error: " + ex.getMessage());
                logger.debug("Processing error: " + ex.getMessage(), ex);
                result = formatter.format(ex, true);
            }
            logger.debug("Process result: " + result);
            return result;
        } else {
            Document resXML = DocumentHelper.createDocument();
            Element el = resXML.addElement("data");
            try {
                fillAnswerData(conn, params, el);
                logger.debug("Process result: " + el.asXML());
                logger.info("Process success");
            } catch (DataException ex) {
                el.addAttribute("error", ex.getErrorCode());
                el.addAttribute("errorMessage", ex.getMessage());
                logger.error("Processing error: " + ex.getMessage());
                logger.debug("Processing error: " + ex.getMessage(), ex);
            } catch (SQLException ex) {
                el.addAttribute("error", ex.getSQLState());
                el.addAttribute("errorMessage", ex.getMessage());
                logger.error("Processing error: " + ex.getMessage());
                logger.debug("Processing error: " + ex.getMessage(), ex);
            } catch (Exception ex) {
                el.addAttribute("error", "0");
                el.addAttribute("errorMessage", ex.getMessage());
                logger.error("General processing error: " + ex.getMessage());
                logger.debug("General processing error: " + ex.getMessage(), ex);
            }
            return resXML.asXML();
        }
    }

    protected abstract void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException;

    protected String getInitParam(String name) {
        if (initParams != null) {
            return initParams.get(name);
        }
        return null;
    }

    public boolean isEmpty(String value) {
        return (value == null) || "".equals(value);
    }

    protected abstract DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException;

    protected void sendToQueue(Object value, String queue) {
        if (QUEUE_SMS.equals(queue)) {
            QueueSender.getInstance().sendToQueue(value, getInitParam("smsQueueName"), getInitParam("smsQueueHost"), getInitParam("smsQueueUser"), getInitParam("smsQueuePassword"), getInitParam("smsQueuePort"), getInitParam("smsQueueVirtualHost"));
        } else if (QUEUE_EMAIL.equals(queue)) {
            QueueSender.getInstance().sendToQueue(value, getInitParam("emailQueueName"), getInitParam("emailQueueHost"), getInitParam("emailQueueUser"), getInitParam("emailQueuePassword"), getInitParam("emailQueuePort"), getInitParam("emailQueueVirtualHost"));
        } else if (QUEUE_TICKET.equals(queue)) {
            QueueSender.getInstance().sendToQueue(value, getInitParam("ticketQueueName"), getInitParam("ticketQueueHost"), getInitParam("ticketQueueUser"), getInitParam("ticketQueuePassword"), getInitParam("ticketQueuePort"), getInitParam("ticketQueueVirtualHost"));
        }
    }

}
