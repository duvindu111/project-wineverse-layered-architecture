package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.dao.SuperDAO;
import lk.ijse.project_wineverse.entity.CashierCustomer;

import java.sql.SQLException;

public interface CashierCustomerDAO extends CrudDAO<CashierCustomer> {

    public int ordercountbycustid(String id) throws SQLException;

}
