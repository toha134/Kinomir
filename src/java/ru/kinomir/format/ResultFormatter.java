/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.format;

import java.sql.SQLException;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.processor.DataException;

/**
 *
 * @author Антон
 */
public interface ResultFormatter {
    
    public String format(DataNode dataNode) throws Exception;
    
    public String format(SQLException ex);
    
    public String format(DataException ex);
    
    public String format(Exception ex, boolean isUnknown);
    
}
