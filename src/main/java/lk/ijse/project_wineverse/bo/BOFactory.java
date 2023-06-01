package lk.ijse.project_wineverse.bo;

import lk.ijse.project_wineverse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO,EVENTIMAGE_BO,EMPLOYEE_BO,SALARY_BO,SUPPLIER_BO,DELIVERY_BO,EVENT_BO,PLACEORDER_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case EVENTIMAGE_BO:
                return (T) new AddEventImageBOImpl();
            case EMPLOYEE_BO:
                return (T) new EmployeeBOImpl();
            case SALARY_BO:
                return (T) new SalaryBOImpl();
            case SUPPLIER_BO:
                return (T) new SupplierBOImpl();
            case DELIVERY_BO:
                return (T) new DeliveryBOImpl();
            case EVENT_BO:
                return (T) new EventBOImpl();
            case PLACEORDER_BO:
                return (T) new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
