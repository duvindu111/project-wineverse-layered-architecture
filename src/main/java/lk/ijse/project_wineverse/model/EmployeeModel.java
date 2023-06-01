package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.project_wineverse.dto.EmployeeDTO;
import lk.ijse.project_wineverse.view.tdm.EmployeeTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    @FXML
    private TextField txtempid;

    public static boolean save(EmployeeDTO employee) throws SQLException {
        String sql = "INSERT INTO employee(emp_id,emp_name,emp_dob,emp_address,emp_contact,emp_email,emp_job_title,emp_nic) " +
                "VALUES(?,?,?,?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, employee.getId());
//            pstm.setString(2, employee.getName());
//            pstm.setString(3, employee.getDob());
//            pstm.setString(4, employee.getAddress());
//            pstm.setString(5, employee.getContact());
//            pstm.setString(6, employee.getEmail());
//            pstm.setString(7, employee.getJob());
//            pstm.setString(8, employee.getNic());
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                employee.getId(),
                employee.getName(),
                employee.getDob(),
                employee.getAddress(),
                employee.getContact(),
                employee.getEmail(),
                employee.getJob(),
                employee.getNic());

    }

    public static ObservableList<EmployeeTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new EmployeeTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));
            }
            return obList;
    }

    public static EmployeeDTO findById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE emp_id=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new EmployeeDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(8),
                        resultSet.getString(3),
                        resultSet.getString(7),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getString(6)
                ));
            }
            return null;
    }

    public static boolean update(EmployeeDTO employee) throws SQLException {
        String sql = "UPDATE Employee SET emp_name=?,emp_dob=?,emp_address=?,emp_contact=?,emp_email=?,emp_job_title=?,emp_nic=? WHERE emp_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, employee.getName());
//            pstm.setString(2, employee.getDob());
//            pstm.setString(3, employee.getAddress());
//            pstm.setString(4, employee.getContact());
//            pstm.setString(5, employee.getEmail());
//            pstm.setString(6, employee.getJob());
//            pstm.setString(7, employee.getNic());
//            pstm.setString(8, employee.getId());
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                employee.getName(),
                employee.getDob(),
                employee.getAddress(),
                employee.getContact(),
                employee.getEmail(),
                employee.getJob(),
                employee.getNic(),
                employee.getId()
        );
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE emp_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, id);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,id);
    }

    public static List<String> loadIds() throws SQLException {
        String sql = "SELECT emp_id FROM employee";
            ResultSet resultSet =CrudUtil.execute(sql);

            List<String> data = new ArrayList<>();
            while (resultSet.next()) {
                data.add(resultSet.getString(1));
            }
            return data;
    }

}
