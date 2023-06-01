package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<Delivery> {

    public Delivery findBydeliveryId(String delid) throws SQLException;

    public ArrayList<Delivery> getByDueDate(String duedate) throws SQLException;

    public ArrayList<Delivery> getByDeliveryStatus(String delists) throws SQLException;
}
