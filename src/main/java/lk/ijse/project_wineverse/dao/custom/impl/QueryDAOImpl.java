package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.project_wineverse.dao.custom.QueryDAO;
import lk.ijse.project_wineverse.dto.OrderDetailDTO;
import lk.ijse.project_wineverse.entity.Custom;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.OrderDetailTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO{

    public ArrayList<Custom> getAll() throws SQLException {
        String sql = "SELECT order_detail.order_id,order_detail.item_code,item.item_name,order_detail.order_qty "+
                "FROM order_detail "+
                "INNER JOIN item "+
                "ON order_detail.item_code=item.item_code";

        ArrayList<Custom> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            Custom custom = new Custom();
            custom.setOrder_id(resultSet.getString(1));
            custom.setItem_code(resultSet.getString(2));
            custom.setItem_name(resultSet.getString(3));
            custom.setOrder_qty(resultSet.getInt(4));

            all.add(custom);
        }
        return all;
    }

    public Custom fillFields(String orderid) throws SQLException {
        String sql = "SELECT orders.cust_id,customer.cust_name,orders.delivery,orders.order_date,orders.order_time,orders.order_payment "+
                "FROM orders "+
                "INNER JOIN customer "+
                "ON orders.cust_id=customer.cust_id "+
                "WHERE orders.order_id=? ";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ResultSet resultSet=CrudUtil.execute(sql,orderid);
        if(resultSet.next()){
            LocalDate localDate = LocalDate.parse(resultSet.getString(4), formatter);
            Custom custom =new Custom();
            custom.setCust_id(resultSet.getString(1));
            custom.setCust_name(resultSet.getString(2));
            custom.setDelivery(resultSet.getBoolean(3));
            custom.setOrder_date(localDate);
            custom.setOrder_time(resultSet.getTime(5).toLocalTime());
            custom.setOrder_payment(resultSet.getDouble(6));
            return custom;
        }
        return null;
    }

    public ArrayList<Custom> searchbyorderdate(String date) throws SQLException {
        String sql = "SELECT order_detail.order_id,order_detail.item_code,item.item_name,order_detail.order_qty "+
                "FROM order_detail "+
                "INNER JOIN item "+
                "ON order_detail.item_code=item.item_code "+
                "INNER JOIN orders "+
                "ON order_detail.order_id=orders.order_id "+
                "WHERE orders.order_date=? ";

        ArrayList<Custom> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            Custom custom = new Custom();
            custom.setOrder_id(resultSet.getString(1));
            custom.setItem_code(resultSet.getString(2));
            custom.setItem_name(resultSet.getString(3));
            custom.setOrder_qty(resultSet.getInt(4));
            all.add(custom);
        }
        return all;
    }

    public ArrayList<PieChart.Data> getDataToPieChart() throws SQLException {
        String sql="SELECT item.item_name,COUNT(order_detail.item_code) "+
                "FROM order_detail "+
                "INNER JOIN item "+
                "ON item.item_code = order_detail.item_code " +
                "INNER JOIN orders " +
                "ON order_detail.order_id=orders.order_id " +
                "WHERE MONTH(orders.order_date) = MONTH(CURRENT_DATE()) " +
                "GROUP BY item.item_name " +
                "LIMIT 5 ";

        ArrayList<PieChart.Data> all = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            all.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return all;
    }

}
