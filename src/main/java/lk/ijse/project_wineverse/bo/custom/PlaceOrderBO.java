package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    public String getNextOrderId() throws SQLException;

    public List<String> loadCustomerIds() throws SQLException;

    public List<String> loadItemCodes() throws SQLException;

    public String getCustomerName(String cust_id) throws SQLException;

    public ItemDTO findByItemCode(String id) throws SQLException;
}
