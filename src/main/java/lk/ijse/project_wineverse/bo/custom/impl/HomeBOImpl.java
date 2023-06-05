package lk.ijse.project_wineverse.bo.custom.impl;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.util.Pair;
import lk.ijse.project_wineverse.bo.custom.HomeBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.EventImagesDAO;
import lk.ijse.project_wineverse.dao.custom.ItemDAO;
import lk.ijse.project_wineverse.dao.custom.OrdersDAO;
import lk.ijse.project_wineverse.dao.custom.QueryDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeBOImpl implements HomeBO {

    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrdersDAO ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    EventImagesDAO eventImagesDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EVENT_IMAGES);

    public ArrayList<PieChart.Data> getDataToPieChart() throws SQLException {
        return queryDAO.getDataToPieChart();
    }

    public ArrayList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {
        return itemDAO.getDataToBarChart();
    }

    public List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException {
        return ordersDAO.getDataToAreaChart(year);
    }

    public List<Pair<String, Image>> eventDataWithIds() throws SQLException {
        return eventImagesDAO.eventDataWithIds();
    }
}
