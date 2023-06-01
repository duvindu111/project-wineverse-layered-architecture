package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.EmployeeDAO;
import lk.ijse.project_wineverse.dto.EmployeeDTO;
import lk.ijse.project_wineverse.entity.Customer;
import lk.ijse.project_wineverse.entity.Employee;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.EmployeeTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee(emp_id,emp_name,emp_dob,emp_address,emp_contact,emp_email,emp_job_title,emp_nic) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                employee.getEmp_id(),
                employee.getEmp_name(),
                employee.getEmp_dob(),
                employee.getEmp_address(),
                employee.getEmp_contact(),
                employee.getEmp_email(),
                employee.getEmp_job_title(),
                employee.getEmp_nic());
    }

    @Override
    public boolean update(Employee entity) throws SQLException {
        String sql = "UPDATE Employee SET emp_name=?,emp_dob=?,emp_address=?,emp_contact=?,emp_email=?,emp_job_title=?,emp_nic=? WHERE emp_id=?";

        return CrudUtil.execute(
                sql,
                entity.getEmp_name(),
                entity.getEmp_dob(),
                entity.getEmp_address(),
                entity.getEmp_contact(),
                entity.getEmp_email(),
                entity.getEmp_job_title(),
                entity.getEmp_nic(),
                entity.getEmp_id()
        );
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        ArrayList<Employee> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE emp_id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Employee findBy(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE emp_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return null;
    }

    public List<String> loadIds() throws SQLException {
        String sql = "SELECT emp_id FROM employee";
        ResultSet resultSet =CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
