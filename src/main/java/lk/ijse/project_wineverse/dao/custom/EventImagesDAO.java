package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Customer;
import lk.ijse.project_wineverse.entity.EventImages;

import java.io.InputStream;
import java.sql.SQLException;

public interface EventImagesDAO extends CrudDAO<EventImages> {

    public  boolean saveImage(String eventid, InputStream in) throws SQLException;
}
