package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO extends SuperBO {

    public List<String> loadEmployeeIds() throws SQLException;

    public ArrayList<SalaryDTO> getAll() throws SQLException;

    public boolean update(SalaryDTO dto) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
