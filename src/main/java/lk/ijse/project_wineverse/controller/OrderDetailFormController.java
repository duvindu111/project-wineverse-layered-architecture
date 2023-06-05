package lk.ijse.project_wineverse.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.OrderDetailBO;
import lk.ijse.project_wineverse.dto.CustomDTO;
import lk.ijse.project_wineverse.view.tdm.OrderDetailTM;
import lk.ijse.project_wineverse.controller.util.AlertController;
import lk.ijse.project_wineverse.controller.util.ValidateField;

public class OrderDetailFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colitemcode;

    @FXML
    private TableColumn<?, ?> colitemname;

    @FXML
    private TableColumn<?, ?> colordid;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private Label lblcustid;

    @FXML
    private Label lblcustname;

    @FXML
    private Label lbldate;

    @FXML
    private Label lbldelivery;

    @FXML
    private Label lblordid;

    @FXML
    private Label lblprice;

    @FXML
    private Label lbltime;

    @FXML
    private TableView<OrderDetailTM> tblOrderDetail;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label orderdetailslbl;

    @FXML
    private JFXButton btnShowAll;

    @FXML
    private TextField txtSearchdate;

    @FXML
    private Label lblcountmonth;

    @FXML
    private Label lblcounttoday;

    @FXML
    private Label lblordersthismonth;

    OrderDetailBO orderDetailBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDERDETAil_BO);

    @FXML
    void tblOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtSearchKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();
     //   ObservableList<OrderDetailTM> obList = OrderDetailModel.getAll();
        ObservableList<OrderDetailTM> obList = FXCollections.observableArrayList();

        ArrayList<CustomDTO> all = orderDetailBO.getAll();

        for (CustomDTO dto : all) {
            obList.add(new OrderDetailTM(dto.getOrder_id(),dto.getItem_code(),dto.getItem_name(),String.valueOf(dto.getOrder_qty())));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<OrderDetailTM> filteredData = obList.filtered(new Predicate<OrderDetailTM>(){
                @Override
                public boolean test(OrderDetailTM orderDetailTM) {
                    return String.valueOf(orderDetailTM.getOrderid()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblOrderDetail.setItems(filteredData);
        } else {
            tblOrderDetail.setItems(obList);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String orderid = txtSearch.getText();

        try {
         //   OrderDetailDTO orderDetail =  OrderDetailModel.fillFields(orderid);
            CustomDTO dto =  orderDetailBO.fillFields(orderid);
            if(dto!=null) {
                lblordid.setText(orderid);
                lblcustid.setText(dto.getCust_id());
                lblcustname.setText(dto.getCust_name());
                lbldelivery.setText(String.valueOf(dto.getDelivery()));
                lbldate.setText(String.valueOf(dto.getOrder_date()));
                lbltime.setText(String.valueOf(dto.getOrder_time()));
                lblprice.setText(String.valueOf(dto.getOrder_payment()));

                orderdetailslbl.requestFocus();

                ///////////////////
//                KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_TYPED, "", "", KeyCode.UNDEFINED, false, false, false, false);
//                txtSearch.fireEvent(keyEvent);
                ///////////////

               // txtSearch.setText("");
            }else{
                AlertController.errormessage("Order ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    @FXML
    void initialize() throws SQLException {
        assert colitemcode != null : "fx:id=\"colitemcode\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert colitemname != null : "fx:id=\"colitemname\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert colordid != null : "fx:id=\"colordid\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert colqty != null : "fx:id=\"colqty\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lblcustid != null : "fx:id=\"lblcustid\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lblcustname != null : "fx:id=\"lblcustname\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lbldate != null : "fx:id=\"lbldate\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lbldelivery != null : "fx:id=\"lblddelivery\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lblordid != null : "fx:id=\"lblordid\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lblprice != null : "fx:id=\"lblprice\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert lbltime != null : "fx:id=\"lbltime\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert tblOrderDetail != null : "fx:id=\"tblOrderDetail\" was not injected: check your FXML file 'orderdetailform.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'orderdetailform.fxml'.";

        setCellValueFactory();
        getAll();

        dailyAndMonthlyOrderCount();

        /////////MonthName
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        String monthName = currentDate.format(formatter);
        lblordersthismonth.setText("Total Orders On "+monthName+": "+monthly_count);
        /////////////
    }

    int monthly_count;
    private void dailyAndMonthlyOrderCount() throws SQLException {
     //   int daily_count = OrderDetailModel.totalOrdersToday();
        int daily_count = orderDetailBO.totalOrdersToday();
        lblcounttoday.setText(String.valueOf(daily_count));

     //   monthly_count = OrderDetailModel.totalOrdersMonth();
        monthly_count = orderDetailBO.totalOrdersMonth();
    }

    private void setCellValueFactory() {
        colordid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colitemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void getAll(){
        ObservableList<OrderDetailTM> obList = FXCollections.observableArrayList();
        try {
          //  obList = OrderDetailModel.getAll();
            ArrayList<CustomDTO> all = orderDetailBO.getAll();

            for (CustomDTO dto : all) {
                obList.add(new OrderDetailTM(dto.getOrder_id(),dto.getItem_code(),dto.getItem_name(),String.valueOf(dto.getOrder_qty())));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        tblOrderDetail.setItems(obList);
    }

    public void tblOrderDetailOnMouseClicked(MouseEvent mouseEvent) throws SQLException {
        TablePosition pos = tblOrderDetail.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<OrderDetailTM, ?>> columns = tblOrderDetail.getColumns();

        String orderid=columns.get(0).getCellData(row).toString();
        lblordid.setText(orderid);

      //  OrderDetailDTO orderDetail =  OrderDetailModel.fillFields(orderid);
        CustomDTO dto =  orderDetailBO.fillFields(orderid);

        lblcustid.setText(dto.getCust_id());
        lblcustname.setText(dto.getCust_name());
        lbldelivery.setText(String.valueOf(dto.getDelivery()));
        lbldate.setText(String.valueOf(dto.getOrder_date()));
        lbltime.setText(String.valueOf(dto.getOrder_time()));
        lblprice.setText(String.valueOf(dto.getOrder_payment()));
    }

    public void txtSearchOnClicked(MouseEvent mouseEvent) {
        lblordid.setText("");
        lblcustid.setText("");
        lblcustname.setText("");
        lbldelivery.setText("");
        lbldate.setText("");
        lbltime.setText("");
        lblprice.setText("");
        txtSearch.setText("");
        getAll();
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        getAll();
        lblordid.setText("");
        lblcustid.setText("");
        lblcustname.setText("");
        lbldelivery.setText("");
        lbldate.setText("");
        lbltime.setText("");
        lblprice.setText("");
        txtSearch.setText("");
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
    }

    public void txtSearchdateOnAction(ActionEvent actionEvent) throws SQLException {

            String date = txtSearchdate.getText();
            if(ValidateField.dateCheck(date)) {
                ObservableList<OrderDetailTM> obList = FXCollections.observableArrayList();
                ArrayList<CustomDTO> all = orderDetailBO.searchbyorderdate(date);

                for (CustomDTO dto : all) {
                    obList.add(new OrderDetailTM(dto.getOrder_id(),dto.getItem_code(),dto.getItem_name(),String.valueOf(dto.getOrder_qty())));
                }
                tblOrderDetail.setItems(obList);
            }else{
                AlertController.errormessage("Wrong Date Format\nRequired format : (yyyy-mm-dd)");
            }
    }

    public void txtSearchdateKeyTyped(KeyEvent keyEvent) {
    }

    public void txtSearchdateOnClicked(MouseEvent mouseEvent) {

    }
}
