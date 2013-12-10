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
public class LockPlaceDTO {

    private String price;

    public LockPlaceDTO(ResultSet rs) throws SQLException{
        if (rs == null)
            throw new SQLException("No data for query");
        try {
            if (rs.next()) {
                price = rs.getString("Price");
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    public String getPrice() {
        return price;
    }
}
