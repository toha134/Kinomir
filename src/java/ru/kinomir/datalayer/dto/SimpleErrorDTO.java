/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class SimpleErrorDTO {

    private String error;
    private String errorDescription;

    public SimpleErrorDTO(ResultSet rs) throws SQLException {
        if (rs == null)
            throw new SQLException("No data for query");
        try {
            while (rs.next()) {
                error = rs.getString("Error");
                errorDescription = rs.getString("ErrorDescription");
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
