/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
public class PerformanceNewData extends DataNode {

    private static final transient SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @SerializedName("show")
    List<Show> shows = new ArrayList<Show>();

    public PerformanceNewData() {

    }

    public PerformanceNewData(ResultSet rs) throws SQLException, DataException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    Show show = findShow(rs.getString("idshow"));
                    if (show == null) {
                        show = new Show(rs);
                        shows.add(show);
                    }

                    Kinoteatr building = show.findKinoteatr(rs.getString("kinoteatr_id"), rs.getString("idhall"));
                    if (building == null) {
                        building = new Kinoteatr(rs);
                        show.addKinoteatr(building);
                    }

                    Performance performance = building.findPerformance(rs.getString("idperformance"));
                    if (performance == null) {
                        performance = new Performance(rs);
                        building.addPerformance(performance);
                    }
                    Price price = new Price(rs);
                    performance.addPrice(price);
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    private Show findShow(String idShow) {
        for (Show show : shows) {
            if (show.getIdshow().equals(idShow)) {
                return show;
            }
        }
        return null;
    }

    @XmlElement(name = "show")
    public List<Show> getShows() {
        return shows;
    }

    @XmlRootElement(name = "show")
    private static class Show {

        @SerializedName("kinoteatr")
        List<Kinoteatr> kinoteatres = new ArrayList<Kinoteatr>();
        @SerializedName("idshow")
        private final String idshow;
        @SerializedName("PremiereDate")
        private final String PremiereDate;
        @SerializedName("DateLastDisplay")
        private final String DateLastDisplay;
        @SerializedName("ShowName")
        private final String ShowName;
        @SerializedName("IdGenre")
        private final String IdGenre;
        @SerializedName("GenreName")
        private final String GenreName;
        @SerializedName("Duration")
        private final String Duration;

        public Show() {
            idshow = "";
            PremiereDate = "";
            DateLastDisplay = "";
            ShowName = "";
            IdGenre = "";
            GenreName = "";
            Duration = "";
        }

        public Show(ResultSet rs) throws SQLException {
            idshow = Integer.toString(rs.getInt("idshow"));
            PremiereDate = outDateFormat.format(rs.getTimestamp("PremiereDate", new GregorianCalendar()));
            DateLastDisplay = outDateFormat.format(rs.getTimestamp("DateLastDisplay", new GregorianCalendar()));
            ShowName = rs.getString("ShowName");
            IdGenre = rs.getString("IdGenre");
            GenreName = rs.getString("GenreName");
            Duration = Integer.toString(rs.getInt("Duration"));
        }

        protected void addKinoteatr(Kinoteatr kino) {
            kinoteatres.add(kino);
        }

        protected Kinoteatr findKinoteatr(String idKinoteatr, String idHall) {
            for (Kinoteatr kino : kinoteatres) {
                if (kino.getKinoteatr_id().equals(idKinoteatr) && kino.getIdhall().equals(idHall)) {
                    return kino;
                }
            }
            return null;
        }

        @XmlElement(name = "kinoteatr")
        public List<Kinoteatr> getKinoteatres() {
            return kinoteatres;
        }

        @XmlAttribute(name = "idshow")
        public String getIdshow() {
            return idshow;
        }

        @XmlAttribute(name = "PremiereDate")
        public String getPremiereDate() {
            return PremiereDate;
        }

        @XmlAttribute(name = "DateLastDisplay")
        public String getDateLastDisplay() {
            return DateLastDisplay;
        }

        @XmlAttribute(name = "ShowName")
        public String getShowName() {
            return ShowName;
        }

        @XmlAttribute(name = "IdGenre")
        public String getIdGenre() {
            return IdGenre;
        }

        @XmlAttribute(name = "GenreName")
        public String getGenreName() {
            return GenreName;
        }

        @XmlAttribute(name = "Duration")
        public String getDuration() {
            return Duration;
        }
    }

    @XmlRootElement(name = "kinoteatr")
    private static class Kinoteatr {

        @SerializedName("performance")
        List<Performance> performances = new ArrayList<Performance>();
        @SerializedName("kinoteatr_id")
        private final String kinoteatr_id;
        @SerializedName("idhall")
        private final String idhall;
        @SerializedName("kinoteatr")
        private final String kinoteatr;
        @SerializedName("zal")
        private final String zal;

        public Kinoteatr() {
            kinoteatr_id = "";
            idhall = "";
            kinoteatr = "";
            zal = "";
        }

        public Kinoteatr(ResultSet rs) throws SQLException {
            kinoteatr_id = Integer.toString(rs.getInt("kinoteatr_id"));
            idhall = Integer.toString(rs.getInt("idhall"));
            kinoteatr = rs.getString("kinoteatr");
            zal = rs.getString("zal");
        }

        protected void addPerformance(Performance perf) {
            performances.add(perf);
        }

        protected Performance findPerformance(String idPerformance) {
            for (Performance perf : performances) {
                if (perf.getIdperformance().equals(idPerformance)) {
                    return perf;
                }
            }
            return null;
        }

        @XmlElement(name = "performance")
        public List<Performance> getPerformances() {
            return performances;
        }

        @XmlAttribute(name = "kinoteatr_id")
        public String getKinoteatr_id() {
            return kinoteatr_id;
        }

        @XmlAttribute(name = "idhall")
        public String getIdhall() {
            return idhall;
        }

        @XmlAttribute(name = "kinoteatr")
        public String getKinoteatr() {
            return kinoteatr;
        }

        @XmlAttribute(name = "zal")
        public String getZal() {
            return zal;
        }

    }

    @XmlRootElement(name = "performance")
    private static class Performance {

        @SerializedName("price")
        List<Price> prices = new ArrayList<Price>();
        @SerializedName("idperformance")
        private final String idperformance;
        @SerializedName("begintime")
        private final String begintime;
        @SerializedName("endtime")
        private String endtime;
        @SerializedName("FreePlaces")
        private final String FreePlaces;
        @SerializedName("is3d")
        private final String is3d;
        @SerializedName("hfr")
        private final String hfr;

        public Performance() {
            idperformance = "";
            begintime = "";
            endtime = "";
            FreePlaces = "";
            is3d = "";
            hfr = "";
        }

        public Performance(ResultSet rs) throws SQLException {
            idperformance = Integer.toString(rs.getInt("idperformance"));
            begintime = outDateFormat.format(rs.getTimestamp("begintime", new GregorianCalendar()));
            try {
                endtime = rs.getObject("endtime") != null ? outDateFormat.format(rs.getTimestamp("endtime", new GregorianCalendar())) : "";
            } catch (Exception ex) {
                endtime = "";
            }
            FreePlaces = Integer.toString(rs.getInt("FreePlaces"));
            is3d = Integer.toString(rs.getInt("ddd"));
            hfr = Integer.toString(rs.getInt("hfr"));
        }

        protected void addPrice(Price price) {
            prices.add(price);
        }

        @XmlElement(name = "price")
        public List<Price> getPrices() {
            return prices;
        }

        @XmlAttribute(name = "idperformance")
        public String getIdperformance() {
            return idperformance;
        }

        @XmlAttribute(name = "begintime")
        public String getBegintime() {
            return begintime;
        }

        @XmlAttribute(name = "endtime")
        public String getEndtime() {
            return endtime;
        }

        @XmlAttribute(name = "FreePlaces")
        public String getFreePlaces() {
            return FreePlaces;
        }

        @XmlAttribute(name = "is3d")
        public String getIs3d() {
            return is3d;
        }

        @XmlAttribute(name = "hfr")
        public String getHfr() {
            return hfr;
        }

    }

    @XmlRootElement(name = "price")
    private static class Price {

        @SerializedName("idzone")
        private final String idzone;
        @SerializedName("price")
        private final String price;
        @SerializedName("zonename")
        private final String zonename;

        public Price() {
            idzone = "";
            price = "";
            zonename = "";
        }

        public Price(ResultSet rs) throws SQLException {
            idzone = Integer.toString(rs.getInt("idzone"));
            price = Double.toString(rs.getDouble(("price")));
            zonename = rs.getString("zonename");
        }

        @XmlAttribute(name = "idzone")
        public String getIdzone() {
            return idzone;
        }

        @XmlAttribute(name = "price")
        public String getPrice() {
            return price;
        }

        @XmlAttribute(name = "zonename")
        public String getZonename() {
            return zonename;
        }

    }

}
