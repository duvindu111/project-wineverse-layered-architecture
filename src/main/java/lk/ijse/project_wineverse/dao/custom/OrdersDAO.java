package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {

    public boolean cancelDelivery(String ordid) throws SQLException;

    public String getNextOrderId() throws SQLException;
}
