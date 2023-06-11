package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    public boolean saveOrderDetails(String orderid, OrderDetail orderDetail) throws SQLException;

    }
