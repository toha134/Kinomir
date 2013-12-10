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
public class CreateOrderDTO {

    private String idOrder;
    private final String placeCount;
    private final String price;
    private final String markUp;

    public CreateOrderDTO(ResultSet rs) throws SQLException {
        try {
        idOrder = rs.getString("idOrder");
        placeCount = rs.getString("PlaceCount");
        price = rs.getString("Price");
        markUp = rs.getString("MarkUp");
        } catch (SQLException ex){
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getPlaceCount() {
        return placeCount;
    }

    public String getPrice() {
        return price;
    }

    public String getMarkUp() {
        return markUp;
    }
}
