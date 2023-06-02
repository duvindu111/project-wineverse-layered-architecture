package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.ItemDAO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.entity.Item;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.ItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item(item_code,item_name,item_unit_price,item_category,item_qty) " +
                "VALUES(?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getItem_code(),
                entity.getItem_name(),
                entity.getUnit_price(),
                entity.getItem_category(),
                entity.getItem_qty()
        );
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        String sql = "UPDATE item SET item_name=?,item_unit_price=?,item_category=?,item_qty=? WHERE item_code=?";

        return CrudUtil.execute(
                sql,
                entity.getItem_name(),
                entity.getUnit_price(),
                entity.getItem_category(),
                entity.getItem_qty(),
                entity.getItem_code()
        );
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM item";

        ArrayList<Item> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE item_code=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Item findBy(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_code=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return null;
    }

    public List<String> loadItemCodes() throws SQLException {
        String sql = "SELECT item_code FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public boolean updateQty(List<PlaceOrderDTO> placeOrderList) throws SQLException {
        for(PlaceOrderDTO placeorder : placeOrderList) {
            if(!updateQty(placeorder)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(PlaceOrderDTO placeorder) throws SQLException {
        String sql = "UPDATE item SET item_qty = (item_qty - ?) WHERE item_code = ?";

        return CrudUtil.execute(
                sql,
                placeorder.getOrdereditemqty(),
                placeorder.getOrdereditemcode()
        );
    }
}
