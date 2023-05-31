package lk.ijse.project_wineverse.model;

import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CashierSupplyLoadModel {

    public static String getNextSupplyLoadId() throws SQLException {
        String sql = "SELECT load_id FROM supply_load_details ORDER BY load_id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("LOAD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "LOAD-" + digit;
        }
        return "LOAD-001";
    }

    public static boolean placeLoad(String loadid, String suppid, String totalprice, List<PlaceSupplyLoadDTO> placeSupplyLoadList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(loadid,suppid,totalprice, LocalDate.now(), LocalTime.now(),placeSupplyLoadList);
            if(isSaved) {
                boolean isUpdated = ItemModel.addQty(placeSupplyLoadList);
                if(isUpdated) {
                                con.commit();
                                return true;
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean save(String loadid, String suppid, String totalprice, LocalDate now, LocalTime now1, List<PlaceSupplyLoadDTO> placeSupplyLoadList) throws SQLException {
        for(PlaceSupplyLoadDTO placeSupplyLoad : placeSupplyLoadList) {
            if(!savesupplyloaddetails(loadid,suppid,totalprice,now,now1,placeSupplyLoad)) {
                return false;
            }
        }
        return true;
    }

    private static boolean savesupplyloaddetails(String loadid, String suppid, String totalprice, LocalDate now, LocalTime now1, PlaceSupplyLoadDTO placeSupplyLoad) throws SQLException {
        String sql = "INSERT INTO supply_load_details(load_id,supp_id,item_code,supp_qty,load_date,load_time,price)" +
                "VALUES(?,?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                loadid,
                suppid,
                placeSupplyLoad.getItemcode(),
                placeSupplyLoad.getSuppqty(),
                now,
                now1,
                totalprice
        );
    }

}

