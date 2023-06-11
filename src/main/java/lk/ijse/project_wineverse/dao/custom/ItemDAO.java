package lk.ijse.project_wineverse.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.entity.Item;
import lk.ijse.project_wineverse.entity.OrderDetail;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {

    public List<String> loadItemCodes() throws SQLException;

    public boolean updateQty(OrderDetail orderDetail) throws SQLException;

    public boolean addQty(SupplyLoadDetails placeSupplyLoad) throws SQLException;

    public ArrayList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException;

}
