/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.processor;

import java.io.File;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.dom4j.Element;
import ru.kinomir.datalayer.KinomirManager;
import ru.kinomir.datalayer.dto.DataNode;
import ru.kinomir.tools.sql.SqlUtils;

/**
 *
 * @author Admin
 */
public class MyWebShowInfoProcessor extends AbstractRequestProcessor {

    private final static String[] columns = {"IdShow", "IdShowType", "Producer", "Painter",
        "Description", "Remark", "Name", "Duration", "PremiereDate", "KDMTP", "IsHidden",
        "EnableSound", "IdSupplier", "InstId", "IdExtShow", "IdGenre", "Actors",
        "IdRollerman", "rol_name", "pict_name", "pu_number", "DateLastDisplay", "Creator",
        "NameEng", "genre_name"};

    @Override
    protected void fillAnswerData(Connection conn, Map<String, String> params, Element el) throws SQLException, InvalidParameterException {
        PreparedStatement sp = null;
        ResultSet rs = null;
        try {
            sp = conn.prepareStatement("exec dbo.MyWeb_ShowInfo ?");
            if (params.get(KinomirManager.IDSHOW) != null) {
                sp.setInt(1, Integer.parseInt(params.get(KinomirManager.IDSHOW)));
            } else {
                sp.setNull(1, java.sql.Types.INTEGER);
            }
            rs = sp.executeQuery();
            while (rs.next()) {
                Element item = el.addElement("show");
                for (String column : columns) {
                    try {
                        item.addElement(column).addText(rs.getObject(column) == null ? "" : rs.getString(column));
                    } catch (SQLException ex) {
                        logger.error("Column '" + column + "' not found!", ex);
                    }
                }

                try {
                    String shotsPath = getInitParam("shots_path");
                    if (shotsPath != null) {

                        File shotsDir = new File(shotsPath + rs.getString("IdShow"));
                        if (shotsDir.exists() && shotsDir.isDirectory()) {
                            String[] files = shotsDir.list();
                            if ((files != null) && (files.length > 0)) {
                                Element shots = item.addElement("shots");
                                for (String file : files) {
                                    shots.addElement("shot").addAttribute("file", file);
                                }
                            }
                        }
                    }
                    String videoPath = getInitParam("video_path");
                    File videoDir = new File(videoPath + rs.getString("IdShow"));
                    if (videoDir.exists() && videoDir.isDirectory()) {
                        String[] files = videoDir.list();
                        if ((files != null) && (files.length > 0)) {
                            Element video = item.addElement("video");
                            for (String file : files) {
                                video.addElement("trailer")
                                        .addAttribute("file", file)
                                        .addAttribute("type", "ext")
                                        .addAttribute("shot", file + ".jpg");
                            }
                        }
                    }
                } catch (SecurityException ex) {
                    logger.error("Access denied or not exist" + ex.getMessage());
                    logger.debug(ex.getMessage(), ex);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(rs.getString("ErrorDescription"), rs.getString("Error"), ex);
        } finally {
            SqlUtils.closeSQLObjects(rs, sp);
        }
    }

    @Override
    protected DataNode getData(Connection conn, Map<String, String> params) throws SQLException, InvalidParameterException, DataException {
        return KinomirManager.getShowInfo(conn, params, logger, getInitParam("shots_path"), getInitParam("video_path"));
    }

}
