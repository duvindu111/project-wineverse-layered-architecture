package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.DeliveryBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.DeliveryDAO;
import lk.ijse.project_wineverse.dao.custom.EmployeeDAO;
import lk.ijse.project_wineverse.dao.custom.OrdersDAO;
import lk.ijse.project_wineverse.dto.DeliveryDTO;
import lk.ijse.project_wineverse.entity.Delivery;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {

    DeliveryDAO deliveryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);
    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    public boolean updateDelivery(DeliveryDTO dto) throws SQLException {
        return deliveryDAO.update(new Delivery(dto.getDelid(),dto.getDelsts(),dto.getLoc(),dto.getDeldate(),dto.getDuedate(),dto.getOrdid(),dto.getEmpid()));
    }

    public boolean deleteDelivery(String id) throws SQLException {
        return deliveryDAO.delete(id);
    }

    public boolean cancelDelivery(String ordid) throws SQLException {
        return ordersDAO.cancelDelivery(ordid);
    }

    public DeliveryDTO findByOrderID(String id) throws SQLException {
        Delivery del = deliveryDAO.findBy(id);
        return new DeliveryDTO(del.getDelivery_id(),del.getDelivery_status(),del.getLocation(),del.getDelivered_date(),del.getDue_date(),del.getOrder_id(),del.getEmp_id());
    }

    public DeliveryDTO findBydeliveryId(String delid) throws SQLException {
        Delivery del = deliveryDAO.findBydeliveryId(delid);
        return new DeliveryDTO(del.getDelivery_id(),del.getDelivery_status(),del.getLocation(),del.getDelivered_date(),del.getDue_date(),del.getOrder_id(),del.getEmp_id());
    }

    public ArrayList<DeliveryDTO> getByDueDate(String duedate) throws SQLException {
        ArrayList<Delivery> all = deliveryDAO.getByDueDate(duedate);
        ArrayList<DeliveryDTO> arrayList = new ArrayList<>();

        for (Delivery d : all) {
            arrayList.add(new DeliveryDTO(d.getDelivery_id(),d.getDelivery_status(),d.getLocation(),d.getDelivered_date(),d.getDue_date(),d.getOrder_id(),d.getEmp_id()));
        }
        return arrayList;
    }

    public ArrayList<DeliveryDTO> getAllDeliveries() throws SQLException {
        ArrayList<Delivery> all = deliveryDAO.getAll();
        ArrayList<DeliveryDTO> arrayList = new ArrayList<>();

        for (Delivery d : all) {
            arrayList.add(new DeliveryDTO(d.getDelivery_id(),d.getDelivery_status(),d.getLocation(),d.getDelivered_date(),d.getDue_date(),d.getOrder_id(),d.getEmp_id()));
        }
        return arrayList;
    }

    public List<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }

    public ArrayList<DeliveryDTO> getByDeliveryStatus(String delists) throws SQLException {
        ArrayList<Delivery> all = deliveryDAO.getByDeliveryStatus(delists);
        ArrayList<DeliveryDTO> arrayList = new ArrayList<>();

        for (Delivery d : all) {
            arrayList.add(new DeliveryDTO(d.getDelivery_id(),d.getDelivery_status(),d.getLocation(),d.getDelivered_date(),d.getDue_date(),d.getOrder_id(),d.getEmp_id()));
        }
        return arrayList;
    }

}
