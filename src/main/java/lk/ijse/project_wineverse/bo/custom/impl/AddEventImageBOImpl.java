package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.AddEventImageBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.EventDAO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEventImageBOImpl implements AddEventImageBO {

    EventDAO eventDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EVENT);

    public List<String> loadEventIds() throws SQLException {
        return eventDAO.loadIds();
    }


}
