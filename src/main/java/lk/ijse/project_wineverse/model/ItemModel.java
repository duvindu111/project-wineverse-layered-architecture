package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.dto.tm.ItemTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    public static boolean save(ItemDTO item) throws SQLException {
        String sql = "INSERT INTO item(item_code,item_name,item_unit_price,item_category,item_qty) " +
                "VALUES(?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, item.getCode());
//            pstm.setString(2, item.getName());
//            pstm.setDouble(3, item.getUnitprice());
//            pstm.setString(4, item.getCategory());
//            pstm.setInt(5, item.getQuantity());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                item.getCode(),
                item.getName(),
                item.getUnitprice(),
                item.getCategory(),
                item.getQuantity()
        );
    }

    public static ObservableList<ItemTM> getAll() throws SQLException {
        String sql = "SELECT * FROM item";

            ObservableList<ItemTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new ItemTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
            return obList;
    }

    public static ItemDTO findById(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_code=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new ItemDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE item_code=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, id);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,id);
    }

    public static boolean update(ItemDTO item) throws SQLException {
        String sql = "UPDATE item SET item_name=?,item_unit_price=?,item_category=?,item_qty=? WHERE item_code=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, item.getName());
//            pstm.setDouble(2, item.getUnitprice());
//            pstm.setString(3, item.getCategory());
//            pstm.setInt(4, item.getQuantity());
//            pstm.setString(5, item.getCode());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                item.getName(),
                item.getUnitprice(),
                item.getCategory(),
                item.getQuantity(),
                item.getCode()
        );
    }

    public static List<String> loadCodes() throws SQLException {
        String sql = "SELECT item_code FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static boolean updateQty(List<PlaceOrderDTO> placeOrderList) throws SQLException {
        for(PlaceOrderDTO placeorder : placeOrderList) {
            if(!updateQty(placeorder)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(PlaceOrderDTO placeorder) throws SQLException {
        String sql = "UPDATE item SET item_qty = (item_qty - ?) WHERE item_code = ?";

        return CrudUtil.execute(
          sql,
          placeorder.getOrdereditemqty(),
          placeorder.getOrdereditemcode()
        );
    }

    public static boolean addQty(List<PlaceSupplyLoadDTO> placeSupplyLoadList) throws SQLException {
        for(PlaceSupplyLoadDTO placeSupplyLoad : placeSupplyLoadList) {
            if(!addQty(placeSupplyLoad)) {
                return false;
            }
        }
        return true;
    }

    private static boolean addQty(PlaceSupplyLoadDTO placeSupplyLoad) throws SQLException {
        String sql = "UPDATE item SET item_qty = (item_qty + ?) WHERE item_code = ?";

        return CrudUtil.execute(
                sql,
                placeSupplyLoad.getSuppqty(),
                placeSupplyLoad.getItemcode()
        );
    }

    public static ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {
        String sql="SELECT item_name,item_qty FROM item WHERE item_qty<=100 ";

        ObservableList<XYChart.Series<String, Integer>> datalist =FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        // Creating a new series object
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        while(resultSet.next()){
            String itemName = resultSet.getString("item_name");
            int itemQty = resultSet.getInt("item_qty");
            series.getData().add(new XYChart.Data<>(itemName, itemQty));
        }

        datalist.add(series);
        return datalist;
    }
}
