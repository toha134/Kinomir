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
import ru.kinomir.tools.KinomirLog;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
public class ShowInfoData extends DataNode {

    @SerializedName("show")
    List<ShowInfo> shows = new ArrayList<ShowInfo>();

    public ShowInfoData() {

    }

    public ShowInfoData(ResultSet rs, KinomirLog logger, String shotPath, String videoPath) throws DataException, SQLException {
        if (rs != null) {
            try {
                while (rs.next()) {
                    ShowInfo show = new ShowInfo(rs, logger, shotPath, videoPath);
                    shows.add(show);
                }
            } catch (SQLException ex) {
                throw SqlUtils.convertErrorToException(rs, ex);
            }
        } else {
            throw new DataException("1", "No result for query");
        }
    }

    @XmlElement(name = "show")
    public List<ShowInfo> getShows() {
        return shows;
    }
}
