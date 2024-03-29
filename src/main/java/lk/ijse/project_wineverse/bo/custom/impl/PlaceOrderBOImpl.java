package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.PlaceOrderBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.*;
import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.NewDeliveryDTO;
import lk.ijse.project_wineverse.dto.OrderDetailDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.entity.Delivery;
import lk.ijse.project_wineverse.entity.Item;
import lk.ijse.project_wineverse.entity.OrderDetail;
import lk.ijse.project_wineverse.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    DeliveryDAO deliveryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

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
        return new ItemDTO(item.getItem_code(), item.getItem_name(), String.valueOf(item.getUnit_price()), item.getItem_category(), String.valueOf(item.getItem_qty()));
    }

    static NewDeliveryDTO gotnewdelivery;
    public void sendObject(NewDeliveryDTO newDelivery) {
        gotnewdelivery = newDelivery;
    }

    public boolean placeOrder(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            //   boolean isSaved = save(orderid, custid, delivery, LocalDate.now(), LocalTime.now(), ordpay);
            boolean isSaved = ordersDAO.save(new Orders(dto.getOrderId(), dto.getCustid(), dto.getDelivery(), LocalDate.now(), LocalTime.now(), dto.getPrice()));
            if (isSaved) {
                //   boolean isUpdated = ItemModel.updateQty(placeOrderList);
                boolean isUpdated = updateQty(dto.getPlaceOrderDTOList());
                if (isUpdated) {
                    //  boolean isOrdered = AdminOrderDetailModel.save(orderid, placeOrderList);
                    boolean isOrdered = saveOrderDetails(dto);
                    if (isOrdered) {
                        if (dto.getDelivery()) {
                            Delivery entity = new Delivery();
                            entity.setOrder_id(gotnewdelivery.getOrderid());
                            entity.setDelivery_id(gotnewdelivery.getDelid());
                            entity.setLocation(gotnewdelivery.getLocation());
                            entity.setEmp_id(gotnewdelivery.getEmpid());
                            entity.setDue_date(String.valueOf(gotnewdelivery.getDuedate()));

                            boolean isDelivered = deliveryDAO.save(entity);
                            if (isDelivered) {
                                con.commit();
                                return true;
                            }
                        } else {
                            con.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException {
        for(PlaceOrderDTO placeOrder : dto.getPlaceOrderDTOList()) {
            OrderDetail orderDetail = new OrderDetail(placeOrder.getOrdereditemcode(),placeOrder.getOrdereditemqty());
            if(!orderDetailDAO.saveOrderDetails(dto.getOrderId(), orderDetail)) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(List<PlaceOrderDTO> placeOrderList) throws SQLException {
        for(PlaceOrderDTO placeorder : placeOrderList) {
            OrderDetail orderDetail = new OrderDetail(placeorder.getOrdereditemcode(),placeorder.getOrdereditemqty());
            if(!itemDAO.updateQty(orderDetail)) {
                return false;
            }
        }
        return true;
    }

    public List<String> loadEmployeeIds() throws SQLException {
        return employeeDAO.loadIds();
    }

    public String getEmailByCustID(String custId) throws SQLException {
        return customerDAO.getEmailByCustID(custId);
    }
}
