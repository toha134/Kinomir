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
import java.util.Date;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import static ru.kinomir.datalayer.KinomirManager.BIRTHDAY;
import static ru.kinomir.datalayer.KinomirManager.CELLULAR;
import static ru.kinomir.datalayer.KinomirManager.CITY;
import static ru.kinomir.datalayer.KinomirManager.EMAIL;
import static ru.kinomir.datalayer.KinomirManager.FIRSTNAME;
import static ru.kinomir.datalayer.KinomirManager.IDDOCUMENT;
import static ru.kinomir.datalayer.KinomirManager.LOGIN;
import static ru.kinomir.datalayer.KinomirManager.NAME;
import static ru.kinomir.datalayer.KinomirManager.PASSWORD;
import static ru.kinomir.datalayer.KinomirManager.PATRONYMIC;
import static ru.kinomir.datalayer.KinomirManager.PHONE;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.datalayer.dto.NewClientData;
import ru.kinomir.queue.QueueSender;
import ru.kinomir.queue.data.Account;
import ru.kinomir.queue.data.RegisterAccountMessage;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class MyWebCreateClient extends AbstractRequestProcessor {

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
            if (rs.next()) {
                Element item = el.addElement("result");
                item.addAttribute("IdClient", Integer.toString(rs.getInt("IdClient")));
                item.addAttribute("Name", rs.getString("Name"));
                item.addAttribute("Token", rs.getString("Token"));
                item.addAttribute("EmailCode", rs.getString("EmailCode"));
                item.addAttribute("CellularCode", rs.getString("CellularCode"));

                Account account = new Account();
                account.setCellular(params.get(CELLULAR));
                try {
                    account.setBirthday(df.format(df.parse(params.get(BIRTHDAY)).getTime()));
                } catch (Exception ignore) {
                }
                account.setCity(params.get(CITY));
                account.setEmail(params.get(EMAIL));
                account.setFirstName(params.get(FIRSTNAME));
                account.setLogin(params.get(LOGIN));
                account.setName(params.get(NAME));
                account.setPassword(params.get(PASSWORD));
                account.setPhone(params.get(PHONE));
                account.setPatronymic(params.get(PATRONYMIC));
                RegisterAccountMessage regAcc = new RegisterAccountMessage();
                regAcc.addAccount(account);
                regAcc.setAction("registration");
                regAcc.setCellularActivationCode(rs.getString("CellularCode"));
                regAcc.setDate(new Date());
                regAcc.setEmailActivationCode(rs.getString("EmailCode"));
                regAcc.setToken(rs.getString("Token"));
                sendToQueue(regAcc, QUEUE_SMS);
                sendToQueue(regAcc, QUEUE_EMAIL);
            }
        } catch (SQLException ex) {
            if (rs != null && SqlUtils.hasColumn(rs, "ErrorDescription")) {
                throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
            } else {
                throw new DataException("1", "Unknown error", ex);
            }
        } catch (UnsupportedEncodingException ex) {
            throw new DataException("1", "Wrong data", ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        NewClientData node = KinomirManager.createClient(conn, params, df);
        Account account = new Account();
        account.setCity(params.get(CITY));
        account.setEmail(params.get(EMAIL));
        account.setFirstName(params.get(FIRSTNAME));
        account.setLogin(params.get(LOGIN));
        account.setName(params.get(NAME));
        account.setPassword(params.get(PASSWORD));
        account.setPhone(params.get(PHONE));
        account.setPatronymic(params.get(PATRONYMIC));
        RegisterAccountMessage regAcc = new RegisterAccountMessage();
        regAcc.addAccount(account);
        regAcc.setAction("registration");
        regAcc.setCellularActivationCode(node.getClient().getCellularCode());
        regAcc.setDate(new Date());
        regAcc.setEmailActivationCode(node.getClient().getEmailCode());
        regAcc.setToken(node.getClient().getToken());
        sendToQueue(regAcc, QUEUE_SMS);
        sendToQueue(regAcc, QUEUE_EMAIL);
        return node;
    }

}
