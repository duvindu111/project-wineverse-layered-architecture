package lk.ijse.project_wineverse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import lk.ijse.project_wineverse.bo.custom.CashierCustomerBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.CashierCustomerDAO;
import lk.ijse.project_wineverse.dao.custom.impl.CashierCustomerDAOImpl;
import lk.ijse.project_wineverse.dto.CustomerDTO;
import lk.ijse.project_wineverse.entity.CashierCustomer;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierCustomerBOImpl implements CashierCustomerBO {
    CashierCustomerDAO cashierCustomerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return cashierCustomerDAO.save( new CashierCustomer(dto.getId(), dto.getName(),dto.getEmail(),dto.getContact()));
    }

    public int orderCountByCustID(String id) throws SQLException {
        return cashierCustomerDAO.ordercountbycustid(id);
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ArrayList<CashierCustomer> all = cashierCustomerDAO.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();

        for (CashierCustomer c : all) {
            arrayList.add(new CustomerDTO(c.getCust_id(),c.getCust_name(),c.getCust_email(),c.getCust_contact()));
        }
        return arrayList;
    }

    public CustomerDTO findByCustomerId(String id) throws SQLException {
        CashierCustomer customer = cashierCustomerDAO.findBy(id);
        return new CustomerDTO(customer.getCust_id(),customer.getCust_name(),customer.getCust_email(),customer.getCust_contact());
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return cashierCustomerDAO.update(new CashierCustomer(dto.getId(), dto.getName(),dto.getEmail(),dto.getContact()));
    }

    public boolean deleteCustomer(String id) throws SQLException {
        return cashierCustomerDAO.delete(id);
    }
}
