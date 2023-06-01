package lk.ijse.project_wineverse.dao;

import lk.ijse.project_wineverse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.project_wineverse.dao.custom.impl.EventDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EVENT
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case EVENT:
                return (T) new EventDAOImpl();
            default:
                return null;
        }
    }
}
