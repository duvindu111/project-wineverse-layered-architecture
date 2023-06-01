package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface EventDAO extends SuperDAO {

    public List<String> loadIds() throws SQLException;
}
