/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class OrderInfoData extends DataNode {

    @SerializedName("order")
    Order order;

    public OrderInfoData() {

    }

    public OrderInfoData(ResultSet rs) throws SQLException, DataException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    if (order == null) {
                        order = new Order(rs);
                    }

                    Performance performance = order.findPerformance(rs.getString("idperformance"));
                    if (performance == null) {
                        performance = new Performance(rs);
                        order.addPerformance(performance);
                    }

                    Place place = new Place(rs);
                    performance.addPlace(place);
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    /*protected Order findOrder(String idOrder) {
        for (Order order : orders) {
            if (order.getIdorder().equals(idOrder)) {
                return order;
            }
        }
        return null;
    }*/

    @XmlElement(name = "order")
    public Order getOrder() {
        return order;
    }

    @XmlRootElement(name = "order")
    public static class Order {

        @SerializedName("idorder")
        private final String idorder;
        @SerializedName("performance")
        List<Performance> performances = new ArrayList<Performance>();
        @SerializedName("begintime")
        private final String begintime;
        @SerializedName("brokerage")
        private final String brokerage;
        @SerializedName("timepayment")
        private final String timepayment;
        @SerializedName("orderexpiretime")
        private final String orderexpiretime;
        @SerializedName("orderstate")
        private final String orderstate;
        @SerializedName("orderprice")
        private final String orderprice;
        @SerializedName("orderpaysum")
        private final String orderpaysum;
        @SerializedName("orderrecalltickets")
        private final String orderrecalltickets;
        @SerializedName("saledticketssum")
        private final String saledticketssum;
        @SerializedName("saledtickets")
        private final String saledtickets;
        @SerializedName("ordertotalticketssum")
        private final String ordertotalticketssum;
        @SerializedName("ordertotaltickets")
        private final String ordertotaltickets;
        @SerializedName("description")
        private final String description;
        @SerializedName("idclient")
        private final String idclient;
        @SerializedName("orderpass")
        private final String orderpass;

        public Order() {
            idorder = "";
            begintime = "";
            brokerage = "";
            timepayment = "";
            orderexpiretime = "";
            orderstate = "";
            orderprice = "";
            orderpaysum = "";
            orderrecalltickets = "";
            saledticketssum = "";
            saledtickets = "";
            ordertotalticketssum = "";
            ordertotaltickets = "";
            description = "";
            idclient = "";
            orderpass = "";
        }

        public Order(ResultSet rs) throws SQLException {
            idorder = rs.getString("idorder");
            begintime = rs.getString("begintime");
            brokerage = rs.getString("brokerage");
            timepayment = rs.getString("timepayment");
            orderexpiretime = rs.getString("orderexpiretime");
            orderstate = rs.getString("orderstate");
            orderprice = rs.getString("orderprice");
            orderpaysum = rs.getString("orderpaysum");
            orderrecalltickets = rs.getString("orderrecalltickets");
            saledticketssum = rs.getString("saledticketssum");
            saledtickets = rs.getString("saledtickets");
            ordertotalticketssum = rs.getString("ordertotalticketssum");
            ordertotaltickets = rs.getString("ordertotaltickets");
            description = rs.getString("description");
            idclient = rs.getString("idclient");
            String description = rs.getString("description");
            if ((description != null) && (description.length() > 3)) {
                description = description.substring(description.length() - 4);
            }
            orderpass = description;

        }

        @XmlAttribute(name = "begintime")
        public String getbegintime() {
            return begintime;
        }

        @XmlAttribute(name = "brokerage")
        public String getbrokerage() {
            return brokerage;
        }

        @XmlAttribute(name = "timepayment")
        public String gettimepayment() {
            return timepayment;
        }

        @XmlAttribute(name = "orderexpiretime")
        public String getorderexpiretime() {
            return orderexpiretime;
        }

        @XmlAttribute(name = "orderstate")
        public String getorderstate() {
            return orderstate;
        }

        @XmlAttribute(name = "orderprice")
        public String getorderprice() {
            return orderprice;
        }

        @XmlAttribute(name = "orderpaysum")
        public String getorderpaysum() {
            return orderpaysum;
        }

        @XmlAttribute(name = "orderrecalltickets")
        public String getorderrecalltickets() {
            return orderrecalltickets;
        }

        @XmlAttribute(name = "saledticketssum")
        public String getsaledticketssum() {
            return saledticketssum;
        }

        @XmlAttribute(name = "saledtickets")
        public String getsaledtickets() {
            return saledtickets;
        }

        @XmlAttribute(name = "ordertotalticketssum")
        public String getordertotalticketssum() {
            return ordertotalticketssum;
        }

        @XmlAttribute(name = "ordertotaltickets")
        public String getordertotaltickets() {
            return ordertotaltickets;
        }

        @XmlAttribute(name = "description")
        public String getdescription() {
            return description;
        }

        @XmlAttribute(name = "idclient")
        public String getidclient() {
            return idclient;
        }

        @XmlAttribute(name = "orderpass")
        public String getorderpass() {
            return orderpass;
        }

        @XmlAttribute(name = "idOrder")
        public String getIdorder() {
            return idorder;
        }

        private void addPerformance(Performance performance) {
            performances.add(performance);
        }

        protected Performance findPerformance(String idPerformance) {
            for (Performance perf : performances) {
                if (perf.getidperformance().equals(idPerformance)) {
                    return perf;
                }
            }
            return null;
        }

        @XmlElement(name = "performance")
        public List<Performance> getPerformances() {
            return performances;
        }

    }

    @XmlRootElement(name = "performance")
    private static class Performance {

        @SerializedName("place")
        List<Place> places = new ArrayList<Place>();

        @SerializedName("showname")
        private final String showname;
        @SerializedName("idperformance")
        private final String idperformance;
        @SerializedName("performancestarttime")
        private final String performancestarttime;
        @SerializedName("hall")
        private final String hall;
        @SerializedName("building")
        private final String building;

        public Performance() {
            showname = "";
            idperformance = "";
            performancestarttime = "";
            hall = "";
            building = "";
        }

        public Performance(ResultSet rs) throws SQLException {
            showname = rs.getString("showname");
            idperformance = rs.getString("idperformance");
            performancestarttime = rs.getString("performancestarttime");
            hall = rs.getString("hall");
            building = rs.getString("building");

        }

        @XmlAttribute(name = "showname")
        public String getshowname() {
            return showname;
        }

        @XmlAttribute(name = "idperformance")
        public String getidperformance() {
            return idperformance;
        }

        @XmlAttribute(name = "performancestarttime")
        public String getperformancestarttime() {
            return performancestarttime;
        }

        @XmlAttribute(name = "hall")
        public String gethall() {
            return hall;
        }

        @XmlAttribute(name = "building")
        public String getbuilding() {
            return building;
        }

        private void addPlace(Place place) {
            places.add(place);
        }

        @XmlElement(name = "place")
        public List<Place> getPlaces() {
            return places;
        }

    }

    @XmlRootElement(name = "place")
    private static class Place {

        @SerializedName("idplace")
        private final String idplace;
        @SerializedName("rownom")
        private final String rownom;
        @SerializedName("placenom")
        private final String placenom;
        /*@SerializedName("price")
        private final String price;*/

        public Place(ResultSet rs) throws SQLException {
            idplace = rs.getString("idplace");
            rownom = rs.getString("rownom");
            placenom = rs.getString("placenom");
            //price = rs.getString("price");

        }

        public Place() {
            idplace = "";
            rownom = "";
            placenom = "";
            //price = "";
        }

        @XmlAttribute(name = "idplace")
        public String getidplace() {
            return idplace;
        }

        @XmlAttribute(name = "rownom")
        public String getrownom() {
            return rownom;
        }

        @XmlAttribute(name = "placenom")
        public String getplacenom() {
            return placenom;
        }

        /*@XmlAttribute(name = "price")
        public String getPrice() {
            return price;
        }*/

    }

}
