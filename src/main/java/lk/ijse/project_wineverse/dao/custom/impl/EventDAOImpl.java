package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.custom.EventDAO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    public List<String> loadIds() throws SQLException {
        String sql = "SELECT event_id FROM event";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
