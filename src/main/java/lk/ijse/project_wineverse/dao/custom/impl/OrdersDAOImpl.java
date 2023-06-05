package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.project_wineverse.dao.custom.OrdersDAO;
import lk.ijse.project_wineverse.entity.Orders;
import lk.ijse.project_wineverse.dao.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public int totalOrdersToday() throws SQLException {
        String sql = "SELECT COUNT(order_id) FROM orders WHERE order_date=?";

        String currDate = String.valueOf(LocalDate.now());
        ResultSet resultSet=CrudUtil.execute(sql,currDate);

        int count;
        if(resultSet.next()) {
            return count = resultSet.getInt(1);
        }
        return 0;
    }

    public int totalOrdersMonth() throws SQLException {
        String sql = "SELECT COUNT(order_id) FROM orders "+
                "WHERE YEAR(order_date) = YEAR(CURDATE()) "+
                "AND MONTH(order_date) = MONTH(CURDATE()) ";

        ResultSet resultSet=CrudUtil.execute(sql);

        int count;
        if(resultSet.next()) {
            return count = resultSet.getInt(1);
        }
        return 0;
    }

    public List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException {
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
