package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.PlaceOrderBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.CustomerDAO;
import lk.ijse.project_wineverse.dao.custom.ItemDAO;
import lk.ijse.project_wineverse.dao.custom.OrdersDAO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.entity.Item;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public String getNextOrderId() throws SQLException {
        return ordersDAO.getNextOrderId();
    }

    public List<String> loadCustomerIds() throws SQLException {
        return customerDAO.loadCustomerIds();
    }

    public List<String> loadItemCodes() throws SQLException {
        return itemDAO.loadItemCodes();
    }

    public String getCustomerName(String cust_id) throws SQLException {
        return customerDAO.getCustName(cust_id);
    }

    public ItemDTO findByItemCode(String id) throws SQLException {
       Item item = itemDAO.findBy(id);
       return new ItemDTO(item.getItem_code(),item.getItem_name(),String.valueOf(item.getUnit_price()),item.getItem_category(),String.valueOf(item.getItem_qty()));
    }
}
