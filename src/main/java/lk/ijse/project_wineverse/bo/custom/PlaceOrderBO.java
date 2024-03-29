package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.NewDeliveryDTO;
import lk.ijse.project_wineverse.dto.OrderDetailDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    public String getNextOrderId() throws SQLException;

    public List<String> loadCustomerIds() throws SQLException;

    public List<String> loadItemCodes() throws SQLException;

    public String getCustomerName(String cust_id) throws SQLException;

    public ItemDTO findByItemCode(String id) throws SQLException;

    public boolean placeOrder(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

    public void sendObject(NewDeliveryDTO newDelivery);

    public List<String> loadEmployeeIds() throws SQLException;

    public String getEmailByCustID(String custId) throws SQLException;
}
