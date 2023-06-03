package lk.ijse.project_wineverse.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<Orders> {

    public boolean cancelDelivery(String ordid) throws SQLException;

    public String getNextOrderId() throws SQLException;

    public int totalOrdersToday() throws SQLException;

    public int totalOrdersMonth() throws SQLException;

    public List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException;
}
