package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.SupplyLoadBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.ItemDAO;
import lk.ijse.project_wineverse.dao.custom.SupplierDAO;
import lk.ijse.project_wineverse.dao.custom.SupplyLoadDetailsDAO;
import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.dto.SupplyLoadDetailsDTO;
import lk.ijse.project_wineverse.entity.Item;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;

import java.sql.Connection;
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

    public boolean placeLoad(SupplyLoadDetailsDTO dto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            //SupplyLoadDetailsDTO supplyLoadDetailsDTO = new SupplyLoadDetailsDTO(dto.getLoad_id(),dto.getSupp_id(),String.valueOf(dto.getPrice()));

            boolean isSaved = savesupplyloaddetails(dto);
            if(isSaved) {
                boolean isUpdated = addQty(dto.getPlaceSupplyLoadDTOList());
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

    public boolean savesupplyloaddetails(SupplyLoadDetailsDTO dto) throws SQLException {
        for(PlaceSupplyLoadDTO psld : dto.getPlaceSupplyLoadDTOList()) {
            if(!supplyLoadDetailsDAO.savesupplyloaddetails(new SupplyLoadDetails(dto.getLoad_id(),dto.getSupp_id(),psld.getItemcode(), psld.getSuppqty(),LocalDate.now(),LocalTime.now(),dto.getPrice()))) {
                return false;
            }
        }
        return true;
    }

    public boolean addQty(List<PlaceSupplyLoadDTO> placeSupplyLoadDTOList) throws SQLException {
        for(PlaceSupplyLoadDTO dto : placeSupplyLoadDTOList) {
            SupplyLoadDetails sld = new SupplyLoadDetails(dto.getItemcode(),dto.getSuppqty());
            if(!itemDAO.addQty(sld)) {
                return false;
            }
        }
        return true;
    }

    public  String getSupplierName(String supp_id) throws SQLException {
        return supplierDAO.getSupplierName(supp_id);
    }

    public ItemDTO findBy(String id) throws SQLException {
        Item itm =  itemDAO.findBy(id);
        return new ItemDTO(itm.getItem_code(),itm.getItem_name(),String.valueOf(itm.getUnit_price()),itm.getItem_category(),String.valueOf(itm.getUnit_price()));
    }
}
