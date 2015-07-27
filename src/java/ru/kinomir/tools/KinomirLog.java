/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.tools;

import org.apache.log4j.Logger;

/**
 *
 * @author Антон
 */
public class KinomirLog {
    
    private Logger logger;

    public static KinomirLog getLogger(Class clazz){
        Logger log = Logger.getLogger(clazz);
        return new KinomirLog(log);
    }

    private KinomirLog(Logger log) {
        logger = log;
    }

    public void debug(long idOrder, String message, Throwable ex) {
        logger.debug(String.format("[OrderId = %1d] %1s", idOrder, message), ex);
    }
    
    public void error(long idOrder, String message, Throwable ex) {
        logger.error(String.format("[OrderId = %1d] %1s", idOrder, message), ex);
    }
    
    public void debug(long idOrder, String message) {
        logger.debug(String.format("[OrderId = %1d] %1s", idOrder, message));
    }
    
    public void error(long idOrder, String message) {
        logger.error(String.format("[OrderId = %1d] %1s", idOrder, message));
    }
    
    public void info(long idOrder, String message, Throwable ex) {
        logger.info(String.format("[OrderId = %1d] %1s", idOrder, message), ex);
    }
    
    public void info(long idOrder, String message) {
        logger.info(String.format("[OrderId = %1d] %1s", idOrder, message));
    }
    
    public void info(String message){
        logger.info(message);
    }
    
    public void error(String message){
        logger.error(message);
    }
    
    public void debug(String message){
        logger.debug(message);
    }
    
    public void info(String message, Throwable t){
        logger.info(message, t);
    }
    
    public void error(String message, Throwable t){
        logger.error(message, t);
    }
    
    public void debug(String message, Throwable t){
        logger.debug(message, t);
    }

}
