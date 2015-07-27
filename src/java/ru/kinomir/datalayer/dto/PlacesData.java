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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class PlacesData extends DataNode {

    @SerializedName("place")
    private List<Place> places = new ArrayList<Place>();

    public PlacesData() {

    }

    public PlacesData(ResultSet rs) throws SQLException, DataException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    Place place = new Place(rs);
                    places.add(place);
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    @XmlElement(name = "place")
    public List<Place> getPlaces() {
        return places;
    }

}
