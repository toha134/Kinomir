/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.cert.X509Certificate;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author user
 */
public class SecurityFilter implements Filter {

    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private String certSerial = null;
    private String certOwnerType = null;

    public SecurityFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:DoBeforeProcessing");
        }

        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

        if (certs != null && certs.length > 0) {
            X509Certificate cert = certs[0];
            certSerial = Long.toHexString(cert.getSerialNumber().longValue());
            for (final String field : (cert.getSubjectDN().toString()).split(", ")) {
                if (field.startsWith("CN=")) {
                    if (debug) {
                        log("Common Name: " + field.substring(3));
                    }
                }
                if (field.startsWith("OU=")) {
                    if (debug) {
                        log("Organization Unit: " + field.substring(3));
                        certOwnerType = field.substring(3);
                    }
                }
                if (field.startsWith("O=")) {
                    if (debug) {
                        log("Organization name: " + field.substring(2));
                    }
                }
                if (field.startsWith("L=")) {
                    if (debug) {
                        log("Locality name: " + field.substring(2));
                    }
                }
                if (field.startsWith("ST=")) {
                    if (debug) {
                        log("State or province Name: " + field.substring(3));
                    }
                }
                if (field.startsWith("C=")) {
                    if (debug) {
                        log("Country: " + field.substring(2));
                    }
                }
                if (field.startsWith("DC=")) {
                    if (debug) {
                        log("Domain Component: " + field.substring(3));
                    }
                }
            }
            
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("SecurityFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            if (certSerial == null) {
                throw new Exception("No authorize access");
            }
            request.setAttribute("user_id", certSerial);
            request.setAttribute("certType", certOwnerType);
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("SecurityFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityFilter()");
        }
        StringBuffer sb = new StringBuffer("SecurityFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                try (PrintWriter pw = new PrintWriter(new PrintStream(response.getOutputStream()))) {
                    pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
                    pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                    pw.print(stackTrace);
                    pw.print("</pre></body>\n</html>");
                }
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try (PrintStream ps = new PrintStream(response.getOutputStream())) {
                t.printStackTrace(ps);
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try (StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
