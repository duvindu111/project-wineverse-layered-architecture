package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.custom.OrderDetailDAO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.entity.OrderDetail;
import lk.ijse.project_wineverse.dao.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean saveOrderDetails(String orderid, OrderDetail orderDetail ) throws SQLException {
        String sql = "INSERT INTO order_detail(order_id,item_code,order_qty)" +
                "VALUES(?, ?, ?)";

        return CrudUtil.execute(
                sql,
                orderid,
                orderDetail.getItem_code(),
                orderDetail.getOrder_qty()
        );
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public OrderDetail findBy(String id) throws SQLException {
        return null;
    }
}
