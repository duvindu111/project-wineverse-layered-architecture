package lk.ijse.project_wineverse.dao.custom;

import javafx.scene.chart.PieChart;
import lk.ijse.project_wineverse.dao.SuperDAO;
import lk.ijse.project_wineverse.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    public ArrayList<Custom> getAll() throws SQLException;

    public Custom fillFields(String orderid) throws SQLException;

    public ArrayList<Custom> searchbyorderdate(String date) throws SQLException;

    public ArrayList<PieChart.Data> getDataToPieChart() throws SQLException;
}
