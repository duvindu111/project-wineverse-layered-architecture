package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.SupplyLoadBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.ItemDAO;
import lk.ijse.project_wineverse.dao.custom.SupplierDAO;
import lk.ijse.project_wineverse.dao.custom.SupplyLoadDetailsDAO;
import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;
import lk.ijse.project_wineverse.model.ItemModel;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SupplyLoadBOImpl implements SupplyLoadBO {

    SupplierDAO supplierDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    SupplyLoadDetailsDAO supplyLoadDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLYLOADDETAILS);

    public List<String> loadSupplierIds() throws SQLException {
        return supplierDAO.loadIds();
    }

    public List<String> loadItemCodes() throws SQLException {
        return itemDAO.loadItemCodes();
    }

    public String getNextSupplyLoadId() throws SQLException {
        return supplyLoadDetailsDAO.getNextSupplyLoadId();
    }

    public boolean placeLoad(String loadid, String suppid, String totalprice, List<PlaceSupplyLoadDTO> placeSupplyLoadList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            SupplyLoadDetails sld = new SupplyLoadDetails();
            sld.setLoad_id(loadid);
            sld.setSupp_id(suppid);
            sld.setPrice(Double.parseDouble(totalprice));
            sld.setDate(LocalDate.now());
            sld.setTime(LocalTime.now());

            List<SupplyLoadDetails> placeLoadEntityList = new ArrayList<>();

            for (PlaceSupplyLoadDTO dto : placeSupplyLoadList) {
                SupplyLoadDetails s = new SupplyLoadDetails();
                s.setItem_code(dto.getItemcode());
                s.setSupp_qty(dto.getSuppqty());
                placeLoadEntityList.add(s);
            }
            boolean isSaved = supplyLoadDetailsDAO.savesupplyloaddetails(sld,placeLoadEntityList);
            if(isSaved) {
                boolean isUpdated = itemDAO.addQty(placeLoadEntityList);
                if(isUpdated) {
                    con.commit();
                    return true;
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
}
