/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "order")
public class CreateOrderOrder {

    @SerializedName("idOrder")
    private String idOrder;
    @SerializedName("placeCount")
    private String placeCount;
    @SerializedName("price")
    private String price;
    @SerializedName("markUp")
    private String markUp;
    @SerializedName("Description")
    private String Description;

    public CreateOrderOrder() {

    }

    public CreateOrderOrder(ResultSet rs) throws SQLException {
        idOrder = rs.getString("idOrder");
        placeCount = rs.getString("PlaceCount");
        price = rs.getString("Price");
        markUp = rs.getString("MarkUp");
        if (SqlUtils.hasColumn(rs, "Description")) {
            Description = rs.getString("Description");
        } else {
            Description = "";
        }
    }

    @XmlAttribute(name = "idOrder")
    public String getIdOrder() {
        return idOrder;
    }

    @XmlAttribute(name = "PlaceCount")
    public String getPlaceCount() {
        return placeCount;
    }

    @XmlAttribute(name = "Price")
    public String getPrice() {
        return price;
    }

    @XmlAttribute(name = "MarkUp")
    public String getMarkUp() {
        return markUp;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return Description;
    }

}
