package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.CustomerBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.CustomerDAO;
import lk.ijse.project_wineverse.dto.CustomerDTO;
import lk.ijse.project_wineverse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save( new Customer(dto.getId(), dto.getName(),dto.getEmail(),dto.getContact()));
    }

    public int orderCountByCustID(String id) throws SQLException {
        return customerDAO.ordercountbycustid(id);
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();

        for (Customer c : all) {
            arrayList.add(new CustomerDTO(c.getCust_id(),c.getCust_name(),c.getCust_email(),c.getCust_contact()));
        }
        return arrayList;
    }

    public CustomerDTO findByCustomerId(String id) throws SQLException {
        Customer customer = customerDAO.findBy(id);
        return new CustomerDTO(customer.getCust_id(),customer.getCust_name(),customer.getCust_email(),customer.getCust_contact());
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(),dto.getEmail(),dto.getContact()));
    }

    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }
}
