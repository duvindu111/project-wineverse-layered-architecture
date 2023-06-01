package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

    public int ordercountbycustid(String id) throws SQLException;

    public List<String> loadCustomerIds() throws SQLException;

    public String getCustName(String cust_id) throws SQLException;

}
