package lk.ijse.project_wineverse.dao.custom.impl;

import lk.ijse.project_wineverse.dao.custom.CustomerDAO;
import lk.ijse.project_wineverse.entity.CashierCustomer;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(CashierCustomer customer) throws SQLException, ClassNotFoundException{
        return CrudUtil.execute("INSERT INTO customer(cust_id,cust_name,cust_email,cust_contact) " +
                "VALUES(?,?,?,?)", customer.getCust_id(), customer.getCust_name(), customer.getCust_email(), customer.getCust_contact());
    }

    public int ordercountbycustid(String id) throws SQLException {
        String sql = "SELECT COUNT(order_id) FROM orders WHERE cust_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        int count;
        if(resultSet.next()) {
            return count = resultSet.getInt(1);
        }
        return 0;
    }

    public ArrayList<CashierCustomer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        ArrayList<CashierCustomer> arrayList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            arrayList.add(new CashierCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return arrayList;
    }

    public CashierCustomer findBy(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE cust_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new CashierCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return null;
    }

    public boolean update(CashierCustomer customer) throws SQLException {
        String sql = "UPDATE customer SET cust_name=?,cust_email=?,cust_contact=? WHERE cust_id=?";

        return CrudUtil.execute(
                sql,
                customer.getCust_name(),
                customer.getCust_email(),
                customer.getCust_contact(),
                customer.getCust_id());
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE cust_id=?";
        return CrudUtil.execute(
                sql,
                id);
    }
}
