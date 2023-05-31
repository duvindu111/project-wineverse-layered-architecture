package lk.ijse.project_wineverse.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.project_wineverse.dto.OrderDetailDTO;
import lk.ijse.project_wineverse.dto.tm.OrderDetailTM;
import lk.ijse.project_wineverse.model.OrderDetailModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.ValidateField;

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

    @FXML
    void tblOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtSearchKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<OrderDetailTM> obList = OrderDetailModel.getAll();

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
            OrderDetailDTO orderDetail =  OrderDetailModel.fillFields(orderid);
            if(orderDetail!=null) {
                lblordid.setText(orderid);
                lblcustid.setText(orderDetail.getCustid());
                lblcustname.setText(orderDetail.getCustname());
                lbldelivery.setText(String.valueOf(orderDetail.getDelivery()));
                lbldate.setText(orderDetail.getDate());
                lbltime.setText(orderDetail.getTime());
                lblprice.setText(String.valueOf(orderDetail.getPrice()));

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
        int daily_count = OrderDetailModel.totalOrdersToday();
        lblcounttoday.setText(String.valueOf(daily_count));

        monthly_count = OrderDetailModel.totalOrdersMonth();
    }

    private void setCellValueFactory() {
        colordid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colitemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void getAll(){
        ObservableList<OrderDetailTM> obList = null;
        try {
            obList = OrderDetailModel.getAll();
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

        OrderDetailDTO orderDetail =  OrderDetailModel.fillFields(orderid);

        lblcustid.setText(orderDetail.getCustid());
        lblcustname.setText(orderDetail.getCustname());
        lbldelivery.setText(String.valueOf(orderDetail.getDelivery()));
        lbldate.setText(orderDetail.getDate());
        lbltime.setText(orderDetail.getTime());
        lblprice.setText(String.valueOf(orderDetail.getPrice()));
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
                ObservableList<OrderDetailTM> obList = OrderDetailModel.searchbyorderdate(date);
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
