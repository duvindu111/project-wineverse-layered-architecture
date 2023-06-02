package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface SupplyLoadDetailsDAO extends CrudDAO<SupplyLoadDetails> {

    public ArrayList<SupplyLoadDetails> searchbyloadid(String loadid) throws SQLException;

    public ArrayList<SupplyLoadDetails> searchbyloaddate(String date) throws SQLException;

    public ArrayList<SupplyLoadDetails> searchbysuppid(String suppid) throws SQLException;

    public String getNextSupplyLoadId() throws SQLException;

    public boolean savesupplyloaddetails(SupplyLoadDetails sld, List<SupplyLoadDetails> placeSupplyLoadList) throws SQLException ;
}
