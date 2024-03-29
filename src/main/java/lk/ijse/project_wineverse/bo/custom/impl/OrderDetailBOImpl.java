package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.OrderDetailBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.OrdersDAO;
import lk.ijse.project_wineverse.dao.custom.QueryDAO;
import lk.ijse.project_wineverse.dto.CustomDTO;
import lk.ijse.project_wineverse.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);

    public ArrayList<CustomDTO> getAll() throws SQLException {
        ArrayList<Custom> all = queryDAO.getAll();
        ArrayList<CustomDTO> arrayList = new ArrayList<>();

        for (Custom c : all) {
            CustomDTO dto = new CustomDTO();
            dto.setOrder_id(c.getOrder_id());
            dto.setItem_code(c.getItem_code());
            dto.setItem_name(c.getItem_name());
            dto.setOrder_qty(c.getOrder_qty());
            arrayList.add(dto);
        }
        return arrayList;
    }

    public CustomDTO fillFields(String orderid) throws SQLException {
       Custom custom = queryDAO.fillFields(orderid);
       CustomDTO dto = new CustomDTO();
       dto.setCust_id(custom.getCust_id());
       dto.setCust_name(custom.getCust_name());
       dto.setDelivery(custom.getDelivery());
       dto.setOrder_date(custom.getOrder_date());
       dto.setOrder_time(custom.getOrder_time());
       dto.setOrder_payment(custom.getOrder_payment());

       return dto;
    }

    public int totalOrdersToday() throws SQLException {
        return ordersDAO.totalOrdersToday();
    }

    public int totalOrdersMonth() throws SQLException {
        return ordersDAO.totalOrdersMonth();
    }

    public ArrayList<CustomDTO> searchbyorderdate(String date) throws SQLException {
        ArrayList<Custom> all = queryDAO.searchbyorderdate(date);
        ArrayList<CustomDTO> arrayList = new ArrayList<>();

        for (Custom c : all) {
            CustomDTO dto =new CustomDTO();
            dto.setOrder_id(c.getOrder_id());
            dto.setItem_code(c.getItem_code());
            dto.setItem_name(c.getItem_name());
            dto.setOrder_qty(c.getOrder_qty());
            arrayList.add(dto);
        }
        return arrayList;
    }
}
