package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.project_wineverse.dto.OrderDetailDTO;
import lk.ijse.project_wineverse.dto.tm.OrderDetailTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDetailModel {
    public static ObservableList<OrderDetailTM> getAll() throws SQLException {
        String sql = "SELECT order_detail.order_id,order_detail.item_code,item.item_name,order_detail.order_qty "+
                "FROM order_detail "+
                "INNER JOIN item "+
                "ON order_detail.item_code=item.item_code";

        ObservableList<OrderDetailTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            obList.add(new OrderDetailTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return obList;
    }

    public static OrderDetailDTO fillFields(String orderid) throws SQLException {
        String sql = "SELECT orders.cust_id,customer.cust_name,orders.delivery,orders.order_date,orders.order_time,orders.order_payment "+
                "FROM orders "+
                "INNER JOIN customer "+
                "ON orders.cust_id=customer.cust_id "+
                "WHERE orders.order_id=? ";

        ResultSet resultSet=CrudUtil.execute(sql,orderid);
        if(resultSet.next()){
            return (new OrderDetailDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBoolean(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)
            ));
        }
        return null;
    }

    public static ObservableList<OrderDetailTM> searchbyorderdate(String date) throws SQLException {
        String sql = "SELECT order_detail.order_id,order_detail.item_code,item.item_name,order_detail.order_qty "+
                "FROM order_detail "+
                "INNER JOIN item "+
                "ON order_detail.item_code=item.item_code "+
                "INNER JOIN orders "+
                "ON order_detail.order_id=orders.order_id "+
                "WHERE orders.order_date=? ";

        ObservableList<OrderDetailTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            obList.add(new OrderDetailTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return obList;
    }

    public static int totalOrdersToday() throws SQLException {
        String sql = "SELECT COUNT(order_id) FROM orders WHERE order_date=?";

        String currDate = String.valueOf(LocalDate.now());
        ResultSet resultSet=CrudUtil.execute(sql,currDate);

        int count;
        if(resultSet.next()) {
           return count = resultSet.getInt(1);
        }
        return 0;
    }

    public static int totalOrdersMonth() throws SQLException {
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

    public static ObservableList<PieChart.Data> getDataToPieChart() throws SQLException {
        String sql="SELECT item.item_name,COUNT(order_detail.item_code) "+
                "FROM order_detail "+
                "INNER JOIN item "+
                "ON item.item_code = order_detail.item_code " +
                "INNER JOIN orders " +
                "ON order_detail.order_id=orders.order_id " +
                "WHERE MONTH(orders.order_date) = MONTH(CURRENT_DATE()) " +
                "GROUP BY item.item_name " +
                "LIMIT 5 ";
        ObservableList<PieChart.Data> datalist =FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            datalist.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return datalist;
    }
}
