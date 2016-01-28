/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class CreateOrderData extends DataNode {

    @SerializedName("order")
    private CreateOrderOrder order = null;
    @SerializedName("clientData")
    private ClientInfoData clientData = null;

    public CreateOrderData() {

    }

    public CreateOrderData(ResultSet rs) throws SQLException {
        try {
            if (rs.next()) {
                order = new CreateOrderOrder(rs);
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        }
    }

    @XmlElement(name = "order")
    public CreateOrderOrder getOrder() {
        return order;
    }

    @XmlElement(name = "clientData")
    public ClientInfoData getClientData() {
        return clientData;
    }

    public void setClientData(ClientInfoData clientData) {
        this.clientData = clientData;
    }

}
