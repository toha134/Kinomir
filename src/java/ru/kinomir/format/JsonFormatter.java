/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        StringBuilder result = new StringBuilder();
        result.append("{error: ").append(ex.getSQLState())
                .append("; errorMessage:").append(ObjectName.quote(ex.getMessage())).append("}");
        return result.toString();
    }

    @Override
    public String format(DataException ex) {
        StringBuilder result = new StringBuilder();
        result.append("{error: ").append(ex.getErrorCode())
                .append("; errorMessage:").append(ObjectName.quote(ex.getMessage())).append("}");
        
        return result.toString();
    }

    @Override
    public String format(Exception ex, boolean isUnknown) {
        StringBuilder result = new StringBuilder();
        result.append("{error: \"0\"; errorMessage:").append(ObjectName.quote(ex.getMessage())).append("}");
        return result.toString();
    }

}
