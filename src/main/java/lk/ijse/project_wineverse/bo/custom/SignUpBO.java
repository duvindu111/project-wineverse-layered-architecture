package lk.ijse.project_wineverse.bo.custom;

import lk.ijse.project_wineverse.bo.SuperBO;
import lk.ijse.project_wineverse.dto.SignUpDTO;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {

    public boolean save(SignUpDTO dto) throws SQLException, ClassNotFoundException;
}
