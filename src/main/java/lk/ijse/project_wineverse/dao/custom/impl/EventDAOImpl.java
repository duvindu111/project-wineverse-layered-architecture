package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.EventDAO;
import lk.ijse.project_wineverse.dto.EventDTO;
import lk.ijse.project_wineverse.entity.Event;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.EventTM;

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

    @Override
    public boolean save(Event entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO event(event_id,event_name,event_type,event_date,event_time,emp_id) " +
                "VALUES(?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getEvent_id(),
                entity.getEvent_name(),
                entity.getEvent_type(),
                entity.getEvent_date(),
                entity.getEvent_time(),
                entity.getEmp_id()
        );
    }

    @Override
    public boolean update(Event entity) throws SQLException {
        String sql = "UPDATE event SET event_name=?,event_type=?,event_date=?,event_time=?,emp_id=? WHERE event_id=?";

        return CrudUtil.execute(
                sql,
                entity.getEvent_name(),
                entity.getEvent_type(),
                entity.getEvent_date(),
                entity.getEvent_time(),
                entity.getEmp_id(),
                entity.getEvent_id()
        );
    }

    @Override
    public ArrayList<Event> getAll() throws SQLException {
        String sql = "SELECT * FROM event";

        ArrayList<Event> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new Event(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getTime(5).toLocalTime(),
                    resultSet.getString(6)

            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM event WHERE event_id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Event findBy(String id) throws SQLException {
        String sql = "SELECT * FROM event WHERE event_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Event(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getTime(5).toLocalTime(),
                    resultSet.getString(6)
            ));
        }
        return null;
    }
}
