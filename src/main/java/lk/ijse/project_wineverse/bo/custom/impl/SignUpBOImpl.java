package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.SignUpBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.UserDAO;
import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.entity.User;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {

    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public boolean save(SignUpDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUsername(),dto.getPassword(),dto.getJob_title(),dto.getEmail()));
    }

}
