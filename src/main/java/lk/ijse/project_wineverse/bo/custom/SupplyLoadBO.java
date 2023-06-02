package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplyLoadBO extends SuperBO {

    public List<String> loadSupplierIds() throws SQLException;

    public List<String> loadItemCodes() throws SQLException;

    public String getNextSupplyLoadId() throws SQLException;

    public boolean placeLoad(String loadid, String suppid, String totalprice, List<PlaceSupplyLoadDTO> placeSupplyLoadList) throws SQLException;
}
