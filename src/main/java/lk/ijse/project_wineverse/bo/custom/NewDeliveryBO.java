package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface NewDeliveryBO extends SuperBO {

    public String getNextOrderId() throws SQLException;

    public List<String> loadEmployeeIds() throws SQLException;
}
