package ru.kinomir.queue.data;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.kinomir.datalayer.dto.ClientInfoData;

/**
 *
 * @author Антон
 */
public class NewOrderMessage {

    @SerializedName("action")
    private final String action = "created_order";
    @SerializedName("order")
    private List<NewOrderInfo> order = new ArrayList<NewOrderInfo>();
    @SerializedName("date")
    private Date date = new Date();
    @SerializedName("clientData")
    ClientInfoData clientData;

    public List<NewOrderInfo> getOrder() {
        return order;
    }

    public void setOrder(List<NewOrderInfo> order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public ClientInfoData getClientData() {
        return clientData;
    }

    public void setClientData(ClientInfoData clientData) {
        this.clientData = clientData;
    }

}
