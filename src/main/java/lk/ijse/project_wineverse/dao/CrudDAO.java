package lk.ijse.project_wineverse.dao;

import lk.ijse.project_wineverse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public boolean save(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public boolean delete(String id) throws SQLException;

    public T findBy(String id) throws SQLException;

}
