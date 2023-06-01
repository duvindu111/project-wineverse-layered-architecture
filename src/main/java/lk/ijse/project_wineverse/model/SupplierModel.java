package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dto.SupplierDTO;
import lk.ijse.project_wineverse.view.tdm.SupplierTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static boolean save(SupplierDTO supplier) throws SQLException {
        String sql = "INSERT INTO supplier(supp_id,supp_name,supp_email,supp_contact,supp_address) " +
                "VALUES(?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, supplier.getId());
//            pstm.setString(2, supplier.getName());
//            pstm.setString(3, supplier.getEmail());
//            pstm.setString(4, supplier.getContact());
//            pstm.setString(5, supplier.getAddress());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getContact(),
                supplier.getAddress()
        );
    }

    public static ObservableList<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new SupplierTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getString(3)
                ));
            }
            return obList;
    }

    public static boolean update(SupplierDTO supplier) throws SQLException {
        String sql = "UPDATE Supplier SET supp_name=?,supp_email=?,supp_contact=?,supp_address=? WHERE supp_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, supplier.getName());
//            pstm.setString(2, supplier.getEmail());
//            pstm.setString(3, supplier.getContact());
//            pstm.setString(4, supplier.getAddress());
//            pstm.setString(5, supplier.getId());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                supplier.getName(),
                supplier.getEmail(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getId()
        );
    }

    public static SupplierDTO findById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supp_id=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new SupplierDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getString(3)
                ));
            }
            return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supp_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, id);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,id);
    }

    public static List<String> loadIds() throws SQLException {
        String sql = "SELECT supp_id FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static String getSupplierName(String supp_id) throws SQLException {
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
