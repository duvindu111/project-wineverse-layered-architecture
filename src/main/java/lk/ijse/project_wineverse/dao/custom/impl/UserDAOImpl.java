package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.UserDAO;
import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.entity.User;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {



    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user(username,password,user_job_title,email) " +
                "VALUES(?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getUsername(),
                entity.getPassword(),
                entity.getUser_job_title(),
                entity.getEmail()
        );
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public User findBy(String id) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public boolean updatePassword(String username, String newpassword) throws SQLException {
        String sql = "UPDATE user SET password =? WHERE username = ?";
        return CrudUtil.execute(sql,newpassword,username);
    }
}
