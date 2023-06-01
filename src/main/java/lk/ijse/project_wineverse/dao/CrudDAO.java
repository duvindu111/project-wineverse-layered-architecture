package lk.ijse.project_wineverse.dao;

import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dto.CustomerDTO;
import lk.ijse.project_wineverse.entity.CashierCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public boolean save(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(CashierCustomer customer) throws SQLException;

    public ArrayList<CashierCustomer> getAll() throws SQLException;

    public boolean delete(String id) throws SQLException;

    public T findBy(String id) throws SQLException;
}
