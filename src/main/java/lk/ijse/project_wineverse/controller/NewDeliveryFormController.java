package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_wineverse.dto.NewDeliveryDTO;
import lk.ijse.project_wineverse.model.CashierOrderModel;
import lk.ijse.project_wineverse.model.EmployeeModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.ValidateField;

public class NewDeliveryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private ComboBox<String> cmbempid;

    @FXML
    private DatePicker duedate;

    @FXML
    private Label lblorderid;

    @FXML
    private TextField txtdelid;

    @FXML
    private TextField txtlocation;

    @FXML
    private TextField txtduedate;

    @FXML
    private AnchorPane newdeliveryAncPane;

    @FXML
    private Label lblwrongdateformat;

    @FXML
    private Label lblwrongdelid;


    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtcontactOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAddNew != null : "fx:id=\"btnAddNew\" was not injected: check your FXML file 'newdeliveryform.fxml'.";
        assert cmbempid != null : "fx:id=\"cmbempid\" was not injected: check your FXML file 'newdeliveryform.fxml'.";
        assert duedate != null : "fx:id=\"duedate\" was not injected: check your FXML file 'newdeliveryform.fxml'.";
        assert lblorderid != null : "fx:id=\"lblorderid\" was not injected: check your FXML file 'newdeliveryform.fxml'.";
        assert txtdelid != null : "fx:id=\"txtdelid\" was not injected: check your FXML file 'newdeliveryform.fxml'.";
        assert txtlocation != null : "fx:id=\"txtlocation\" was not injected: check your FXML file 'newdeliveryform.fxml'.";

        generateNextOrderId();
        loadEmployeeIds();
        setdeliveryID();

        lblwrongdateformat.setVisible(false);
        lblwrongdelid.setVisible(false);

    }

    private void setdeliveryID(){

            String[] strings = lblorderid.getText().split("ORD-");
            int id = Integer.parseInt(strings[1]);
            String digit=String.format("%03d", id);
            txtdelid.setText("DL-"+digit);
    }

    private void generateNextOrderId() {
        try {
            String id = CashierOrderModel.getNextOrderId();
            lblorderid.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private void loadEmployeeIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = EmployeeModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbempid.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    NewDeliveryDTO newDelivery;
    public void btnAddNewOnAction(ActionEvent actionEvent) {
        if(txtlocation.getText().isEmpty() || cmbempid.getSelectionModel().getSelectedIndex()==-1) {
            AlertController.errormessage("Please make sure you fill out all the required fields before trying to add the delivery");
        }else{
            boolean result = AlertController.okconfirmmessage("Are you sure you want to add this details as a new delivery?\nMake sure you provide correct information or you can later update info from the delivery page");
            if (result) {
                String orderid = lblorderid.getText();
                String delid = txtdelid.getText();
                String location = txtlocation.getText();
                String empid = cmbempid.getValue();
                String duedate = txtduedate.getText();

                if (ValidateField.deliveryIdCheck(delid)) {
                    if (ValidateField.dateCheck(duedate) || txtduedate.getText().isEmpty()) {
                        newDelivery = new NewDeliveryDTO(orderid, delid, location, empid, duedate);

                        CashierOrderModel.sendObject(newDelivery);

                        newdeliveryAncPane.getScene().getWindow().hide();

                    } else {
                        txtduedate.setStyle("-fx-text-fill: red");
                        AlertController.errormessage("Delivery Not Saved.\nReason: Wrong Date Format.\nCorrect Format: (yyyy-mm-dd)\nEx: 0000-00-00");
                        lblwrongdateformat.setVisible(true);
                    }
                } else {
                    txtdelid.setStyle("-fx-text-fill: red");
                    AlertController.errormessage("Delivery Not Saved.\nReason: Wrong Delivery ID Format.\nCorrect Format: (DL-***)\nEx: DL-000");
                    lblwrongdelid.setVisible(true);
                }
            }
        }
    }


    public void txtduedateOnMouseClicked(MouseEvent mouseEvent) {
        lblwrongdateformat.setVisible(false);
        txtduedate.setStyle("-fx-text-fill: black");
    }

    public void txtdelidOnMouseClicked(MouseEvent mouseEvent) {
        lblwrongdelid.setVisible(false);
        txtdelid.setStyle("-fx-text-fill: black");
    }
}
