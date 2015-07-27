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
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.datalayer.dto.SimpleResultData;

/**
 *
 * @author Admin
 */
			 
public class SetOrderDescriptionProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        SimpleResultData result = KinomirManager.setOrderDescription(conn, params);

        Element item = el.addElement("result");
        item.addAttribute("Result", result.getResult().getResult());
        item.addAttribute("ResultDescription", result.getResult().getResultDescription());
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.setOrderDescription(conn, params);
    }
    
    
}
