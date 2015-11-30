/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.datalayer.dto.PrintTicketData;
import ru.kinomir.datalayer.dto.Ticket;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class PrintTicketProcessor extends AbstractRequestProcessor {

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException, DataException {

        PrintTicketData tickets = KinomirManager.printTicket(conn, params);
        for (Ticket ticket : tickets.getTickets()) {
            Element item = el.addElement("ticket");
            item.addAttribute("IdTicketOperation", ticket.getIdTicketOperation());
            item.addAttribute("IdPerformance", ticket.getIdPerformance());
            item.addAttribute("IdPlace", ticket.getIdPlace());
            item.addAttribute("Price", ticket.getPrice());
            item.addAttribute("Discount", ticket.getDiscount());
            item.addAttribute("Series", ticket.getSeries());
            item.addAttribute("TickNum", ticket.getTickNum());
            item.addAttribute("datetime", ticket.getdatetime());
            item.addAttribute("HallName", ticket.getHallName());
            item.addAttribute("BuildingName", ticket.getBuildingName());
            item.addAttribute("Row", ticket.getRow());
            item.addAttribute("Place", ticket.getPlace());
            item.addAttribute("ShowName", ticket.getShowName());
            item.addAttribute("agelimit", ticket.getagelimit());
        }
        sendToQueue(tickets, QUEUE_TICKET);
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.printTicket(conn, params);
    }

}
