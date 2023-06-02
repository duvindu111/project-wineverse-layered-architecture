package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAll() throws SQLException;

    public boolean update(ItemDTO dto) throws SQLException;

    public ItemDTO findByItemCode(String id) throws SQLException;

    public boolean deleteItem(String id) throws SQLException;
}
