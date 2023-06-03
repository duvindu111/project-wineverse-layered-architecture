package lk.ijse.project_wineverse.dao.custom;

import javafx.scene.image.Image;
import javafx.util.Pair;
import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Customer;
import lk.ijse.project_wineverse.entity.EventImages;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EventImagesDAO extends CrudDAO<EventImages> {

    public  boolean saveImage(String eventid, InputStream in) throws SQLException;

    public List<Pair<String, Image>> eventDataWithIds() throws SQLException;
}
