package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.dao.SuperDAO;
import lk.ijse.project_wineverse.entity.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventDAO extends CrudDAO<Event> {

    public List<String> loadIds() throws SQLException;
}
