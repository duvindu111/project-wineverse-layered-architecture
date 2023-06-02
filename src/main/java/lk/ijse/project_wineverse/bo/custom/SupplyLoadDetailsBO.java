package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.SupplyLoadDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplyLoadDetailsBO extends SuperBO {

    public ArrayList<SupplyLoadDetailsDTO> getAll() throws SQLException;

    public ArrayList<SupplyLoadDetailsDTO> searchbyloadid(String loadid) throws SQLException;

    public ArrayList<SupplyLoadDetailsDTO> searchbyloaddate(String date) throws SQLException;

    public ArrayList<SupplyLoadDetailsDTO> searchbysuppid(String suppid) throws SQLException;

}
