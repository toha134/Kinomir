/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.KinomirLog;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class AddPaymentResultDTO extends DataNode {

    @SerializedName("result")
	private SimpleResult result;
	private String paymentId = "";

	public AddPaymentResultDTO() {
	}

	public AddPaymentResultDTO(ResultSet rs, KinomirLog logger) throws SQLException, DataException {
		try {
			if (rs.next()) {
				result = new SimpleResult(rs);
				paymentId = rs.getString("IdPayment");
				do {
					if (rs.getMetaData().getColumnCount() == 3) {
						logger.debug(String.format("%1$s %2$s %3$s", new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)}));
					} else {
						for (int i = 1; i<=rs.getMetaData().getColumnCount(); i++){
							logger.debug(rs.getMetaData().getColumnName(i)+" = "+rs.getString(i));
						}
					}
				} while (rs.next());
			} else {
                throw new DataException("1", "No result from SP");
			}
		} catch (SQLException ex) {
				throw SqlUtils.convertErrorToException(rs, ex);
		}
	}


    @XmlElement(name = "result")
	public SimpleResult getResult() {
		return result;
	}

	public void setResult(SimpleResult result) {
		this.result = result;
	}

    @XmlElement(name = "idpayment")
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
}
