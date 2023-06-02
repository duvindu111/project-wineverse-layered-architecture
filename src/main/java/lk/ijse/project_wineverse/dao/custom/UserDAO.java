package lk.ijse.project_wineverse.dao.custom;

import lk.ijse.project_wineverse.dao.CrudDAO;
import lk.ijse.project_wineverse.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {

    public boolean updatePassword(String username, String newpassword) throws SQLException;
}
