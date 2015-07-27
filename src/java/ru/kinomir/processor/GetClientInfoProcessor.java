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
import ru.kinomir.datalayer.dto.ClientInfoDTO;
import ru.kinomir.datalayer.dto.DataNode;

/**
 *
 * @author Admin
 */
public class GetClientInfoProcessor extends AbstractRequestProcessor {

	private final static String[] columns = {"idclient", "iddocument", "F", "I", "O", "address", "phone",
		"secaddress", "city", "email", "fax", "addstring", "Cellular", "BeginTime", "BarCode", "AccSkidka",
		"Description", "Description2", "Description3", "Birthday", "Login", "DocName", "OperationLimit", "OrderLife",
		"OrderLifeBeforePerformance", "IdSchBA", "ShowLimit", "PerformanceLimit", "UseAccSkidka", "DiskountName", "Percent", "isBlocFastSale"};

	@Override
	protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {

		ClientInfoDTO clientInfoDTO = KinomirManager.getClientInfo(conn, params);

		if (clientInfoDTO.getClientInfoField("idclient") != null) {
			Element item = el.addElement("client");
			for (String column : columns) {
				item.addElement(column).addText(clientInfoDTO.getClientInfoField(column) == null ? "" : clientInfoDTO.getClientInfoField(column));
			}
		} else {
			throw new DataException("1", "No data");
		}
	}

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getClientInfoData(conn, params);
    }
    
    
}
