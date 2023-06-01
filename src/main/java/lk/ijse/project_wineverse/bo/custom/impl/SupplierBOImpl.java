package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.SupplierBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.SupplierDAO;
import lk.ijse.project_wineverse.dto.SupplierDTO;
import lk.ijse.project_wineverse.entity.Supplier;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public boolean updateSupplier(SupplierDTO dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getId(),dto.getName(),dto.getEmail(),dto.getContact(),dto.getAddress()));
    }

    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(),dto.getName(),dto.getEmail(),dto.getContact(),dto.getAddress()));
    }

    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDTO> arrayList = new ArrayList<>();

        for (Supplier s : all) {
            arrayList.add(new SupplierDTO(s.getSupp_id(),s.getSupp_name(),s.getSupp_address(),s.getSupp_contact(),s.getSupp_email()));
        }
        return arrayList;
    }

    @Override
    public SupplierDTO findBy(String id) throws SQLException {
        Supplier supplier= supplierDAO.findBy(id);
        return new SupplierDTO(supplier.getSupp_id(),supplier.getSupp_name(),supplier.getSupp_address(),supplier.getSupp_contact(),supplier.getSupp_email());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return supplierDAO.delete(id);
    }
}
