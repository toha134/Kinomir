/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import javax.management.ObjectName;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.processor.DataException;

/**
 *
 * @author Антон
 */
class JsonFormatter implements ResultFormatter {

    public JsonFormatter() {
    }

    @Override
    public String format(DataNode dataNode) throws Exception {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
        return gson.toJson(dataNode);
    }

    @Override
    public String format(SQLException ex) {
        ErrorObject error = new ErrorObject(ex.getSQLState(), ex.getMessage());
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
        return gson.toJson(error);
    }

    @Override
    public String format(DataException ex) {
        ErrorObject error = new ErrorObject(ex.getErrorCode(), ex.getMessage());
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
        return gson.toJson(error);
    }

    @Override
    public String format(Exception ex, boolean isUnknown) {
        ErrorObject error = new ErrorObject("0", ex.getMessage());
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .create();
        return gson.toJson(error);
    }

    private static class ErrorObject {

        @SerializedName("error")
        private String error;
        @SerializedName("errorMessage")
        private String errorMessage;

        public ErrorObject(String error, String errorMessage) {
            this.error = error;
            this.errorMessage = errorMessage;
        }

        public String getError() {
            return error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    }

}
