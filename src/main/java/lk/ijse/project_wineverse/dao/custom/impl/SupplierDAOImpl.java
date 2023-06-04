package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.SupplierDAO;
import lk.ijse.project_wineverse.dto.SupplierDTO;
import lk.ijse.project_wineverse.entity.Supplier;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.SupplierTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier(supp_id,supp_name,supp_email,supp_contact,supp_address) " +
                "VALUES(?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getSupp_id(),
                entity.getSupp_name(),
                entity.getSupp_email(),
                entity.getSupp_contact(),
                entity.getSupp_address()
        );
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        String sql = "UPDATE Supplier SET supp_name=?,supp_email=?,supp_contact=?,supp_address=? WHERE supp_id=?";
        return CrudUtil.execute(
                sql,
                entity.getSupp_name(),
                entity.getSupp_email(),
                entity.getSupp_contact(),
                entity.getSupp_address(),
                entity.getSupp_id()
        );
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        ArrayList<Supplier> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supp_id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Supplier findBy(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supp_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(3)
            ));
        }
        return null;
    }

    public List<String> loadIds() throws SQLException {
        String sql = "SELECT supp_id FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public  String getSupplierName(String supp_id) throws SQLException {
        String sql = "SELECT supp_name FROM supplier WHERE supp_id=?";
        ResultSet resultSet = CrudUtil.execute(sql,supp_id);

        if(resultSet.next()){
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }
}
