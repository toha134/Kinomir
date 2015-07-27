/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.format;

/**
 *
 * @author Антон
 */
public class FormatterFactory {
    
    public static ResultFormatter getFormatter(String type){
        if ("json".equalsIgnoreCase(type)){
            return new JsonFormatter();
        } 
        return new XmlFormatter();
    }
    
}
