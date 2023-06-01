package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.DeliveryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DeliveryBO extends SuperBO {

    public boolean updateDelivery(DeliveryDTO dto) throws SQLException;

    public boolean deleteDelivery(String id) throws SQLException;

    public boolean cancelDelivery(String ordid) throws SQLException;

    public DeliveryDTO findByOrderID(String id) throws SQLException;

    public DeliveryDTO findBydeliveryId(String delid) throws SQLException;

    public ArrayList<DeliveryDTO> getByDueDate(String duedate) throws SQLException;

    public ArrayList<DeliveryDTO> getAllDeliveries() throws SQLException;

    public List<String> loadEmployeeIds() throws SQLException;

    public ArrayList<DeliveryDTO> getByDeliveryStatus(String delists) throws SQLException;
}

