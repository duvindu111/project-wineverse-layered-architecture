package lk.ijse.project_wineverse.dao;

import lk.ijse.project_wineverse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EVENT,EVENT_IMAGES,EMPLOYEE,SALARY,SUPPLIER,DELIVERY,ORDERS,ITEM,ORDER_DETAIL,USER,QUERY,SUPPLYLOADDETAILS
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
            case SALARY:
                return (T) new SalaryDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case DELIVERY:
                return (T) new DeliveryDAOImpl();
            case ORDERS:
                return (T) new OrdersDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case SUPPLYLOADDETAILS:
                return (T) new SupplyLoadDetailsDAOImpl();
            default:
                return null;
        }
    }
}
