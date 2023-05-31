package lk.ijse.project_wineverse.model;

import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class AdminOrderDetailModel {

    public static boolean save(String orderid, List<PlaceOrderDTO> placeOrderList) throws SQLException {
        for(PlaceOrderDTO placeOrder : placeOrderList) {
            if(!save(orderid, placeOrder)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String orderid, PlaceOrderDTO placeOrder) throws SQLException {
        String sql = "INSERT INTO order_detail(order_id,item_code,order_qty)" +
                "VALUES(?, ?, ?)";

        return CrudUtil.execute(
            sql,
            orderid,
            placeOrder.getOrdereditemcode(),
            placeOrder.getOrdereditemqty()
        );
    }
}
