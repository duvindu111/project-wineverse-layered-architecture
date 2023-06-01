package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface AddEventImageBO extends SuperBO {

    public List<String> loadEventIds() throws SQLException;

    public boolean saveEventImage(String eventid, InputStream in) throws SQLException;

    public boolean deleteEventImage(String id) throws SQLException;
}
