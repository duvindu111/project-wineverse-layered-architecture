package lk.ijse.project_wineverse.dao;

import lk.ijse.project_wineverse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.project_wineverse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.project_wineverse.dao.custom.impl.EventDAOImpl;
import lk.ijse.project_wineverse.dao.custom.impl.EventImagesDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EVENT,EVENT_IMAGES,EMPLOYEE
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case EVENT:
                return (T) new EventDAOImpl();
            case EVENT_IMAGES:
                return (T) new EventImagesDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            default:
                return null;
        }
    }
}
