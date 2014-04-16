/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ru.kinomir.processor.AbstractRequestProcessor;

/**
 *
 * @author Admin
 */
public class QueryServlet extends HttpServlet {

	protected Logger logger = null;

	@Override
	public void init() {
		logger = Logger.getLogger(getClass());
		logger.info(new StringBuilder("Servlet init : ").append(getClass().getName()));
	}

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String answer = null;
			String errorMessage = null;
			if (request.getParameter("sp") != null) {
				String queryProcessor = getInitParameter(request.getParameter("sp"));
				String userId = (String) request.getAttribute("user_id");
				logger.info("Find processor for " + request.getParameter("sp"));
				AbstractRequestProcessor processor = (AbstractRequestProcessor) Class.forName(queryProcessor).newInstance();
				processor.setLogger(logger);
				Connection conn = getConnection(userId);
				Map<String, String> requestParams = new HashMap<String, String>();
				for (Object parName : request.getParameterMap().keySet()) {
					requestParams.put(((String) parName).toUpperCase(), request.getParameter((String) parName));
				}
				Map<String, String> initParams = new HashMap<String, String>();
				Enumeration<String> initPapamsNames = getInitParameterNames();
				while (initPapamsNames.hasMoreElements()) {
					String initParamName = initPapamsNames.nextElement();
					initParams.put(initParamName, getInitParameter(initParamName));
				}
				if (!requestParams.containsKey("IDDOCUMENT")) {
					requestParams.put("IDDOCUMENT", getInitParameter("IDDOCUMENT"));
				}
				logger.info("Request params: " + paramsToString(requestParams));
				try {
					answer = processor.processQuery(conn, requestParams, initParams);
				} finally {
					conn.close();
				}
			} else {
				errorMessage = "No parameter 'sp'";
			}
			if (answer == null) {
				logger.error("No answer to client");
				answer = String.format("<data error=\"1\" message=\"%s\"/>", errorMessage == null ? "Can't process query" : errorMessage);
			}
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			try {
				out.write(answer);
			} finally {
				out.close();
			}

		} catch (Exception ex) {
			logger.error("Processing error: " + ex.getMessage(), ex);
		}
	}

	private Connection getConnection(String userId) throws SQLException, NamingException {
		Context ctx = new InitialContext();
		String userDSName = getInitParameter("user_" + userId);
		DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/" + userDSName);
		Connection conn = dataSource.getConnection();
		return conn;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP
	 * <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP
	 * <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	private String paramsToString(Map<String, String> requestParams) {
		StringBuffer buf = new StringBuffer("[");
		for (String pName : requestParams.keySet()) {
			buf.append("{").append(pName).append(" = ").append(requestParams.get(pName)).append("} ");
		}
		buf.append("]");
		return buf.toString();

	}
}
