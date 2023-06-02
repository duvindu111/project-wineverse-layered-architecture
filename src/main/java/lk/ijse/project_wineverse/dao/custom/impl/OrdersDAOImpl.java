package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.custom.OrdersDAO;
import lk.ijse.project_wineverse.entity.Orders;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {

    public boolean cancelDelivery(String ordid) throws SQLException {
        String sql = "UPDATE orders SET delivery=? WHERE order_id=?";

        return CrudUtil.execute(
                sql,
                false,
                ordid
        );
    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders(order_id,cust_id,delivery,order_date,order_time,order_payment) VALUES(?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getOrder_id(),
                entity.getCust_id(),
                entity.getDelivery(),
                entity.getOrder_date(),
                entity.getOrder_time(),
                entity.getOrder_payment()
        );
    }

    @Override
    public boolean update(Orders entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Orders findBy(String id) throws SQLException {
        return null;
    }

    public String getNextOrderId() throws SQLException {
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }


}
