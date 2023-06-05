package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.scene.image.Image;
import javafx.util.Pair;
import lk.ijse.project_wineverse.dao.custom.EventImagesDAO;
import lk.ijse.project_wineverse.entity.EventImages;
import lk.ijse.project_wineverse.dao.util.CrudUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Pair<String, Image>> eventDataWithIds() throws SQLException {
        String sql = "SELECT event_id,image FROM event_images";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Pair<String, Image>> data = new ArrayList<>();
        while (resultSet.next()) {
            String eventid=resultSet.getString(1);
            byte[]imageData=resultSet.getBytes(2);
            Image image=new Image(new ByteArrayInputStream(imageData));
            Pair<String, Image> eventImage = new Pair<>(eventid, image);
            data.add(eventImage);
        }
        return data;
    }
}
