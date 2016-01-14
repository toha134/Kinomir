/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.tools.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import ru.kinomir.tools.KinomirLog;

/**
 *
 * @author Антон
 */
public class SqlUtils {

    private static final KinomirLog logger = KinomirLog.getLogger(SqlUtils.class);

    public static SQLException convertErrorToException(ResultSet rs, SQLException originalEx) {
        SQLException result = null;
        if (originalEx != null) {
            logger.error("SQL error in query processing : " + originalEx.getMessage(), originalEx);
        }
        try {
            if (rs != null) {
                result = new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), originalEx);
            } else {
                result = new SQLException("No result for query", "1", originalEx);
            }
        } catch (SQLException ex) {
            result = originalEx;
        }
        return result;
    }

    public static void closeSQLObjects(ResultSet rs, PreparedStatement sp) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (sp != null) {
                sp.close();
            }
        } catch (SQLException ex) {
        }
    }

    public static boolean hasColumn(ResultSet rs, String columnName) {
		try{
        ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if (columnName.equalsIgnoreCase(rsmd.getColumnLabel(x))) {
					return true;
				}
			} 
		} catch(SQLException ex){
			
		}
        return false;
    }
}
