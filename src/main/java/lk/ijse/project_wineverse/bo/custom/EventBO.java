package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.dao.SuperDAO;
import lk.ijse.project_wineverse.dto.EventDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EventBO extends SuperDAO {

    public List<String> loadIds() throws SQLException;

    public boolean saveEvent(EventDTO dto) throws SQLException, ClassNotFoundException;

    public ArrayList<EventDTO> getAllEvents() throws SQLException;

    public boolean updateEvent(EventDTO dto) throws SQLException;

    public boolean deleteEvent(String id) throws SQLException;

    public EventDTO findBy(String id) throws SQLException;
}
