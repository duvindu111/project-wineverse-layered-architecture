package lk.ijse.project_wineverse.bo;

import lk.ijse.project_wineverse.bo.custom.impl.AddEventImageBOImpl;
import lk.ijse.project_wineverse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.project_wineverse.bo.custom.impl.EmployeeBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO,EVENTIMAGE_BO,EMPLOYEE_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case EVENTIMAGE_BO:
                return (T) new AddEventImageBOImpl();
            case EMPLOYEE_BO:
                return (T) new EmployeeBOImpl();
            default:
                return null;
        }
    }
}
