/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface IRequestProcessor {

	/**
	 *  
	 */
	String processQuery(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException;
}
