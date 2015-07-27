/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.format;

import java.io.StringWriter;
import java.sql.SQLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.processor.DataException;

/**
 *
 * @author Антон
 */
class XmlFormatter implements ResultFormatter {

    public XmlFormatter() {

    }

    @Override
    public String format(DataNode dataNode) throws Exception {
        JAXBContext context = JAXBContext.newInstance(dataNode.getClass());
        Marshaller m = context.createMarshaller();
        StringWriter xmlString = new StringWriter();
        m.marshal(dataNode, xmlString);
        return xmlString.toString();
    }

    @Override
    public String format(SQLException ex) {
        Document doc = DocumentHelper.createDocument();
        Element data = doc.addElement("data");
        data.addAttribute("error", ex.getSQLState());
        data.addAttribute("errorMessage", ex.getMessage());
        return data.asXML();
    }

    @Override
    public String format(DataException ex) {
        Document doc = DocumentHelper.createDocument();
        Element data = doc.addElement("data");
        data.addAttribute("error", ex.getErrorCode());
        data.addAttribute("errorMessage", ex.getMessage());
        return data.asXML();
    }

    @Override
    public String format(Exception ex, boolean isUnknown) {
        Document doc = DocumentHelper.createDocument();
        Element data = doc.addElement("data");
        data.addAttribute("error", "0");
        data.addAttribute("errorMessage", ex.getMessage());
        return data.asXML();
    }

}
