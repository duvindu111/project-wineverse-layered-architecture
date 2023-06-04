package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.SalaryDAO;
import lk.ijse.project_wineverse.dto.SalaryDTO;
import lk.ijse.project_wineverse.entity.Salary;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.SalaryTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO salary(emp_id,salary_id,salary_amount,OT,payment_method) " +
                "VALUES(?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getEmp_id(),
                entity.getSalary_id(),
                entity.getSalary_amount(),
                entity.getOT(),
                entity.getPayment_method()
        );
    }

    @Override
    public boolean update(Salary entity) throws SQLException {
        String sql = "UPDATE salary SET emp_id=?,salary_amount=?,OT=?,payment_method=? WHERE salary_id=?";

        return CrudUtil.execute(
                sql,
                entity.getEmp_id(),
                entity.getSalary_amount(),
                entity.getOT(),
                entity.getPayment_method(),
                entity.getSalary_id()
        );
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        ArrayList<Salary> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM salary WHERE salary_id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Salary findBy(String id) throws SQLException {
        return null;
    }
}
