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
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class MyWebShowInfoProcessor extends AbstractRequestProcessor {

    private final static String[] columns = {"IdShow", "IdShowType", "Producer", "Painter",
        "Description", "Remark", "Name", "Duration", "PremiereDate", "KDMTP", "IsHidden",
        "EnableSound", "IdSupplier", "InstId", "IdExtShow", "IdGenre", "Actors",
        "IdRollerman", "rol_name", "pict_name", "pu_number", "DateLastDisplay", "Creator",
        "NameEng", "genre_name"};

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_ShowInfo ?");
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            while (rs.next()) {
                Element item = el.addElement("show");
                for (String column : columns) {
                    try {
                        item.addElement(column).addText(rs.getObject(column) == null ? "" : rs.getString(column));
                    } catch (SQLException ex) {
                        logger.error("Column '" + column + "' not found!", ex);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }
}
