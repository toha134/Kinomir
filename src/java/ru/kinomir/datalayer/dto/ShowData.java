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
public class ShowData extends DataNode {

    @SerializedName("show")
    List<Show> shows = new ArrayList< Show>();

    public ShowData() {

    }
    
    private Show findShow(String idShow){
        for (Show show : shows){
            if (idShow.equalsIgnoreCase(show.getIdShow())){
                return show;
            }
        }
        return null;
    }

    public ShowData(ResultSet rs) throws DataException, SQLException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    Show show = findShow(rs.getString("IdShow"));
                    if (show == null){
                        show = new Show(rs);
                        shows.add(show);
                    }
                    Hall hall = new Hall(rs);
                    show.addHall(hall);
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    @XmlElement(name = "show")
    public List< Show> getShows() {
        return shows;
    }
    
    @XmlRootElement(name = "hall")
    protected static class Hall{
        @SerializedName("IdBuilding")
        private String IdBuilding;
        @SerializedName("IdHall")
        private String IdHall;
        
        Hall(){
            
        }
        
        public Hall(ResultSet rs) throws SQLException{
            IdBuilding = rs.getString("IdBuilding");
            IdHall = rs.getString("IdHall");
        }

        @XmlAttribute(name = "IdBuilding")
        public String getIdBuilding() {
            return IdBuilding;
        }

        @XmlAttribute(name = "IdHall")
        public String getIdHall() {
            return IdHall;
        }
        
        
        
    }
}
