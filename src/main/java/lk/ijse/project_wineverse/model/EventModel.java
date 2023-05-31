package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lk.ijse.project_wineverse.dto.EventDTO;
import lk.ijse.project_wineverse.dto.tm.EventTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventModel {
    public static boolean save(EventDTO event) throws SQLException, FileNotFoundException {
        String sql = "INSERT INTO event(event_id,event_name,event_type,event_date,event_time,emp_id) " +
                "VALUES(?,?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, event.getEventid());
//            pstm.setString(2, event.getEventname());
//            pstm.setString(3, event.getEventtype());
//            pstm.setString(4, event.getEventdate());
//            pstm.setString(5, event.getEventtime());
//            pstm.setString(6, event.getEmpid());
//
//            return pstm.executeUpdate() > 0;
//        }
//        boolean result = addimage(event.getEventid(),filepath);
//        if(!result){
//            System.out.println("Image not added");
//        }
        return CrudUtil.execute(
                sql,
                event.getEventid(),
                event.getEventname(),
                event.getEventtype(),
                event.getEventdate(),
                event.getEventtime(),
                event.getEmpid()
        );
    }

//    public static boolean addimage(String id,String filepath) throws FileNotFoundException, SQLException {
//        String sql ="UPDATE event SET picture = ? WHERE event_id = ?";
//
//
//        InputStream in = new FileInputStream("filepath");
//
//        return CrudUtil.execute(
//                sql,
//                in,
//                id
//        );
//    }

    public static ObservableList<EventTM> getAll() throws SQLException {
        String sql = "SELECT * FROM event";

            ObservableList<EventTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new EventTM(
                        resultSet.getString(6),
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)

                ));
            }
            return obList;
    }

    public static boolean update(EventDTO event) throws SQLException {
        String sql = "UPDATE event SET event_name=?,event_type=?,event_date=?,event_time=?,emp_id=? WHERE event_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, event.getEventname());
//            pstm.setString(2, event.getEventtype());
//            pstm.setString(3, event.getEventdate());
//            pstm.setString(4, event.getEventtime());
//            pstm.setString(5, event.getEmpid());
//            pstm.setString(6, event.getEventid());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                event.getEventname(),
                event.getEventtype(),
                event.getEventdate(),
                event.getEventtime(),
                event.getEmpid(),
                event.getEventid()
        );
    }

    public static boolean delete(String eventid) throws SQLException {
        String sql = "DELETE FROM event WHERE event_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, eventid);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,eventid);
    }

    public static EventDTO findById(String id) throws SQLException {
        String sql = "SELECT * FROM event WHERE event_id=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new EventDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }
            return null;
    }

    public static List<Image> eventData() throws SQLException {
        //        String sql = "SELECT eventName FROM event";
//        ResultSet resultSet = CrudUtil.execute(sql);
//        List<String> data = new ArrayList<>();
//        while (resultSet.next()) {
//            data.add(
//                    resultSet.getString(1)
//            );
//        }
//        return data;

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

    public static List<String> loadIds() throws SQLException {
        String sql = "SELECT event_id FROM event";
        ResultSet resultSet =CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
