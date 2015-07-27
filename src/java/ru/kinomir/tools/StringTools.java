/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.tools;

/**
 *
 * @author Антон
 */
public class StringTools {

    public static String nullToEmptyString(String str) {
        return str == null ? "" : str;
    }

    public static boolean isEmpty(String value) {
        return (value == null) || "".equals(value);
    }
}
