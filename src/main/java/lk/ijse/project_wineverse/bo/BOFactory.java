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
        CUSTOMER_BO,EVENTIMAGE_BO,EMPLOYEE_BO,SALARY_BO,SUPPLIER_BO,DELIVERY_BO,EVENT_BO,PLACEORDER_BO,FORGOTPASSWORD_BO,ITEM_BO,ORDERDETAil_BO,LOGIN_BO,SIGNUP_BO,SUPPLYLOADDETAILS_BO,SUPPLYLOAD_BO,HOME_BO
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
            case FORGOTPASSWORD_BO:
                return (T) new ForgotPasswordBOImpl();
            case ITEM_BO:
                return (T) new ItemBOImpl();
            case ORDERDETAil_BO:
                return (T) new OrderDetailBOImpl();
            case LOGIN_BO:
                return (T) new LoginBOImpl();
            case SIGNUP_BO:
                return (T) new SignUpBOImpl();
            case SUPPLYLOADDETAILS_BO:
                return (T) new SupplyLoadDetailsBOImpl();
            case SUPPLYLOAD_BO:
                return (T) new SupplyLoadBOImpl();
            case HOME_BO:
                return (T) new HomeBOImpl();
            default:
                return null;
        }
    }
}
