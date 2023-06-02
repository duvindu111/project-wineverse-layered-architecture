package lk.ijse.project_wineverse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_wineverse.dao.custom.SupplyLoadDetailsDAO;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.entity.SupplyLoadDetails;
import lk.ijse.project_wineverse.util.CrudUtil;
import lk.ijse.project_wineverse.view.tdm.SupplyLoadDetailTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SupplyLoadDetailsDAOImpl implements SupplyLoadDetailsDAO {
    @Override
    public boolean save(SupplyLoadDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplyLoadDetails entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<SupplyLoadDetails> getAll() throws SQLException {
        String sql = "SELECT * FROM supply_load_details ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public SupplyLoadDetails findBy(String id) throws SQLException {
        return null;
    }

    public ArrayList<SupplyLoadDetails> searchbyloadid(String loadid) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE load_id=? ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,loadid);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    public ArrayList<SupplyLoadDetails> searchbyloaddate(String date) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE load_date=? ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,date);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    public ArrayList<SupplyLoadDetails> searchbysuppid(String suppid) throws SQLException {
        String sql = "SELECT * FROM supply_load_details WHERE supp_id=? ";

        ArrayList<SupplyLoadDetails> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,suppid);
        while (resultSet.next()) {
            all.add(new SupplyLoadDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    resultSet.getDouble(7)
            ));
        }
        return all;
    }

    public String getNextSupplyLoadId() throws SQLException {
        String sql = "SELECT load_id FROM supply_load_details ORDER BY load_id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean savesupplyloaddetails(SupplyLoadDetails sld, List<SupplyLoadDetails> placeSupplyLoadList) throws SQLException {
        for(SupplyLoadDetails placeSupplyLoad : placeSupplyLoadList) {
            if(!savesupplyloaddetails(sld.getLoad_id(),sld.getSupp_id(),String.valueOf(sld.getPrice()),sld.getDate(),sld.getTime(),placeSupplyLoad)) {
                return false;
            }
        }
        return true;
    }

    private boolean savesupplyloaddetails(String loadid, String suppid, String totalprice, LocalDate now, LocalTime now1, SupplyLoadDetails placeSupplyLoad) throws SQLException {
        String sql = "INSERT INTO supply_load_details(load_id,supp_id,item_code,supp_qty,load_date,load_time,price)" +
                "VALUES(?,?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                loadid,
                suppid,
                placeSupplyLoad.getItem_code(),
                placeSupplyLoad.getSupp_qty(),
                now,
                now1,
                totalprice
        );
    }
}
