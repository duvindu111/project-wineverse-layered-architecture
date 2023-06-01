package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.AddEventImageBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.EventDAO;
import lk.ijse.project_wineverse.dao.custom.EventImagesDAO;
import lk.ijse.project_wineverse.dto.EventImageDTO;
import lk.ijse.project_wineverse.entity.EventImages;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEventImageBOImpl implements AddEventImageBO {

    EventDAO eventDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EVENT);
    EventImagesDAO eventImagesDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EVENT_IMAGES);

    public List<String> loadEventIds() throws SQLException {
        return eventDAO.loadIds();
    }

    public boolean saveEventImage(String eventid, InputStream in) throws SQLException {
        return eventImagesDAO.saveImage(eventid,in);
    }

    public boolean deleteEventImage(String id) throws SQLException {
        return eventImagesDAO.delete(id);
    }


}
