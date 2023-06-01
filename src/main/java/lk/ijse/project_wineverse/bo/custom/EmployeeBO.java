package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException, ParseException;

    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException;

    public EmployeeDTO findByEmployee(String id) throws SQLException;

    public boolean deleteEmployee(String id) throws SQLException;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException;
}
