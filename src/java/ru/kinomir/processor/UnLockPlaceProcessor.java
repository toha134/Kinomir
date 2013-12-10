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
import ru.kinomir.datalayer.dto.SimpleResultDTO;

/**
 *
 * @author Admin
 */
public class UnLockPlaceProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        SimpleResultDTO result = KinomirManager.unlockPlace(conn, params);
        Element item = el.addElement("result");
        item.addAttribute("Result", result.getResult());
        item.addAttribute("ResultDescription", result.getResultDescription());
    }
}
