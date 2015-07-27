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
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "performance")
public class PerfInfo {

    @SerializedName("DateTime")
    String DateTime;
    @SerializedName("ShowName")
    String ShowName;
    @SerializedName("Producer")
    String Producer;
    @SerializedName("Painter")
    String Painter;
    @SerializedName("Description")
    String Description;
    @SerializedName("Remark")
    String Remark;
    @SerializedName("Actors")
    String Actors;
    @SerializedName("pu_number")
    String pu_number;
    @SerializedName("NameEng")
    String NameEng;
    @SerializedName("ShowType")
    String ShowType;
    @SerializedName("GenreName")
    String GenreName;
    @SerializedName("Hall")
    String Hall;
    @SerializedName("HallDesc")
    String HallDesc;
    @SerializedName("BuildingName")
    String BuildingName;
    @SerializedName("Duration")
    String Duration;
    @SerializedName("Premieredate")
    String Premieredate;
    
    public PerfInfo(){
        
    }

    public PerfInfo(ResultSet rs) throws SQLException {
        SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateTime = outDateFormat.format(rs.getTimestamp("DateTime", new GregorianCalendar()));
        ShowName = rs.getString("ShowName");
        Producer = rs.getString("Producer");
        Painter = rs.getString("Painter");
        Description = rs.getString("Description");
        Remark = rs.getString("Remark");
        Actors = rs.getString("Actors");
        pu_number = rs.getString("pu_number");
        NameEng = rs.getString("NameEng");
        ShowType = rs.getString("ShowType");
        GenreName = rs.getString("GenreName");
        Hall = rs.getString("Hall");
        HallDesc = rs.getString("HallDesc");
        BuildingName = rs.getString("BuildingName");
        Duration = rs.getString("Duration");
        Premieredate = rs.getString("Premieredate");
    }

    @XmlAttribute(name = "DateTime")
    public String getDateTime() {
        return DateTime;
    }

    @XmlAttribute(name = "ShowName")
    public String getShowName() {
        return ShowName;
    }

    @XmlAttribute(name = "Producer")
    public String getProducer() {
        return Producer;
    }

    @XmlAttribute(name = "Painter")
    public String getPainter() {
        return Painter;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlAttribute(name = "Remark")
    public String getRemark() {
        return Remark;
    }

    @XmlAttribute(name = "Actors")
    public String getActors() {
        return Actors;
    }

    @XmlAttribute(name = "pu_number")
    public String getpu_number() {
        return pu_number;
    }

    @XmlAttribute(name = "NameEng")
    public String getNameEng() {
        return NameEng;
    }

    @XmlAttribute(name = "ShowType")
    public String getShowType() {
        return ShowType;
    }

    @XmlAttribute(name = "GenreName")
    public String getGenreName() {
        return GenreName;
    }

    @XmlAttribute(name = "Hall")
    public String getHall() {
        return Hall;
    }

    @XmlAttribute(name = "HallDesc")
    public String getHallDesc() {
        return HallDesc;
    }

    @XmlAttribute(name = "BuildingName")
    public String getBuildingName() {
        return BuildingName;
    }

    @XmlAttribute(name = "Duration")
    public String getDuration() {
        return Duration;
    }

    @XmlAttribute(name = "Premieredate")
    public String getPremieredate() {
        return Premieredate;
    }
}
