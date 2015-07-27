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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ru.kinomir.processor.DataException;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class HallSchemaNCData extends DataNode {

    @SerializedName("places")
    List<HallSchemaNC> places = new ArrayList<HallSchemaNC>();

    public HallSchemaNCData() {

    }

    public HallSchemaNCData(ResultSet rs) throws DataException, SQLException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    HallSchemaNC place = new HallSchemaNC(rs);
                    places.add(place);
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }
    
    @XmlElementWrapper(name = "places")
    @XmlElement(name = "place")
    public List<HallSchemaNC> getPlaces() {
        return places;
    }
}
