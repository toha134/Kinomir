/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author Admin
 */
public abstract class AbstractRequestProcessor {

    /**
     *
     */
    protected Logger logger;
	private Map<String, String> initParams;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();

    public String processQuery(Connection conn, Map<String, String> params, Map<String, String> initParams) throws Exception {
		this.initParams = initParams;
        logger.info("Start prcessing with " + this.getClass().getName());
        df.applyPattern("MM/dd/yyyy HH:mm:ss");
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

    protected abstract void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException;
	
	protected String getInitParam(String name){
		if (initParams != null){
			return initParams.get(name);
		}
		return null;
	}
	
	public boolean isEmpty(String value){
		return (value == null) || "".equals(value);
	}
}
