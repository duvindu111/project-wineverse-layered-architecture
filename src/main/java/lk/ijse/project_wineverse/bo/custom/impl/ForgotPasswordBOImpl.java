package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.UserDAO;
import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.entity.User;

import java.sql.SQLException;

public class ForgotPasswordBOImpl {

    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public SignUpDTO findByUsername(String id) throws SQLException {
        User user = userDAO.findBy(id);
        return new SignUpDTO(user.getUsername(),user.getPassword(),user.getUser_job_title(),user.getEmail());
    }

    public boolean updatePassword(String username, String newpassword) throws SQLException {
       return userDAO.updatePassword(username,newpassword);
    }

}
