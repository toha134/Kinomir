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

/**
 *
 * @author Антон
 */
public class ShowNew {
    
    @SerializedName("idshow")
    private final String idshow;
    @SerializedName("producer")
    private final String producer;
    @SerializedName("painter")
    private final String painter;
    @SerializedName("description")
    private final String description;
    @SerializedName("remark")
    private final String remark;
    @SerializedName("showname")
    private final String showname;
    @SerializedName("duration")
    private final String duration;
    @SerializedName("PremiereDate")
    private final String PremiereDate;
    @SerializedName("idgenre")
    private final String idgenre;
    @SerializedName("actors")
    private final String actors;
    @SerializedName("pu_number")
    private final String pu_number;
    @SerializedName("datelastdisplay")
    private final String datelastdisplay;
    @SerializedName("creator")
    private final String creator;
    @SerializedName("nameeng")
    private final String nameeng;
    @SerializedName("genrename")
    private final String genrename;
    @SerializedName("if_prem")
    private final String if_prem;
    @SerializedName("age_limit")
    private final String age_limit;
    
    public ShowNew(){
        idshow = "";
        idgenre = "";
        genrename = "";
        showname = "";
        age_limit = "";
        duration = "";
        producer = "";
        painter = "";
        remark = "";
        description = "";
        actors = "";
        if_prem = "";
        PremiereDate = "";
        datelastdisplay = "";
        nameeng = "";
        pu_number = "";
        creator = "";
    }

    public ShowNew(ResultSet rs) throws SQLException {
        SimpleDateFormat outDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        idshow = Integer.toString(rs.getInt("idshow"));
        idgenre = Integer.toString(rs.getInt("idgenre"));
        genrename = rs.getString("genrename");
        showname = rs.getString("showname");
        age_limit = rs.getString("age_limit") == null ? "" : rs.getString("age_limit");
        duration = Integer.toString(rs.getInt("duration"));
        producer = rs.getString("producer");
        painter = rs.getString("painter");
        remark = rs.getString("remark");
        description = rs.getString("description");
        actors = rs.getString("actors");
        if_prem = Integer.toString(rs.getInt("if_prem"));
        PremiereDate = outDateFormat.format(rs.getTimestamp("PremiereDate", new GregorianCalendar()));
        datelastdisplay = outDateFormat.format(rs.getTimestamp("datelastdisplay", new GregorianCalendar()));
        nameeng = rs.getString("nameeng") == null ? "" : rs.getString("nameeng");
        pu_number = rs.getString("pu_number") == null ? "" : rs.getString("pu_number");
        creator = rs.getString("creator") == null ? "" : rs.getString("creator");
    }

    @XmlAttribute(name = "idshow")
    public String getIdshow() {
        return idshow;
    }

    @XmlAttribute(name = "producer")
    public String getProducer() {
        return producer;
    }

    @XmlAttribute(name = "painter")
    public String getPainter() {
        return painter;
    }

    @XmlAttribute(name = "description")
    public String getDescription() {
        return description;
    }

    @XmlAttribute(name = "remark")
    public String getRemark() {
        return remark;
    }

    @XmlAttribute(name = "showname")
    public String getShowname() {
        return showname;
    }

    @XmlAttribute(name = "duration")
    public String getDuration() {
        return duration;
    }

    @XmlAttribute(name = "PremiereDate")
    public String getPremiereDate() {
        return PremiereDate;
    }

    @XmlAttribute(name = "idgenre")
    public String getIdgenre() {
        return idgenre;
    }

    @XmlAttribute(name = "actors")
    public String getActors() {
        return actors;
    }

    @XmlAttribute(name = "pu_number")
    public String getPu_number() {
        return pu_number;
    }

    @XmlAttribute(name = "datelastdisplay")
    public String getDatelastdisplay() {
        return datelastdisplay;
    }

    @XmlAttribute(name = "creator")
    public String getCreator() {
        return creator;
    }

    @XmlAttribute(name = "nameeng")
    public String getNameeng() {
        return nameeng;
    }

    @XmlAttribute(name = "genrename")
    public String getGenrename() {
        return genrename;
    }

    @XmlAttribute(name = "if_prem")
    public String getIf_prem() {
        return if_prem;
    }

    @XmlAttribute(name = "age_limit")
    public String getAge_limit() {
        return age_limit;
    }
}
