package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.CustomerDTO;
import lk.ijse.project_wineverse.entity.CashierCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public int orderCountByCustID(String id) throws SQLException;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException;

    public CustomerDTO findByCustomerId(String id) throws SQLException;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;
}
