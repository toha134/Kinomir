
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
import ru.kinomir.datalayer.dto.LockPlaceDTO;

/**
 *
 * @author Admin
 */
public class LockPlaceProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {

        LockPlaceDTO lockPlaceDTO = KinomirManager.lockPlace(conn, params);
        Element item = el.addElement("price");
        item.setText(lockPlaceDTO.getPrice());
    }
}
