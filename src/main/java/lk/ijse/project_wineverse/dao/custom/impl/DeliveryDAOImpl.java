package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.custom.DeliveryDAO;
import lk.ijse.project_wineverse.entity.Delivery;
import lk.ijse.project_wineverse.dao.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public boolean save(Delivery entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO delivery(delivery_id,location,due_date,order_id,emp_id)" +
                "VALUES (?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getDelivery_id(),
                entity.getLocation(),
                entity.getDue_date(),
                entity.getOrder_id(),
                entity.getEmp_id()
        );
    }

    @Override
    public boolean update(Delivery entity) throws SQLException {
        String sql = "UPDATE delivery SET delivery_status=?,location=?,delivered_date=?,due_date=?,emp_id=? WHERE delivery_id=?";

        return CrudUtil.execute(
                sql,
                entity.getDelivery_status(),
                entity.getLocation(),
                entity.getDelivered_date(),
                entity.getDue_date(),
                entity.getEmp_id(),
                entity.getDelivery_id()
        );
    }

    @Override
    public ArrayList<Delivery> getAll() throws SQLException {
        String sql = "SELECT * FROM delivery";

        ArrayList<Delivery> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            all.add(new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return all;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM delivery WHERE delivery_id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Delivery findBy(String id) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE order_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);


        if(resultSet.next()){
            System.out.println(resultSet.getDate(4).toLocalDate());
            System.out.println(resultSet.getDate(5).toLocalDate());
            return (new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return null;
    }

    public Delivery findBydeliveryId(String delid) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE delivery_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,delid);
        if(resultSet.next()){
            return (new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return null;
    }

    public ArrayList<Delivery> getByDueDate(String duedate) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE due_date=?";

        ArrayList<Delivery> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,duedate);
        while (resultSet.next()) {
            all.add(new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return all;
    }

    public ArrayList<Delivery> getByDeliveryStatus(String delists) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE delivery_status=?";

        ArrayList<Delivery> all = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,delists);
        while (resultSet.next()) {
            all.add(new Delivery(
                    resultSet.getString(6),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(3),
                    resultSet.getString(7)
            ));
        }
        return all;
    }
}
