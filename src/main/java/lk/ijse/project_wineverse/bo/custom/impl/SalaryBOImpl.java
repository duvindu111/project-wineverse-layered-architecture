package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.SalaryBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.EmployeeDAO;
import lk.ijse.project_wineverse.dao.custom.SalaryDAO;
import lk.ijse.project_wineverse.dto.SalaryDTO;
import lk.ijse.project_wineverse.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    SalaryDAO salaryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    public List<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }

    public ArrayList<SalaryDTO> getAll() throws SQLException {
       ArrayList<Salary> all = salaryDAO.getAll();
       ArrayList<SalaryDTO> arrayList = new ArrayList<>();

        for (Salary s : all) {
            arrayList.add(new SalaryDTO(s.getEmp_id(),s.getSalary_id(),s.getSalary_amount(),s.getOT(),s.getPayment_method()));
        }
          return arrayList;
    }

    public boolean update(SalaryDTO dto) throws SQLException {
        return salaryDAO.update(new Salary(dto.getEmpid(),dto.getSlryid(),dto.getSlryamount(),dto.getOt(),dto.getPaymethod()));
    }

    public boolean delete(String id) throws SQLException {
        return salaryDAO.delete(id);
    }

    public boolean save(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(dto.getEmpid(),dto.getSlryid(),dto.getSlryamount(),dto.getOt(),dto.getPaymethod()));
    }
}
