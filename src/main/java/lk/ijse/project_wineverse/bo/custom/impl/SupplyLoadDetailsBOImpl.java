package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.SupplyLoadDetailsBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.SupplyLoadDetailsDAO;
import lk.ijse.project_wineverse.dto.SupplyLoadDetailsDTO;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class SupplyLoadDetailsBOImpl implements SupplyLoadDetailsBO {

    SupplyLoadDetailsDAO supplyLoadDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLYLOADDETAILS);

    public ArrayList<SupplyLoadDetailsDTO> getAll() throws SQLException {
        ArrayList<SupplyLoadDetails> all = supplyLoadDetailsDAO.getAll();
        ArrayList<SupplyLoadDetailsDTO> arrayList = new ArrayList<>();

        for (SupplyLoadDetails s : all) {
            arrayList.add(new SupplyLoadDetailsDTO(s.getLoad_id(),s.getSupp_id(),s.getItem_code(),s.getSupp_qty(),s.getDate(), Time.valueOf(s.getTime()),s.getPrice()));
        }
        return arrayList;
    }

    public ArrayList<SupplyLoadDetailsDTO> searchbyloadid(String loadid) throws SQLException {
        ArrayList<SupplyLoadDetails> all = supplyLoadDetailsDAO.searchbyloadid(loadid);
        ArrayList<SupplyLoadDetailsDTO> arrayList = new ArrayList<>();

        for (SupplyLoadDetails s : all) {
            arrayList.add(new SupplyLoadDetailsDTO(s.getLoad_id(),s.getSupp_id(),s.getItem_code(),s.getSupp_qty(),s.getDate(),Time.valueOf(s.getTime()),s.getPrice()));
        }
        return arrayList;
    }

    public ArrayList<SupplyLoadDetailsDTO> searchbyloaddate(String date) throws SQLException {
        ArrayList<SupplyLoadDetails> all = supplyLoadDetailsDAO.searchbyloaddate(date);
        ArrayList<SupplyLoadDetailsDTO> arrayList = new ArrayList<>();

        for (SupplyLoadDetails s : all) {
            arrayList.add(new SupplyLoadDetailsDTO(s.getLoad_id(),s.getSupp_id(),s.getItem_code(),s.getSupp_qty(),s.getDate(),Time.valueOf(s.getTime()),s.getPrice()));
        }
        return arrayList;
    }

    public ArrayList<SupplyLoadDetailsDTO> searchbysuppid(String suppid) throws SQLException {
       ArrayList<SupplyLoadDetails> all = supplyLoadDetailsDAO.searchbysuppid(suppid);
        ArrayList<SupplyLoadDetailsDTO> arrayList = new ArrayList<>();

        for (SupplyLoadDetails s : all) {
            arrayList.add(new SupplyLoadDetailsDTO(s.getLoad_id(),s.getSupp_id(),s.getItem_code(),s.getSupp_qty(),s.getDate(),Time.valueOf(s.getTime()),s.getPrice()));
        }
        return arrayList;

    }
}
