package lk.ijse.project_wineverse.model;

import lk.ijse.project_wineverse.dto.NewDeliveryDTO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.SQLException;

public class NewDeliveryModel {

    public static boolean save(NewDeliveryDTO gotnewdelivery) throws SQLException {
        String sql = "INSERT INTO delivery(delivery_id,location,due_date,order_id,emp_id)" +
                "VALUES (?,?,?,?,?)";

       return CrudUtil.execute(
                sql,
                gotnewdelivery.getDelid(),
                gotnewdelivery.getLocation(),
                gotnewdelivery.getDuedate(),
                gotnewdelivery.getOrderid(),
                gotnewdelivery.getEmpid()
       );
    }
}
