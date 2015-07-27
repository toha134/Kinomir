/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.tools.KinomirLog;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "show")
public class ShowInfo {

    @SerializedName("IdShow")
    private final String IdShow;
    @SerializedName("IdShowType")
    private final String IdShowType;
    @SerializedName("Producer")
    private final String Producer;
    @SerializedName("Painter")
    private final String Painter;
    @SerializedName("Description")
    private final String Description;
    @SerializedName("Remark")
    private final String Remark;
    @SerializedName("Name")
    private final String Name;
    @SerializedName("Duration")
    private final String Duration;
    @SerializedName("PremiereDate")
    private final String PremiereDate;
    @SerializedName("KDMTP")
    private final String KDMTP;
    @SerializedName("IsHidden")
    private final String IsHidden;
    @SerializedName("EnableSound")
    private final String EnableSound;
    @SerializedName("IdSupplier")
    private final String IdSupplier;
    @SerializedName("InstId")
    private final String InstId;
    @SerializedName("IdExtShow")
    private final String IdExtShow;
    @SerializedName("IdGenre")
    private final String IdGenre;
    @SerializedName("Actors")
    private final String Actors;
    @SerializedName("IdRollerman")
    private final String IdRollerman;
    @SerializedName("rol_name")
    private final String rol_name;
    @SerializedName("pict_name")
    private final String pict_name;
    @SerializedName("pu_number")
    private final String pu_number;
    @SerializedName("DateLastDisplay")
    private final String DateLastDisplay;
    @SerializedName("Creator")
    private final String Creator;
    @SerializedName("NameEng")
    private final String NameEng;
    @SerializedName("genre_name")
    private final String genre_name;
    @SerializedName("shot")
    private List<Shot> shots = new ArrayList<Shot>();
    @SerializedName("trailer")
    private List<Video> videos = new ArrayList<Video>();
    
    public ShowInfo(){
        IdShow = "";
        IdShowType = "";
        Producer = "";
        Painter = "";
        Description = "";
        Remark = "";
        Name = "";
        Duration = "";
        PremiereDate = "";
        KDMTP = "";
        IsHidden = "";
        EnableSound = "";
        IdSupplier = "";
        InstId = "";
        IdExtShow = "";
        IdGenre = "";
        Actors = "";
        IdRollerman = "";
        rol_name = "";
        pict_name = "";
        pu_number = "";
        DateLastDisplay = "";
        Creator = "";
        NameEng = "";
        genre_name = "";
    }

    public ShowInfo(ResultSet rs, KinomirLog logger, String shotsPath, String videoPath) throws SQLException {
        IdShow = rs.getString("IdShow");
        IdShowType = rs.getString("IdShowType");
        Producer = rs.getString("Producer");
        Painter = rs.getString("Painter");
        Description = rs.getString("Description");
        Remark = rs.getString("Remark");
        Name = rs.getString("Name");
        Duration = rs.getString("Duration");
        PremiereDate = rs.getString("PremiereDate");
        KDMTP = rs.getString("KDMTP");
        IsHidden = rs.getString("IsHidden");
        EnableSound = rs.getString("EnableSound");
        IdSupplier = rs.getString("IdSupplier");
        InstId = rs.getString("InstId");
        IdExtShow = rs.getString("IdExtShow");
        IdGenre = rs.getString("IdGenre");
        Actors = rs.getString("Actors");
        IdRollerman = rs.getString("IdRollerman");
        rol_name = rs.getString("rol_name");
        pict_name = rs.getString("pict_name");
        pu_number = rs.getString("pu_number");
        DateLastDisplay = rs.getString("DateLastDisplay");
        Creator = rs.getString("Creator");
        NameEng = rs.getString("NameEng");
        genre_name = rs.getString("genre_name");
        try {

            if (shotsPath != null) {

                File shotsDir = new File(shotsPath + rs.getString("IdShow"));
                if (shotsDir.exists() && shotsDir.isDirectory()) {
                    String[] files = shotsDir.list();
                    if ((files != null) && (files.length > 0)) {

                        for (String file : files) {
                            Shot shot = new Shot(file);
                            shots.add(shot);
                        }
                    }
                }
            }

            File videoDir = new File(videoPath + rs.getString("IdShow"));
            if (videoDir.exists() && videoDir.isDirectory()) {
                String[] files = videoDir.list();
                if ((files != null) && (files.length > 0)) {
                    
                    for (String file : files) {
                        Video video = new Video(file, "ext");
                        videos.add(video);
                    }
                }
            }
        } catch (SecurityException ex) {
            logger.error("Access denied or not exist" + ex.getMessage());
            logger.debug(ex.getMessage(), ex);
        }
    }

    @XmlElement(name = "IdShow")
    public String getIdShow() {
        return IdShow;
    }

    @XmlElement(name = "IdShowType")
    public String getIdShowType() {
        return IdShowType;
    }

    @XmlElement(name = "Producer")
    public String getProducer() {
        return Producer;
    }

    @XmlElement(name = "Painter")
    public String getPainter() {
        return Painter;
    }

    @XmlElement(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlElement(name = "Remark")
    public String getRemark() {
        return Remark;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return Name;
    }

    @XmlElement(name = "Duration")
    public String getDuration() {
        return Duration;
    }

    @XmlElement(name = "PremiereDate")
    public String getPremiereDate() {
        return PremiereDate;
    }

    @XmlElement(name = "KDMTP")
    public String getKDMTP() {
        return KDMTP;
    }

    @XmlElement(name = "IsHidden")
    public String getIsHidden() {
        return IsHidden;
    }

    @XmlElement(name = "EnableSound")
    public String getEnableSound() {
        return EnableSound;
    }

    @XmlElement(name = "IdSupplier")
    public String getIdSupplier() {
        return IdSupplier;
    }

    @XmlElement(name = "InstId")
    public String getInstId() {
        return InstId;
    }

    @XmlElement(name = "IdExtShow")
    public String getIdExtShow() {
        return IdExtShow;
    }

    @XmlElement(name = "IdGenre")
    public String getIdGenre() {
        return IdGenre;
    }

    @XmlElement(name = "Actors")
    public String getActors() {
        return Actors;
    }

    @XmlElement(name = "IdRollerman")
    public String getIdRollerman() {
        return IdRollerman;
    }

    @XmlElement(name = "DateLastDisplay")
    public String getDateLastDisplay() {
        return DateLastDisplay;
    }

    @XmlElement(name = "Creator")
    public String getCreator() {
        return Creator;
    }

    @XmlElement(name = "NameEng")
    public String getNameEng() {
        return NameEng;
    }

    @XmlRootElement(name = "shot")
    private static class Shot {

        @SerializedName("file")
        private String file;
        
        public Shot(){
            
        }

        public Shot(String file) {
            this.file = file;
        }

        @XmlAttribute(name = "file")
        public String getFile() {
            return file;
        }

    }

    @XmlRootElement(name = "trailer")
    private static class Video {

        @SerializedName("file")
        private String file;
        @SerializedName("type")
        private String type;
        @SerializedName("shot")
        private String shot;
        
        public Video(){
            
        }

        public Video(String file, String type) {
            this.file = file;
            this.type = type;
            this.shot = file + ".jpg";
        }

        @XmlAttribute(name = "file")
        public String getFile() {
            return file;
        }

        @XmlAttribute(name = "type")
        public String getType() {
            return type;
        }

        @XmlAttribute(name = "shot")
        public String getShot() {
            return shot;
        }
        
        
    }
    @XmlElement(name = "rol_name")
    public String getRol_name() {
        return rol_name;
    }

    @XmlElement(name = "pict_name")
    public String getPict_name() {
        return pict_name;
    }

    @XmlElement(name = "pu_number")
    public String getPu_number() {
        return pu_number;
    }

    @XmlElement(name = "genre_name")
    public String getGenre_name() {
        return genre_name;
    }
    @XmlElementWrapper(name = "shots")
    @XmlElement(name = "shot")
    public List<Shot> getShots() {
        return shots;
    }

    @XmlElementWrapper(name = "video")
    @XmlElement(name = "trailer")
    public List<Video> getVideos() {
        return videos;
    }
    
    

}
