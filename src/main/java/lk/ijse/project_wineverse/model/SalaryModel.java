package lk.ijse.project_wineverse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dto.SalaryDTO;
import lk.ijse.project_wineverse.view.tdm.SalaryTM;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {

    public static boolean save(SalaryDTO salary) throws SQLException {
        String sql = "INSERT INTO salary(emp_id,salary_id,salary_amount,OT,payment_method) " +
                "VALUES(?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, salary.getEmpid());
//            pstm.setString(2, salary.getSlryid());
//            pstm.setDouble(3, salary.getSlryamount());
//            pstm.setDouble(4, salary.getOt());
//            pstm.setString(5, salary.getPaymethod());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                salary.getEmpid(),
                salary.getSlryid(),
                salary.getSlryamount(),
                salary.getOt(),
                salary.getPaymethod()
        );
    }

    public static ObservableList<SalaryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

            ObservableList<SalaryTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new SalaryTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }
            return obList;
    }

    public static boolean update(SalaryDTO salary) throws SQLException {
        String sql = "UPDATE salary SET emp_id=?,salary_amount=?,OT=?,payment_method=? WHERE salary_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, salary.getEmpid());
//            pstm.setDouble(2, salary.getSlryamount());
//            pstm.setDouble(3, salary.getOt());
//            pstm.setString(4, salary.getPaymethod());
//            pstm.setString(5, salary.getSlryid());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                salary.getEmpid(),
                salary.getSlryamount(),
                salary.getOt(),
                salary.getPaymethod(),
                salary.getSlryid()
        );
    }

    public static boolean delete(String slryid) throws SQLException {
        String sql = "DELETE FROM salary WHERE salary_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, slryid);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,slryid);
    }
}
