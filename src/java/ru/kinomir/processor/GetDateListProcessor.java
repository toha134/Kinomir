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
import ru.kinomir.datalayer.dto.DataNode;

/**
 *
 * @author user
 */
public class GetDateListProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        PreparedStatement sp = conn.prepareStatement("exec dbo.MyWeb_GetDateList ?");
        if (params.get(KinomirManager.IDREGION) != null) {
            sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDREGION)));
        } else {
            sp.setNull(1, java.sql.Types.INTEGER);
        }
        ResultSet rs = sp.executeQuery();
        try {
            while (rs.next()) {
                el.addElement("date").setText(rs.getObject("pdt").toString());
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (sp != null) {
                sp.close();
            }
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getDateListData(conn, params);
    }

}
