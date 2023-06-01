package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.custom.EventImagesDAO;
import lk.ijse.project_wineverse.entity.Customer;
import lk.ijse.project_wineverse.entity.EventImages;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventImagesDAOImpl implements EventImagesDAO {

    public boolean saveImage(String eventid, InputStream in) throws SQLException {
        String sql ="INSERT INTO event_images VALUES(?,?)";

        return CrudUtil.execute(
                sql,
                eventid,
                in
        );
    }


    @Override
    public boolean save(EventImages entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EventImages entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<EventImages> getAll() throws SQLException {
        return null;
    }


    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM event_images WHERE event_id=?";

        return CrudUtil.execute(
                sql,
                id
        );
    }

    @Override
    public EventImages findBy(String id) throws SQLException {
        return null;
    }
}
