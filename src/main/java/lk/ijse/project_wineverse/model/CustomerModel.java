package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dto.CustomerDTO;
import lk.ijse.project_wineverse.dto.tm.CustomerTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static boolean save(CustomerDTO customer) throws SQLException {
        String sql = "INSERT INTO customer(cust_id,cust_name,cust_email,cust_contact) " +
                "VALUES(?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, customer.getId());
//            pstm.setString(2, customer.getName());
//            pstm.setString(3, customer.getEmail());
//            pstm.setString(4, customer.getContact());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getContact());
    }

    public static ObservableList<CustomerTM> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new CustomerTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
            return obList;
    }

    public static boolean update(CustomerDTO customer) throws SQLException {
        String sql = "UPDATE customer SET cust_name=?,cust_email=?,cust_contact=? WHERE cust_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, customer.getName());
//            pstm.setString(2, customer.getEmail());
//            pstm.setString(3, customer.getContact());
//            pstm.setString(4, customer.getId());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                customer.getName(),
                customer.getEmail(),
                customer.getContact(),
                customer.getId());
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE cust_id=?";
        return CrudUtil.execute(
                sql,
                id);
    }

    public static CustomerDTO findById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE cust_id=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new CustomerDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
            return null;

    }

    public static List<String> loadIds() throws SQLException {
        String sql = "SELECT cust_id FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static String getCustName(String cust_id) throws SQLException {
        String sql = "SELECT cust_name FROM customer WHERE cust_id=?";
        ResultSet resultSet = CrudUtil.execute(sql,cust_id);

        if(resultSet.next()){
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }

    public static int ordercountbycustid(String id) throws SQLException {
        String sql = "SELECT COUNT(order_id) FROM orders WHERE cust_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        int count;
        if(resultSet.next()) {
            return count = resultSet.getInt(1);
        }
        return 0;
    }

    public static String getEmailByCustID(String custId) throws SQLException {
        String sql = "SELECT cust_email FROM customer WHERE cust_id=?";
        ResultSet resultSet = CrudUtil.execute(sql,custId);

        if(resultSet.next()){
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }
}
