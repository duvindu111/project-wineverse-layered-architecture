package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.SupplyLoadDetailsDAO;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.SupplyLoadDetailTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyLoadDetailsDAOImpl implements SupplyLoadDetailsDAO {
    @Override
    public boolean save(SupplyLoadDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplyLoadDetails entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<SupplyLoadDetails> getAll() throws SQLException {
        String sql = "SELECT * FROM supply_load_details ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public SupplyLoadDetails findBy(String id) throws SQLException {
        return null;
    }

    public ArrayList<SupplyLoadDetails> searchbyloadid(String loadid) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE load_id=? ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,loadid);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    public ArrayList<SupplyLoadDetails> searchbyloaddate(String date) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE load_date=? ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    public ArrayList<SupplyLoadDetails> searchbysuppid(String suppid) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE supp_id=? ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,suppid);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }
}
