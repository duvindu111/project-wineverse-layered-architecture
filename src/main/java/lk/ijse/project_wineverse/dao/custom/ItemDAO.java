package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {

    public List<String> loadItemCodes() throws SQLException;
}
