package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.ItemBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.ItemDAO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getName(),Double.valueOf(dto.getUnitprice()),dto.getCategory(),Integer.valueOf(dto.getQuantity())));
    }

    public ArrayList<ItemDTO> getAll() throws SQLException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> arrayList = new ArrayList<>();

        for (Item i : all) {
            arrayList.add(new ItemDTO(i.getItem_code(),i.getItem_name(),String.valueOf(i.getUnit_price()),i.getItem_category(),String.valueOf(i.getItem_qty())));
        }
        return arrayList;
    }

    public boolean update(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getCode(),dto.getName(),Double.valueOf(dto.getUnitprice()),dto.getCategory(),Integer.valueOf(dto.getQuantity())));
    }

    public ItemDTO findByItemCode(String id) throws SQLException {
        Item item = itemDAO.findBy(id);
        return new ItemDTO(item.getItem_code(),item.getItem_name(),String.valueOf(item.getUnit_price()),item.getItem_category(),String.valueOf(item.getItem_qty()));
    }

    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }
}
