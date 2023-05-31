package lk.ijse.project_wineverse.model;

import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpModel {

    public static boolean save(SignUpDTO signup) throws SQLException {
        String sql = "INSERT INTO user(username,password,user_job_title,email) " +
                "VALUES(?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, signup.getUsername());
//            pstm.setString(2, signup.getPassword());
//            pstm.setString(3, signup.getJob_title());
//            pstm.setString(4, signup.getEmail());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(
                sql,
                signup.getUsername(),
                signup.getPassword(),
                signup.getJob_title(),
                signup.getEmail()
        );
    }

    public static SignUpDTO findbyusername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";

            ResultSet resultSet = CrudUtil.execute(sql,username);
            if(resultSet.next()) {
                return new SignUpDTO(
                       resultSet.getString(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4)
                );
            }
            return null;
    }

    public static boolean update(String username, String newpassword) throws SQLException {
        String sql = "UPDATE user SET password =? WHERE username = ?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, newpassword);
//            pstm.setString(2,username);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,newpassword,username);
    }
}
