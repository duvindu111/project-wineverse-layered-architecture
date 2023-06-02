package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.entity.User;

import java.sql.SQLException;

public interface ForgotPasswordBO {

    public SignUpDTO findByUsername(String id) throws SQLException;

    public boolean updatePassword(String username, String newpassword) throws SQLException;
}
