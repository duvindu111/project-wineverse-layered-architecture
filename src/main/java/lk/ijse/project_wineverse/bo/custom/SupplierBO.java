package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.SupplierDTO;
import lk.ijse.project_wineverse.entity.Supplier;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public boolean updateSupplier(SupplierDTO dto) throws SQLException;

    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    public ArrayList<SupplierDTO> getAll() throws SQLException;

    public SupplierDTO findBy(String id) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
