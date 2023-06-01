package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.EmployeeBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.EmployeeDAO;
import lk.ijse.project_wineverse.dto.EmployeeDTO;
import lk.ijse.project_wineverse.entity.Employee;
import lk.ijse.project_wineverse.util.CrudUtil;
import org.exolab.castor.types.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException, ParseException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(), dto.getDob(),dto.getAddress(),dto.getContact(),dto.getEmail(),dto.getJob(),dto.getNic()));
    }

    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList = new ArrayList<>();

        for (Employee e : all) {
            arrayList.add(new EmployeeDTO(e.getEmp_id(),e.getEmp_name(),e.getEmp_nic(),e.getEmp_dob(),e.getEmp_job_title(),e.getEmp_contact(),e.getEmp_address(),e.getEmp_email()));
        }
        return arrayList;
    }

    public EmployeeDTO findByEmployee(String id) throws SQLException {
        Employee e = employeeDAO.findBy(id);
        return new EmployeeDTO(e.getEmp_id(),e.getEmp_name(),e.getEmp_nic(),e.getEmp_dob(),e.getEmp_job_title(),e.getEmp_contact(),e.getEmp_address(),e.getEmp_email());
    }

    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException {
        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getDob(),dto.getAddress(),dto.getContact(),dto.getEmail(),dto.getJob(),dto.getNic()));
    }
}
