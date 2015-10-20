/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class MyWebCreateClient extends AbstractRequestProcessor {

	public static final String IDDOCUMENT = "IDDOCUMENT";
	public static final String LOGIN = "LOGIN";
	public static final String NAME = "NAME";
	public static final String FIRSTNAME = "FIRSTNAME";
	public static final String PATRONYMIC = "PATRONYMIC";
	public static final String PHONE = "PHONE";
	public static final String CELLULAR = "CELLULAR";
	public static final String EMAIL = "EMAIL";
	public static final String CITY = "CITY";
	public static final String BIRTHDAY = "BIRTHDAY";
    public static final String PASSWORD = "PASSWORD";

	@Override
	protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {
//          @IdDocument declare @IdDocument T_ID_Small declare @Login varchar(16) declare @Name varchar(64) declare @FirstName varchar(36) declare 
//          @Patronymic varchar(36) declare @Phone varchar(20) declare @Cellular varchar(20) declare @Email varchar(64) declare @City T_NameString 
//        declare @Birthday T_DATE_TIME  
//        select @IdDocument = null select @Login = null select @Name = null select @FirstName = null select @Patronymic = null 
//        select @Phone = null select @Cellular = null select @Email = null select @City = null select @Birthday = null  
//        execute dbo.MyWeb_CreateClient @IdDocument, @Login, @Name, @FirstName, @Patronymic, @Phone, @Cellular, @Email, @City, @Birthday  
//                ответ: OK: @IdClient as IdClient, @o_Name as Name 
//                       Error:    ErrorCode as Error,Description as ErrorDescription 
		PreparedStatement sp = null;
		ResultSet rs = null;

		try {
			sp = conn.prepareStatement("exec dbo.MyWeb_CreateClient ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?");
			if (!isEmpty(params.get(IDDOCUMENT))) {
				sp.setShort(1, Short.parseShort(params.get(IDDOCUMENT)));
			} else {
				sp.setNull(1, java.sql.Types.SMALLINT);
			}
			if (!isEmpty(params.get(LOGIN))) {
				sp.setString(2, URLDecoder.decode(params.get(LOGIN), "UTF-8"));
			} else {
				sp.setNull(2, java.sql.Types.VARCHAR);
			}
			if (params.get(NAME) != null) {
				sp.setString(3, URLDecoder.decode(params.get(NAME), "UTF-8"));
			} else {
				sp.setNull(3, java.sql.Types.VARCHAR);
			}
			if (params.get(FIRSTNAME) != null) {
				sp.setString(4, URLDecoder.decode(params.get(FIRSTNAME), "UTF-8"));
			} else {
				sp.setNull(4, java.sql.Types.VARCHAR);
			}
			if (params.get(PATRONYMIC) != null) {
				sp.setString(5, URLDecoder.decode(params.get(PATRONYMIC), "UTF-8"));
			} else {
				sp.setNull(5, java.sql.Types.VARCHAR);
			}
			if (params.get(PHONE) != null) {
				sp.setString(6, params.get(PHONE));
			} else {
				sp.setNull(6, java.sql.Types.VARCHAR);
			}
			if (params.get(CELLULAR) != null) {
				sp.setString(7, URLDecoder.decode(params.get(CELLULAR), "UTF-8"));
			} else {
				sp.setNull(7, java.sql.Types.VARCHAR);
			}
			if (params.get(EMAIL) != null) {
				sp.setString(8, URLDecoder.decode(params.get(EMAIL), "UTF-8"));
			} else {
				sp.setNull(8, java.sql.Types.VARCHAR);
			}
			if (params.get(CITY) != null) {
				sp.setString(9, URLDecoder.decode(params.get(CITY), "UTF-8"));
			} else {
				sp.setNull(9, java.sql.Types.VARCHAR);
			}
			if (params.get(BIRTHDAY) != null) {
				try {
					sp.setTimestamp(10, new Timestamp(df.parse(params.get(BIRTHDAY)).getTime()));
				} catch (ParseException ex) {
					sp.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
				}
			} else {
				sp.setNull(10, java.sql.Types.TIMESTAMP);
			}
            if (params.get(PASSWORD) != null) {
				sp.setString(11, URLDecoder.decode(params.get(PASSWORD), "UTF-8"));
			} else {
				sp.setNull(11, java.sql.Types.VARCHAR);
			}
			rs = sp.executeQuery();
			while (rs.next()) {
				Element item = el.addElement("result");
				item.addAttribute("IdClient", Integer.toString(rs.getInt("IdClient")));
				item.addAttribute("Name", rs.getString("Name"));
			}
		} catch (SQLException ex) {
			if (rs != null) {
				throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
			} else {
				throw new DataException("1", "No data", ex);
			}
		} catch (UnsupportedEncodingException ex) {
			throw new DataException("1", "Wrong data", ex);
		} finally {
			SqlUtils.closeSQLObjects(rs, sp);
		}
	}

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.createClient(conn, params, df);
    }
    
    
}
