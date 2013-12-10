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
public class SimpleResultDTO {

    private final String result;
    private final String resultDescription;

    public SimpleResultDTO(ResultSet rs) throws SQLException {
        if (rs == null)
            throw new SQLException("No data for query");
        if (rs.next()) {
            try {
                result = rs.getString("Result");
                resultDescription = rs.getString("ResultDescription");
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new SQLException("No data for query");
        }
    }

    public String getResult() {
        return result;
    }

    public String getResultDescription() {
        return resultDescription;
    }
    
    
}
