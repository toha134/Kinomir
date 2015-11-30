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
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.datalayer.dto.PasswordRestoreResult;
import ru.kinomir.queue.QueueSender;
import ru.kinomir.queue.data.RestoreData;
import ru.kinomir.tools.StringTools;

/**
 *
 * @author Антон
 */
public class PasswordRestoreProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {
        PasswordRestoreResult res = KinomirManager.restorePassword(conn, params);
        if ("OK".equalsIgnoreCase(res.getResult())) {
            sendConfirmCode(params, res);
        }
        el.addAttribute("result", res.getResult());
        el.addAttribute("EmailCode", res.getEmailCode());
        el.addAttribute("CellularCode", res.getCellularCode());
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PasswordRestoreResult res = KinomirManager.restorePassword(conn, params);
        if ("OK".equalsIgnoreCase(res.getResult())) {
            sendConfirmCode(params, res);
        }
        return res;
    }

    private void sendConfirmCode(Map<String, String> params, PasswordRestoreResult res) {
        if (StringTools.isEmpty(params.get(KinomirManager.PASSWORD))) {
            if (!StringTools.isEmpty(params.get(KinomirManager.AUTHTYPE))) {
                if (("email".equalsIgnoreCase(params.get(KinomirManager.AUTHTYPE)) && !StringTools.isEmpty(res.getEmailCode())) || !StringTools.isEmpty(res.getEmailCode())) {
                    RestoreData data = new RestoreData();
                    data.setAction("update_email");
                    data.setPassword(res.getEmailCode());
                    sendToQueue(data, QUEUE_EMAIL);
                }
                if (("cellular".equalsIgnoreCase(params.get(KinomirManager.AUTHTYPE)) && !StringTools.isEmpty(res.getCellularCode())) || !StringTools.isEmpty(res.getCellularCode())) {
                    RestoreData data = new RestoreData();
                    data.setAction("update_cellular");
                    data.setPassword(res.getCellularCode());
                    sendToQueue(data, QUEUE_SMS);
                }
            }

        }

    }

}
