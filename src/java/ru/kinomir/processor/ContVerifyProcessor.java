/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.ContVerifyResult;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.tools.StringTools;

/**
 *
 * @author Антон
 */
public class ContVerifyProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {
        ContVerifyResult contVerify = KinomirManager.contVerify(conn, params);
        el.addAttribute("result", contVerify.getResult());
        el.addAttribute("Token", contVerify.getToken());
        el.addAttribute("Error", contVerify.getError());
        el.addAttribute("ErrorDescription", contVerify.getErrorDescription());
        if (!StringTools.isEmpty(contVerify.getToken())) {
            el.addElement("token").setText(contVerify.getToken());
            Element clientDataEl = el.addElement("clientData");
            params.put(KinomirManager.TOKEN, contVerify.getToken());
            new GetClientInfoByTokenProcessor().fillAnswerData(conn, params, clientDataEl);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        ContVerifyResult result = KinomirManager.contVerify(conn, params);
        if (!StringTools.isEmpty(result.getToken())) {
            params.put(KinomirManager.TOKEN, result.getToken());
            DataNode clientData = new GetClientInfoByTokenProcessor().getData(conn, params);
            result.setClientData(clientData);
        }
        return result;
    }
    
}
