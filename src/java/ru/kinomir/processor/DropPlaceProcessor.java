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
import ru.kinomir.datalayer.dto.SimpleErrorData;


/**
 *
 * @author Admin
 */
public class DropPlaceProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        SimpleErrorData result = KinomirManager.dropPlace(conn, params);
        el.addAttribute("Error", result.getError());
        el.addAttribute("ErrorDescription", result.getErrorDescription());
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.dropPlace(conn, params);
    }
    
    
}
