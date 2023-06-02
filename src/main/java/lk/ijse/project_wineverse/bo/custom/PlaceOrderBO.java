package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.NewDeliveryDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    public String getNextOrderId() throws SQLException;

    public List<String> loadCustomerIds() throws SQLException;

    public List<String> loadItemCodes() throws SQLException;

    public String getCustomerName(String cust_id) throws SQLException;

    public ItemDTO findByItemCode(String id) throws SQLException;

    public boolean placeOrder(String orderid, String custid, Boolean delivery, String ordpay, List<PlaceOrderDTO> placeOrderList) throws SQLException, ClassNotFoundException;

    public void sendObject(NewDeliveryDTO newDelivery);
}
