/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

/**
 *
 * @author Антон
 */
public class DataException extends Exception {
    private String errorCode;

    public DataException(String errorCode) {
        this.errorCode = errorCode;
    }

    public DataException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public DataException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DataException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
}
