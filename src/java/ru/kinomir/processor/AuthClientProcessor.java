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
import ru.kinomir.datalayer.dto.AuthResult;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.datalayer.dto.SimpleErrorData;
import ru.kinomir.tools.StringTools;

/**
 *
 * @author Admin
 */
public class AuthClientProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {

        AuthResult result = KinomirManager.authClient(conn, params);
        if (!StringTools.isEmpty(result.getToken())) {
            el.addElement("token").setText(result.getToken());
            Element clientDataEl = el.addElement("clientData");
            params.put(KinomirManager.TOKEN, result.getToken());
            new GetClientInfoByTokenProcessor().fillAnswerData(conn, params, clientDataEl);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        AuthResult result = KinomirManager.authClient(conn, params);
        if (!StringTools.isEmpty(result.getToken())) {
            params.put(KinomirManager.TOKEN, result.getToken());
            DataNode clientData = new GetClientInfoByTokenProcessor().getData(conn, params);
            result.setClientData(clientData);
        }
        return result;

    }

}
