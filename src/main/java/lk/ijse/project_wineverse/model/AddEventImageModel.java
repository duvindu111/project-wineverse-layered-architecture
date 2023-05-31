package lk.ijse.project_wineverse.model;

import javafx.scene.image.Image;
import javafx.util.Pair;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEventImageModel {
    public static boolean saveImage(String eventid, InputStream in) throws SQLException {
        String sql ="INSERT INTO event_images VALUES(?,?)";

        return CrudUtil.execute(
                sql,
                eventid,
                in
        );
    }


    public static List<Image> eventData() throws SQLException {
        String sql = "SELECT image FROM event_images";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Image> data = new ArrayList<>();
        while (resultSet.next()) {

            byte[]imageData=resultSet.getBytes(1);
            Image image=new Image(new ByteArrayInputStream(imageData));
            data.add(image);
        }
        return data;
    }

    public static List<Pair<String, Image>> eventDataWithIds() throws SQLException {
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

    public static boolean deleterow(String eventid) throws SQLException {
        String sql = "DELETE FROM event_images WHERE event_id=?";

        return CrudUtil.execute(
                sql,
                eventid
        );
    }
}
