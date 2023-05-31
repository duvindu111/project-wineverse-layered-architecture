package lk.ijse.project_wineverse.model;

import javafx.scene.chart.XYChart;
import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.dto.NewDeliveryDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CashierOrderModel {


    static NewDeliveryDTO gotnewdelivery;

    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("ORD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "ORD-" + digit;
        }
        return "ORD-001";
    }

    public static boolean placeOrder(String orderid, String custid, Boolean delivery, String ordpay, List<PlaceOrderDTO> placeOrderList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(orderid, custid, delivery, LocalDate.now(), LocalTime.now(), ordpay);
            if (isSaved) {
                boolean isUpdated = ItemModel.updateQty(placeOrderList);
                if (isUpdated) {
                    boolean isOrdered = AdminOrderDetailModel.save(orderid, placeOrderList);
                    if (isOrdered) {
                        if (delivery) {
                            boolean isDelivered = NewDeliveryModel.save(gotnewdelivery);
                            if (isDelivered) {
                                con.commit();
                                return true;
                            }
                        } else {
                            con.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean save(String orderid, String custid, Boolean delivery, LocalDate date, LocalTime time, String ordpay) throws SQLException {
        String sql = "INSERT INTO orders(order_id,cust_id,delivery,order_date,order_time,order_payment) VALUES(?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                orderid,
                custid,
                delivery,
                date,
                time,
                ordpay
        );
    }

    public static void sendObject(NewDeliveryDTO newDelivery) {
        gotnewdelivery = newDelivery;
    }

    public static boolean updatedelivery(String ordid) throws SQLException {
        String sql = "UPDATE orders SET delivery=? WHERE order_id=?";

        return CrudUtil.execute(
                sql,
                false,
                ordid
        );
    }

    public static List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException {
        String sql= "SELECT MONTHNAME(order_date) AS month,SUM(order_payment) AS total_income FROM orders WHERE YEAR(order_date)=? GROUP BY month ORDER BY month desc";

        List<XYChart.Data<String, Double>> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql,year);

        while(resultSet.next()){
            String month = resultSet.getString("month");
            double income = resultSet.getDouble("total_income");
            data.add(new XYChart.Data<>(month, income));
        }
        return data;
    }

}
