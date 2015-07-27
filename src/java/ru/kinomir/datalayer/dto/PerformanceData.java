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
public class PerformanceData extends DataNode {

    @SerializedName("show")
    List<PerformanceData.Show> shows = new ArrayList<PerformanceData.Show>();

    public PerformanceData() {

    }

    public PerformanceData(ResultSet rs) throws SQLException, DataException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    PerformanceData.Show show = findShow(rs.getString("IdShow"));
                    if (show == null) {
                        show = new Show(rs);
                        shows.add(show);
                    }

                    Building building = show.findBuilding(rs.getString("IdBuilding"), rs.getString("IdHall"));
                    if (building == null) {
                        building = new Building(rs);
                        show.addBuilding(building);
                    }

                    Performance performance = new Performance(rs);
                    building.addPerformance(performance);
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
            if (show.getIdShow().equals(idShow)) {
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

        @SerializedName("building")
        List<PerformanceData.Building> buildings = new ArrayList<PerformanceData.Building>();
        @SerializedName("IdShow")
        private final String IdShow;
        @SerializedName("IdGenre")
        private final String IdGenre;
        @SerializedName("IdShowType")
        private final String IdShowType;
        @SerializedName("ShowName")
        private final String ShowName;
        @SerializedName("GenreName")
        private final String GenreName;
        @SerializedName("Duration")
        private final String Duration;

        public Show() {
            IdShow = "";
            IdGenre = "";
            IdShowType = "";
            ShowName = "";
            GenreName = "";
            Duration = "";
        }

        public Show(ResultSet rs) throws SQLException {
            IdShow = Integer.toString(rs.getInt("IdShow"));
            IdGenre = Integer.toString(rs.getInt("IdGenre"));
            IdShowType = rs.getString("IdShowType");
            ShowName = rs.getString("ShowName");
            GenreName = rs.getString("GenreName");
            Duration = Integer.toString(rs.getInt("Duration"));
        }

        protected void addBuilding(Building building) {
            buildings.add(building);
        }

        protected Building findBuilding(String idBuilding, String idHall) {
            for (Building building : buildings) {
                if (idBuilding.equals(building.getIdBuilding()) && idHall.equals(building.getIdHall())) {
                    return building;
                }
            }
            return null;
        }

        @XmlElement(name = "building")
        public List<Building> getBuildings() {
            return buildings;
        }

        @XmlAttribute(name = "IdShow")
        public String getIdShow() {
            return IdShow;
        }

        @XmlAttribute(name = "IdGenre")
        public String getIdGenre() {
            return IdGenre;
        }

        @XmlAttribute(name = "IdShowType")
        public String getIdShowType() {
            return IdShowType;
        }

        @XmlAttribute(name = "ShowName")
        public String getShowName() {
            return ShowName;
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

    @XmlRootElement(name = "building")
    private static class Building {

        @SerializedName("performance")
        List<PerformanceData.Performance> performances = new ArrayList<PerformanceData.Performance>();
        @SerializedName("IdBuilding")
        private final String IdBuilding;
        @SerializedName("IdHall")
        private final String IdHall;
        @SerializedName("BuildingName")
        private final String BuildingName;
        @SerializedName("HallName")
        private final String HallName;

        public Building() {
            IdBuilding = "";
            IdHall = "";
            BuildingName = "";
            HallName = "";
        }

        public Building(ResultSet rs) throws SQLException {
            IdBuilding = Integer.toString(rs.getInt("IdBuilding"));
            IdHall = Integer.toString(rs.getInt("IdHall"));
            BuildingName = rs.getString("BuildingName");
            HallName = rs.getString("HallName");
        }

        protected void addPerformance(Performance performance) {
            performances.add(performance);
        }

        @XmlElement(name = "performance")
        public List<Performance> getPerformances() {
            return performances;
        }

        @XmlAttribute(name = "IdBuilding")
        public String getIdBuilding() {
            return IdBuilding;
        }

        @XmlAttribute(name = "IdHall")
        public String getIdHall() {
            return IdHall;
        }

        @XmlAttribute(name = "BuildingName")
        public String getBuildingName() {
            return BuildingName;
        }

        @XmlAttribute(name = "HallName")
        public String getHallName() {
            return HallName;
        }

    }

    @XmlRootElement(name = "performance")
    private static class Performance {

        @SerializedName("IdPerformance")
        private final String IdPerformance;
        @SerializedName("MinPrice")
        private final String MinPrice;
        @SerializedName("MaxPrice")
        private final String MaxPrice;
        @SerializedName("FreePlace")
        private final String FreePlace;
        @SerializedName("DateTime")
        private final String DateTime;

        public Performance() {
            IdPerformance = "";
            DateTime = "";
            MinPrice = "";
            MaxPrice = "";
            FreePlace = "";
        }

        public Performance(ResultSet rs) throws SQLException {
            SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            IdPerformance = Integer.toString(rs.getInt("IdPerformance"));
            DateTime = outDateFormat.format(rs.getTimestamp("DateTime", new GregorianCalendar()));
            MinPrice = Double.toString(rs.getDouble(("MinPrice")));
            MaxPrice = Double.toString(rs.getDouble(("MaxPrice")));
            FreePlace = Integer.toString(rs.getInt("FreePlace"));
        }

        @XmlAttribute(name = "IdPerformance")
        public String getIdPerformance() {
            return IdPerformance;
        }

        @XmlAttribute(name = "MinPrice")
        public String getMinPrice() {
            return MinPrice;
        }

        @XmlAttribute(name = "MaxPrice")
        public String getMaxPrice() {
            return MaxPrice;
        }

        @XmlAttribute(name = "FreePlace")
        public String getFreePlace() {
            return FreePlace;
        }

        @XmlAttribute(name = "DateTime")
        public String getDateTime() {
            return DateTime;
        }

    }

}
