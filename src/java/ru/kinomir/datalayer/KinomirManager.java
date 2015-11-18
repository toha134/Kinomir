/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer;

import ru.kinomir.datalayer.dto.OrdersDTO;
import ru.kinomir.datalayer.dto.LockPlaceDTO;
import ru.kinomir.datalayer.dto.OrderInfoDTO;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Map;
import ru.kinomir.tools.KinomirLog;
import ru.kinomir.datalayer.dto.AddPaymentResultDTO;
import ru.kinomir.datalayer.dto.AuthResult;
import ru.kinomir.datalayer.dto.BuildingData;
import ru.kinomir.datalayer.dto.ClientData;
import ru.kinomir.datalayer.dto.ClientInfoDTO;
import ru.kinomir.datalayer.dto.ClientInfoData;
import ru.kinomir.datalayer.dto.CreateOrderData;
import ru.kinomir.datalayer.dto.DateListData;
import ru.kinomir.datalayer.dto.GenreData;
import ru.kinomir.datalayer.dto.HallData;
import ru.kinomir.datalayer.dto.HallSchemaNCData;
import ru.kinomir.datalayer.dto.MoviesoonData;
import ru.kinomir.datalayer.dto.NewClientData;
import ru.kinomir.datalayer.dto.OrderInfoData;
import ru.kinomir.datalayer.dto.OrdersInfoData;
import ru.kinomir.datalayer.dto.PerfInfoData;
import ru.kinomir.datalayer.dto.PerformanceData;
import ru.kinomir.datalayer.dto.PerformanceNewData;
import ru.kinomir.datalayer.dto.PlacesData;
import ru.kinomir.datalayer.dto.PrintTicketData;
import ru.kinomir.datalayer.dto.ShowData;
import ru.kinomir.datalayer.dto.ShowInfoData;
import ru.kinomir.datalayer.dto.ShowNewData;
import ru.kinomir.datalayer.dto.SimpleErrorData;
import ru.kinomir.datalayer.dto.SimpleResultData;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.StringTools;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
public class KinomirManager {

    public static final String DISCOUNT = "DISCOUNT";
    public static final String IDCLIENT = "IDCLIENT";
    public static final String IFDISCOUNT = "IFDISCOUNT";
    public static final String ALLFIELDS = "ALLFIELDS";
    public static final String ASID = "ASID";
    public static final String IDBUILDING = "IDBUILDING";
    public static final String IDGENRE = "IDGENRE";
    public static final String IDHALL = "IDHALL";
    public static final String IDPERFORMANCE = "IDPERFORMANCE";
    public static final String IDSHOW = "IDSHOW";
    public static final String IDSHOWTYPE = "IDSHOWTYPE";
    public static final String BEGINTIME = "BEGINTIME";
    public static final String ENDTIME = "ENDTIME";
    public static final String IDPLACE = "IDPLACE";
    public static final String IDORDER = "IDORDER";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String WEEKS = "WEEKS";
    public static final String WITHPRICE = "WITHPRICE";
    public static final String ALLPLACES = "ALLPLACES";
    public static final String IDPRICECATEGORY = "IDPRICECATEGORY";
    public static final String EMAIL = "EMAIL";
    public static final String CARDNUM = "CARDNUM";
    public static final String IDUSER = "IDUSER";
    public static final String MARK = "MARK";
    public static final String IDPAYMENTMETHOD = "IDPAYMENTMETHOD";
    public static final String AMOUNT = "AMOUNT";
    public static final String BANKTRXID = "BANK_TRX_ID";
    public static final String PAYATTRIBYTES = "PAY_ATTRIBYTES";
    public static final String IDREGION = "IDREGION";
    public static final String IDDOCUMENT = "IDDOCUMENT";
    public static final String LOGIN = "LOGIN";
    public static final String NAME = "NAME";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String PATRONYMIC = "PATRONYMIC";
    public static final String PHONE = "PHONE";
    public static final String CELLULAR = "CELLULAR";
    public static final String CITY = "CITY";
    public static final String BIRTHDAY = "BIRTHDAY";
    public static final String PASSWORD = "PASSWORD";
    public static final String AUTHTYPE = "AUTHTYPE";
    private static final String TOKEN = "TOKEN";
    private static final String PUSHTOKEN = "PUSHTOKEN";
    private static final String APPVERSION = "APPVERSION";
    private static final String APPTYPE = "APPTYPE";
    private static final String DEVICETYPE = "DEVICETYPE";

    public static OrderInfoDTO getOrderInfo(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_GetOrderInfo ?");
            if (params.get(IDORDER) != null) {
                sp.setInt(1, Integer.parseInt(params.get(IDORDER)));
            }
            try {
                rs = sp.executeQuery();
            } catch (SQLException ex) {
                if (!"JZ0R2".equals(ex.getSQLState())) {
                    throw SqlUtils.convertErrorToException(rs, ex);
                }
            }
            return new OrderInfoDTO(rs);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (sp != null) {
                sp.close();
            }
        }
    }

    public static OrderInfoData getOrderInfoData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_GetOrderInfo ?");
            if (params.get(IDORDER) != null) {
                sp.setInt(1, Integer.parseInt(params.get(IDORDER)));
            }
            try {
                rs = sp.executeQuery();
            } catch (SQLException ex) {
                if (!"JZ0R2".equals(ex.getSQLState())) {
                    throw SqlUtils.convertErrorToException(rs, ex);
                }
            }
            return new OrderInfoData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static AddPaymentResultDTO addPayment(Connection conn, Map<String, String> params, KinomirLog logger) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_AddPayment ?, ?, ?, ?, ?, ?, ?");
            if (params.get(IDORDER) != null) {
                sp.setLong(1, Long.parseLong(params.get(IDORDER)));
            }
            if (params.get(AMOUNT) != null) {
                sp.setFloat(2, Float.parseFloat(params.get(AMOUNT)));
            }
            if (params.get(IDPAYMENTMETHOD) != null) {
                sp.setInt(3, Integer.parseInt(params.get(IDPAYMENTMETHOD)));
            }
            if (params.get(MARK) != null) {
                sp.setString(4, params.get(MARK));
            }
            if (params.get(IDUSER) != null) {
                sp.setLong(5, Long.parseLong(params.get(IDUSER)));
            }
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if (params.get(IDCLIENT) != null) {
                sp.setInt(5, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(5, java.sql.Types.INTEGER);
            }
            if (params.get(BANKTRXID) != null) {
                sp.setString(6, params.get(BANKTRXID));
            } else {
                sp.setNull(6, java.sql.Types.VARCHAR);
            }
            if (params.get(PAYATTRIBYTES) != null) {
                sp.setString(7, params.get(PAYATTRIBYTES));
            } else {
                sp.setNull(7, java.sql.Types.VARCHAR);
            }
            rs = sp.executeQuery();
            return new AddPaymentResultDTO(rs, logger);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static CreateOrderData createOrder(Connection conn, Map<String, String> params, KinomirLog logger) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_CreateOrder ?, ?, ?, ?");
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }

            if (params.get(IDCLIENT) != null) {
                sp.setInt(1, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }

            if (params.get(DESCRIPTION) != null) {
                try {
                    sp.setString(2, URLDecoder.decode(params.get(DESCRIPTION), "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    logger.error("Unable decode description field use empty string", ex);
                    sp.setString(2, "");
                }
            } else {
                sp.setNull(2, java.sql.Types.VARCHAR);
            }
            if (params.get(DISCOUNT) != null) {
                sp.setInt(3, Integer.parseInt(params.get(DISCOUNT)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            if ((params.get(IFDISCOUNT) != null) && (!(params.get(IFDISCOUNT)).isEmpty())) {
                int val = Integer.parseInt(params.get(IFDISCOUNT));
                sp.setBoolean(4, val > 0);
            } else {
                sp.setBoolean(4, false);
            }
            rs = sp.executeQuery();
            return new CreateOrderData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static SimpleErrorData dropPlace(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_DropPlace ?, ?");
            if (params.get(IDPERFORMANCE) != null) {
                sp.setLong(1, Long.parseLong(params.get(IDPERFORMANCE)));
            }
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }

            if (params.get(IDCLIENT) != null) {
                sp.setInt(2, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new SimpleErrorData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static ClientInfoDTO getClientInfo(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.get(CARDNUM) == null) {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetClientInfo ?, ?");
            } else {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetClientInfo ?, ?, ?");
                sp.setString(3, params.get(CARDNUM));
            }
            if (params.get(EMAIL) != null) {
                sp.setString(1, params.get(EMAIL));
            } else {
                sp.setNull(1, java.sql.Types.VARCHAR);
            }
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }

            if ((params.get(IDCLIENT) != null) && (!"".equals(params.get(IDCLIENT)))) {
                sp.setInt(2, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new ClientInfoDTO(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static ClientInfoData getClientInfoData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.get(CARDNUM) == null) {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetClientInfo ?, ?");
            } else {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetClientInfo ?, ?, ?");
                sp.setString(3, params.get(CARDNUM));
            }
            if (params.get(EMAIL) != null) {
                sp.setString(1, params.get(EMAIL));
            } else {
                sp.setNull(1, java.sql.Types.VARCHAR);
            }
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if ((params.get(IDCLIENT) != null) && (!"".equals(params.get(IDCLIENT)))) {
                sp.setInt(2, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new ClientInfoData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static LockPlaceDTO lockPlace(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.WgA_LockPlace ?, ?, ?, ?");
            if (params.get(IDPERFORMANCE) != null) {
                sp.setLong(1, Long.parseLong(params.get(IDPERFORMANCE)));
            }
            if (params.get(IDPLACE) != null) {
                sp.setInt(2, Integer.parseInt(params.get(IDPLACE)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }

            if (params.get(IDCLIENT) != null) {
                sp.setInt(3, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            if ((params.get(IFDISCOUNT) != null) && (!(params.get(IFDISCOUNT)).isEmpty())) {
                int val = Integer.parseInt(params.get(IFDISCOUNT));
                sp.setBoolean(4, val > 0);
            } else {
                sp.setBoolean(4, false);
            }
            rs = sp.executeQuery();
            return new LockPlaceDTO(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static OrdersDTO getOrders(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }

            if (params.containsKey(IDCLIENT)) {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetOrders ?, ?, ?");
                sp.setInt(3, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetOrders ?, ?");
            }
            setBeginTimeParameter(params, sp, df, logger, 1);
            setEndTimeParameter(params, sp, df, logger, 2);
            rs = sp.executeQuery();
            return new OrdersDTO(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static OrdersInfoData getOrdersData(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if (params.containsKey(IDCLIENT)) {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetOrders ?, ?, ?");
                sp.setInt(3, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp = conn.prepareStatement("exec dbo.MyWeb_GetOrders ?, ?");
            }
            setBeginTimeParameter(params, sp, df, logger, 1);
            setEndTimeParameter(params, sp, df, logger, 2);
            rs = sp.executeQuery();
            return new OrdersInfoData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

     public static SimpleResultData setOrderDescription(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_SetOrderDescription ?, ?");
            if (params.get(IDORDER) != null) {
                sp.setLong(1, Long.parseLong(params.get(IDORDER)));
            }
            if (params.get(DESCRIPTION) != null) {
                sp.setString(2, params.get(DESCRIPTION));
            } else {
                sp.setNull(2, java.sql.Types.VARCHAR);
            }
            rs = sp.executeQuery();
            return new SimpleResultData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static SimpleResultData setOrderToNull(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_SetOrderToNull ?");
            if (params.get(IDORDER) != null) {
                sp.setLong(1, Long.parseLong(params.get(IDORDER)));
            }
            rs = sp.executeQuery();
            return new SimpleResultData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static SimpleResultData unlockPlace(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_UnlockPlace ?, ?, ?");
            if (params.get(IDPERFORMANCE) != null) {
                sp.setLong(1, Long.parseLong(params.get(IDPERFORMANCE)));
            }
            if (params.get(IDPLACE) != null) {
                sp.setInt(2, Integer.parseInt(params.get(IDPLACE)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if (params.get(IDCLIENT) != null) {
                sp.setInt(3, Integer.parseInt(params.get(IDCLIENT)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new SimpleResultData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static void setBeginTimeParameter(Map<String, String> params, PreparedStatement sp, DateFormat df, KinomirLog logger, int place) throws SQLException {
        try {
            if (params.get(BEGINTIME) != null) {
                if (params.get(BEGINTIME).contains(":")) {
                    sp.setTimestamp(place, new Timestamp(df.parse(params.get(BEGINTIME)).getTime()));
                } else {
                    logger.debug("Converted begin date:" + df.format(df.parse(params.get(BEGINTIME) + " 00:00:00")));
                    sp.setTimestamp(place, new Timestamp(df.parse(params.get(BEGINTIME) + " 00:00:00").getTime()));
                }
            } else {
                sp.setNull(place, java.sql.Types.DATE);
            }
        } catch (Exception ex) {
            logger.error("Can't parse value for '" + BEGINTIME + "'", ex);
            sp.setNull(place, java.sql.Types.DATE);
        }
    }

    public static void setEndTimeParameter(Map<String, String> params, PreparedStatement sp, DateFormat df, KinomirLog logger, int place) throws SQLException {
        try {
            if (params.get(ENDTIME) != null) {
                if (params.get(ENDTIME).contains(":")) {
                    sp.setTimestamp(place, new Timestamp(df.parse(params.get(ENDTIME)).getTime()));
                } else {
                    logger.debug("Converted end date:" + df.format(df.parse(params.get(ENDTIME) + " 23:59:59")));
                    sp.setTimestamp(place, new Timestamp(df.parse(params.get(ENDTIME) + " 23:59:59").getTime()));
                }
            } else {
                sp.setNull(place, java.sql.Types.DATE);
            }
        } catch (ParseException ex) {
            logger.error("Can't parse value for '" + ENDTIME + "'", ex);
            sp.setNull(place, java.sql.Types.DATE);
        }
    }

    public static ClientData getClient(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetClient ?");
            if (params.get(EMAIL) != null) {
                sp.setString(1, params.get(EMAIL));
            } else {
                sp.setNull(1, java.sql.Types.VARCHAR);
            }
            rs = sp.executeQuery();
            return new ClientData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static MoviesoonData getMoviesons(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_moviesoon ?, ?");

            if (params.get(KinomirManager.WEEKS) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.WEEKS)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDREGION) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDREGION)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new MoviesoonData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static DateListData getDateListData(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_GetDateList ?");
            if (params.get(KinomirManager.IDREGION) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDREGION)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new DateListData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static GenreData getGenreData(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.containsKey(KinomirManager.IDREGION)) {
                sp = conn.prepareStatement("exec dbo.Wga_GetGenre ?");
            } else {
                sp = conn.prepareStatement("exec dbo.Wga_GetGenre");
            }
            if (params.containsKey(KinomirManager.IDREGION)) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDREGION)));
            }
            rs = sp.executeQuery();
            return new GenreData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static NewClientData createClient(Connection conn, Map<String, String> params, DateFormat df) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;

        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_CreateClient ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?");
            if (!StringTools.isEmpty(params.get(IDDOCUMENT))) {
                sp.setShort(1, Short.parseShort(params.get(IDDOCUMENT)));
            } else {
                sp.setNull(1, java.sql.Types.SMALLINT);
            }
            if (!StringTools.isEmpty(params.get(LOGIN))) {
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
            return new NewClientData(rs);
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

    public static BuildingData getZUInfo(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_getzuinfo ?");
            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                throw new InvalidParameterException("Parameter '" + KinomirManager.IDBUILDING + "' not found!");
            }
            rs = sp.executeQuery();
            return new BuildingData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static PerfInfoData getPerfInfo(Connection conn, Map<String, String> params) throws SQLException, DataException {
        ResultSet rs = null;
        PreparedStatement sp = null;

        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetPerfInfo ?");

            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
            } else {
                throw new InvalidParameterException("Parameter '" + KinomirManager.IDPERFORMANCE + "' not found!");
            }
            rs = sp.executeQuery();

            return new PerfInfoData(rs);

        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static PlacesData getPlaces(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if (params.get(KinomirManager.IDCLIENT) != null) {
                sp = conn.prepareStatement("exec dbo.Wga_GetPlaces ?, ?, ?, ?");
                sp.setInt(4, Integer.parseInt(params.get(KinomirManager.IDCLIENT)));
            } else {
                sp = conn.prepareStatement("exec Wga_GetPlacesNC ?, ?, ?");
            }
            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setLong(1, Long.parseLong(params.get(KinomirManager.IDPERFORMANCE)));
            }
            if ((params.get(KinomirManager.WITHPRICE) != null) && !(params.get(KinomirManager.WITHPRICE).equalsIgnoreCase("null"))) {
                sp.setShort(2, Short.parseShort(params.get(KinomirManager.WITHPRICE)));
            } else {
                sp.setNull(2, java.sql.Types.SMALLINT);
            }
            if ((params.get(KinomirManager.ALLPLACES) != null) && !(params.get(KinomirManager.ALLPLACES).equalsIgnoreCase("null"))) {
                sp.setShort(3, Short.parseShort(params.get(KinomirManager.ALLPLACES)));
            } else {
                sp.setNull(3, java.sql.Types.SMALLINT);
            }
            rs = sp.executeQuery();
            return new PlacesData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static PrintTicketData printTicket(Connection conn, Map<String, String> params) throws SQLException, DataException {
        ResultSet rs = null;
        PreparedStatement sp = null;

        try {
            sp = conn.prepareStatement("exec dbo.WgA_PrintTicket ?");

            if (params.get(KinomirManager.IDORDER) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDORDER)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new PrintTicketData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }

    }

    public static ShowInfoData getShowInfo(Connection conn, Map<String, String> params, KinomirLog logger, String shotPath, String videoPath) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_ShowInfo ?");
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new ShowInfoData(rs, logger, shotPath, videoPath);
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static ShowData getShow(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetShow ?, ?, ?, ?, ?, ?, ?");

            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDHALL) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDHALL)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDSHOWTYPE) != null) {
                try {
                    sp.setString(3, new String(params.get(KinomirManager.IDSHOWTYPE).getBytes("UTF-8"), "CP1251"));
                } catch (UnsupportedEncodingException ex) {
                    logger.error("Can't convert parametr to CP1251");
                }
            } else {
                sp.setNull(3, java.sql.Types.VARCHAR);
            }
            if (params.get(KinomirManager.IDGENRE) != null) {
                sp.setInt(4, Integer.parseInt(params.get(KinomirManager.IDGENRE)));
            } else {
                sp.setNull(4, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 5);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 6);
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(7, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(7, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new ShowData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static ShowNewData getShowNew(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_GetShow ?, ?, ?, ?, ?, ?");

            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDREGION) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDREGION)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDGENRE) != null) {
                sp.setInt(3, Integer.parseInt(params.get(KinomirManager.IDGENRE)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 4);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 5);
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(6, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(6, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new ShowNewData(rs);
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static HallData getHall(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetHalls ?, ?, ?");
            if (params.get("IDBUILDING") != null) {
                sp.setInt(1, Integer.parseInt(params.get("IDBUILDING")));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 2);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 3);
            rs = sp.executeQuery();
            return new HallData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static HallSchemaNCData getHallSchemaNC(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetHallSchemaNC ?, ?, ?");
            if ((params.get(KinomirManager.IDHALL) != null) && (!"null".equalsIgnoreCase(params.get(KinomirManager.IDHALL)))) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDHALL)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if ((params.get(KinomirManager.IDPERFORMANCE) != null) && (!"null".equalsIgnoreCase(params.get(KinomirManager.IDPERFORMANCE)))) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if ((params.get(KinomirManager.IDPRICECATEGORY) != null) && (!"null".equalsIgnoreCase(KinomirManager.IDPRICECATEGORY))) {
                sp.setInt(3, Integer.parseInt(params.get(KinomirManager.IDPRICECATEGORY)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new HallSchemaNCData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static PerformanceData getPerformance(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.Wga_GetPerformance ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
            // @IdBuilding int, @IdHall int, @IdShow int, @IdShowType varchar(2), @IdGenre int, 
            // @BeginTime datetime, @EndTime datetime, @AllFields tinyint, @AsId bit
            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDHALL) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDHALL)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(3, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDSHOWTYPE) != null) {
                sp.setString(4, params.get(KinomirManager.IDSHOWTYPE));
            } else {
                sp.setNull(4, java.sql.Types.VARCHAR);
            }
            if (params.get(KinomirManager.IDGENRE) != null) {
                sp.setInt(5, Integer.parseInt(params.get(KinomirManager.IDGENRE)));
            } else {
                sp.setNull(5, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 6);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 7);
            if (params.get(KinomirManager.ALLFIELDS) != null) {
                sp.setInt(8, Integer.parseInt(params.get(KinomirManager.ALLFIELDS)));
            } else {
                sp.setInt(8, 1);
            }
            if (params.get(KinomirManager.ASID) != null) {
                sp.setInt(9, Integer.parseInt(params.get(KinomirManager.ASID)));
            } else {
                sp.setInt(9, 1);
            }
            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setInt(10, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
            } else {
                sp.setNull(10, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            return new PerformanceData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static PerformanceNewData getPerformanceNew(Connection conn, Map<String, String> params, KinomirLog logger, DateFormat df) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_GetPerformancesNew ?, ?, ?, ?, ?, ?, ?");
            if (params.get(KinomirManager.IDBUILDING) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDBUILDING)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDREGION) != null) {
                sp.setInt(2, Integer.parseInt(params.get(KinomirManager.IDREGION)));
            } else {
                sp.setNull(2, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(3, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(3, java.sql.Types.INTEGER);
            }
            if (params.get(KinomirManager.IDGENRE) != null) {
                sp.setInt(4, Integer.parseInt(params.get(KinomirManager.IDGENRE)));
            } else {
                sp.setNull(4, java.sql.Types.INTEGER);
            }
            KinomirManager.setBeginTimeParameter(params, sp, df, logger, 5);
            KinomirManager.setEndTimeParameter(params, sp, df, logger, 6);
            if (params.get(KinomirManager.IDPERFORMANCE) != null) {
                sp.setInt(7, Integer.parseInt(params.get(KinomirManager.IDPERFORMANCE)));
            } else {
                sp.setNull(7, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();

            return new PerformanceNewData(rs);
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static AuthResult authClient(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_AuthClient ?, ?, ?");
            if (params.get(AUTHTYPE) != null) {
                sp.setString(1, params.get(AUTHTYPE));
            }
            if (params.get(LOGIN) != null) {
                sp.setString(2, params.get(LOGIN));
            }
            if (params.get(PASSWORD) != null) {
                sp.setString(3, params.get(PASSWORD));
            }

            rs = sp.executeQuery();
            return new AuthResult(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static Long getCliectIdByToken(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_GetClientIdByToken ?");
            if (params.get(TOKEN) != null) {
                sp.setString(1, params.get(TOKEN));
            } else {
                return null;
            }
            rs = sp.executeQuery();
            if (rs != null && rs.next()) {
                if (SqlUtils.hasColumn(rs, "ClientId")) {
                    return rs.getLong("ClientId");
                }
            }
        } catch (SQLException ex) {
            throw SqlUtils.convertErrorToException(rs, ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
        return null;
    }

    public static SimpleErrorData registerPushToken(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            //dbo.MyWeb_RegisterPushToken @IdClient, @DeviceType, @AppType, @AppVersion, @PushToken
            sp = conn.prepareStatement("exec dbo.MyWeb_RegisterPushToken ?, ?, ?, ?, ?");
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if (params.get(IDCLIENT) != null) {
                sp.setString(1, params.get(IDCLIENT));
            }
            if (params.get(DEVICETYPE) != null) {
                sp.setString(2, params.get(DEVICETYPE));
            }
            if (params.get(APPTYPE) != null) {
                sp.setString(3, params.get(APPTYPE));
            }
            if (params.get(APPVERSION) != null) {
                sp.setString(4, params.get(APPVERSION));
            }
            if (params.get(PUSHTOKEN) != null) {
                sp.setString(5, params.get(PUSHTOKEN));
            }
            rs = sp.executeQuery();
            return new SimpleErrorData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    public static SimpleErrorData deletePushToken(Connection conn, Map<String, String> params) throws SQLException, DataException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            //dbo.MyWeb_RegisterPushToken @IdClient, @DeviceType, @AppType, @AppVersion, @PushToken
            sp = conn.prepareStatement("exec dbo.MyWeb_DeletePushToken ?, ?");
            if (params.get(TOKEN) != null) {
                Long idClient = getCliectIdByToken(conn, params);
                if (idClient != null) {
                    params.put(IDCLIENT, idClient.toString());
                }
            }
            if (params.get(IDCLIENT) != null) {
                sp.setString(1, params.get(IDCLIENT));
            }
            if (params.get(PUSHTOKEN) != null) {
                sp.setString(2, params.get(PUSHTOKEN));
            }
            rs = sp.executeQuery();
            return new SimpleErrorData(rs);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

}
