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
public class OrdersInfoData extends DataNode {

    @SerializedName("order")
    List<Order> orders = new ArrayList<Order>();

    public OrdersInfoData() {

    }

    public OrdersInfoData(ResultSet rs) throws SQLException, DataException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    Order order = findOrder(rs.getString("idorder"));
                    if (order == null) {
                        order = new Order(rs);
                        orders.add(order);
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

    protected Order findOrder(String idOrder) {
        for (Order order : orders) {
            if (order.getidorder().equals(idOrder)) {
                return order;
            }
        }
        return null;
    }

    @XmlElement(name = "order")
    public List<Order> getOrders() {
        return orders;
    }

    @XmlRootElement(name = "order")
    public static class Order {

        @SerializedName("performance")
        List<Performance> performances = new ArrayList<Performance>();

        @SerializedName("idorder")
        private final String idorder;
        @SerializedName("ordercreatetime")
        private final String ordercreatetime;
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
        @SerializedName("name")
        private final String name;
        @SerializedName("firstname")
        private final String firstname;
        @SerializedName("patronymic")
        private final String patronymic;
        @SerializedName("phone")
        private final String phone;
        @SerializedName("email")
        private final String email;
        @SerializedName("fax")
        private final String fax;
        @SerializedName("cellular")
        private final String cellular;
        @SerializedName("birthday")
        private final String birthday;
        @SerializedName("orderdescription")
        private final String orderdescription;
        @SerializedName("orderpass")
        private final String orderpass;

        public Order() {
            idorder = "";
            ordercreatetime = "";
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
            name = "";
            firstname = "";
            patronymic = "";
            phone = "";
            email = "";
            fax = "";
            cellular = "";
            birthday = "";
            orderdescription = "";
            orderpass = "";
        }

        public Order(ResultSet rs) throws SQLException {
            idorder = rs.getString("idorder");
            ordercreatetime = rs.getString("ordercreatetime");
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
            name = rs.getString("name");
            firstname = rs.getString("firstname");
            patronymic = rs.getString("patronymic");
            phone = rs.getString("phone");
            email = rs.getString("email");
            fax = rs.getString("fax");
            cellular = rs.getString("cellular");
            birthday = rs.getString("birthday");
            orderdescription = rs.getString("orderdescription");
            String description = rs.getString("description");
            if ((description != null) && (description.length() > 3)) {
                description = description.substring(description.length() - 4);
            }
            orderpass = description;

        }

        @XmlAttribute(name = "idorder")
        public String getidorder() {
            return idorder;
        }

        @XmlAttribute(name = "ordercreatetime")
        public String getordercreatetime() {
            return ordercreatetime;
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

        @XmlAttribute(name = "name")
        public String getname() {
            return name;
        }

        @XmlAttribute(name = "firstname")
        public String getfirstname() {
            return firstname;
        }

        @XmlAttribute(name = "patronymic")
        public String getpatronymic() {
            return patronymic;
        }

        @XmlAttribute(name = "phone")
        public String getphone() {
            return phone;
        }

        @XmlAttribute(name = "email")
        public String getemail() {
            return email;
        }

        @XmlAttribute(name = "fax")
        public String getfax() {
            return fax;
        }

        @XmlAttribute(name = "cellular")
        public String getcellular() {
            return cellular;
        }

        @XmlAttribute(name = "birthday")
        public String getbirthday() {
            return birthday;
        }

        @XmlAttribute(name = "orderdescription")
        public String getorderdescription() {
            return orderdescription;
        }

        @XmlAttribute(name = "orderpass")
        public String getorderpass() {
            return orderpass;
        }

        @XmlElement(name = "performance")
        public List<Performance> getPerformances() {
            return performances;
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
        @SerializedName("idshow")
        private final String idshow;
        
        public Performance(){
            showname = "";
            idperformance = "";
            performancestarttime = "";
            hall = "";
            building = "";
            idshow = "";
        }

        public Performance(ResultSet rs) throws SQLException {
            showname = rs.getString("showname");
            idperformance = rs.getString("idperformance");
            performancestarttime = rs.getString("performancestarttime");
            hall = rs.getString("hall");
            building = rs.getString("building");
            idshow = rs.getString("idshow");
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

        @XmlElement(name = "place")
        public List<Place> getPlaces() {
            return places;
        }

        private void addPlace(Place place) {
            places.add(place);
        }

        @XmlAttribute(name = "idshow")
        public String getIdshow() {
            return idshow;
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
        
        public Place(){
            idplace = "";
            rownom = "";
            placenom = "";
        }

        public Place(ResultSet rs) throws SQLException {
            idplace = rs.getString("idplace");
            rownom = rs.getString("rownom");
            placenom = rs.getString("placenom");
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

    }

}
