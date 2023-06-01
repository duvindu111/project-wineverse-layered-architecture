package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.view.tdm.SupplyLoadDetailTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplyLoadDetailModel {

    public static ObservableList<SupplyLoadDetailTM> getAll() throws SQLException {
        String sql = "SELECT * FROM supply_load_details ";

        ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            obList.add(new SupplyLoadDetailTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            ));
        }
        return obList;
    }

    public static ObservableList<SupplyLoadDetailTM> searchbyloaddate(String date) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE load_date=? ";

        ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            obList.add(new SupplyLoadDetailTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            ));
        }
        return obList;
    }

    public static ObservableList<SupplyLoadDetailTM> searchbyloadid(String loadid) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE load_id=? ";

        ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql,loadid);
        while (resultSet.next()) {
            obList.add(new SupplyLoadDetailTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            ));
        }
        return obList;
    }

    public static ObservableList<SupplyLoadDetailTM> searchbysuppid(String suppid) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE supp_id=? ";

        ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql,suppid);
        while (resultSet.next()) {
            obList.add(new SupplyLoadDetailTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            ));
        }
        return obList;
    }
}
