package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.DeliveryBO;
import lk.ijse.project_wineverse.dto.DeliveryDTO;
import lk.ijse.project_wineverse.view.tdm.DeliveryTM;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierDeliveryController implements Initializable {

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbdeliverystatus;

    @FXML
    private ComboBox<String> cmbempid;

    @FXML
    private TableColumn<?, ?> coldeldate;

    @FXML
    private TableColumn<?, ?> coldeliid;

    @FXML
    private TableColumn<?, ?> coldelstatus;

    @FXML
    private TableColumn<?, ?> colduedate;

    @FXML
    private TableColumn<?, ?> colempid;

    @FXML
    private TableColumn<?, ?> colorderid;

    @FXML
    private TableColumn<?, ?> collocation;

    @FXML
    private Label lbldelid;

    @FXML
    private Label lblorderid;

    @FXML
    private ImageView logoutbtn;

    @FXML
    private Label logoutlabel;

    @FXML
    private TableView<?> tblEvent;

    @FXML
    private TextField txtSearchByDate;

    @FXML
    private TextField txtSearchByDelivery;

    @FXML
    private TextField txtSearchByDeliveryId;

    @FXML
    private TextField txtSearchByOrder;

    @FXML
    private TextField txtdelidate;

    @FXML
    private TextField txtduedate;

    @FXML
    private TextField txtlocation;

    @FXML
    private TableView<DeliveryTM> tblDelivery;

    @FXML
    private TextField txtSearchByDeliverySts;

    @FXML
    private ComboBox cmbSearchByDeliverySts;

    @FXML
    private Label lblinvalidwrongdeldate;

    @FXML
    private Label lblinvalidwrongduedate;

    DeliveryBO deliveryBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DELIVERY_BO);

    public void logoutbtnMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
        String delid = lbldelid.getText();
        String delsts = cmbdeliverystatus.getValue();
        String loc = txtlocation.getText();
        String deldate = txtdelidate.getText();
        String duedate = txtduedate.getText();
        String orderid = lblorderid.getText();
        String empid = cmbempid.getValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localdelDate = LocalDate.parse(deldate, formatter);
        LocalDate localdueDate = LocalDate.parse(deldate, formatter);

        if(ValidateField.dateCheck(deldate) || deldate.equals("Pending")) {
            if(ValidateField.dateCheck(duedate) || duedate.isEmpty()) {
                DeliveryDTO delivery = new DeliveryDTO(delid, delsts, loc, localdelDate, localdueDate, orderid, empid);
                try {
                //    boolean isUpdated = DeliveryModel.update(delivery);
                    boolean isUpdated = deliveryBO.updateDelivery(delivery);
                    if (isUpdated) {
                        AlertController.confirmmessage("Delivery Details Updated");
                        lblorderid.setText("");
                        lbldelid.setText("");
                        cmbdeliverystatus.setValue(null);
                        cmbempid.setValue(null);
                        txtlocation.setText("");
                        txtdelidate.setText("");
                        txtduedate.setText("");
                        getAll();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    AlertController.errormessage("something went wrong!");
                }
            }else{
                lblinvalidwrongduedate.setVisible(true);
            }
        }else{
            lblinvalidwrongdeldate.setVisible(true);
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
        String delid = lbldelid.getText();
        String ordid = lblorderid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to cancel this delivery?");
        if(result==true) {
            try {
            //    boolean isDeleted = DeliveryModel.delete(delid);
                boolean isDeleted = deliveryBO.deleteDelivery(delid);
                if (isDeleted) {
                    //boolean isUpdated = CashierOrderModel.updatedelivery(ordid);
                    boolean isUpdated = deliveryBO.cancelDelivery(ordid);
                    AlertController.confirmmessage("Delivery Cancelled Successfully");
                    lblorderid.setText("");
                    lbldelid.setText("");
                    cmbdeliverystatus.setValue(null);
                    cmbempid.setValue(null);
                    txtlocation.setText("");
                    txtdelidate.setText("");
                    txtduedate.setText("");
                    getAll();
                }
            } catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void txtSearchByOrderOnAction(ActionEvent actionEvent) {
        String id = txtSearchByOrder.getText();
        try {
           // DeliveryDTO delivery = DeliveryModel.findById(id);
            DeliveryDTO delivery = deliveryBO.findByOrderID(id);
            if(delivery !=null) {
                cmbdeliverystatus.setDisable(false);
                txtlocation.setDisable(false);
                txtdelidate.setDisable(false);
                txtduedate.setDisable(false);
                cmbempid.setDisable(false);

                lbldelid.setText(delivery.getDelid());
                cmbdeliverystatus.setValue(delivery.getDelsts());
                txtlocation.setText(delivery.getLoc());
                txtdelidate.setText(String.valueOf(delivery.getDeldate()));
                txtduedate.setText(String.valueOf(delivery.getDuedate()));
                lblorderid.setText(delivery.getOrdid());
                cmbempid.setValue(delivery.getEmpid());

                txtSearchByOrder.setText("");
            }else{
                AlertController.errormessage("Order ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void txtSearchByDeliveryIdOnAction(ActionEvent actionEvent) {
        String delid = txtSearchByDeliveryId.getText();
        try {
           // DeliveryDTO delivery = DeliveryModel.findBydeliveryId(delid);
            DeliveryDTO delivery = deliveryBO.findBydeliveryId(delid);
            if(delivery !=null) {
                cmbdeliverystatus.setDisable(false);
                txtlocation.setDisable(false);
                txtdelidate.setDisable(false);
                txtduedate.setDisable(false);
                cmbempid.setDisable(false);

                lbldelid.setText(delivery.getDelid());
                cmbdeliverystatus.setValue(delivery.getDelsts());
                txtlocation.setText(delivery.getLoc());
                txtdelidate.setText(String.valueOf(delivery.getDeldate()));
                txtduedate.setText(String.valueOf(delivery.getDuedate()));
                lblorderid.setText(delivery.getOrdid());
                cmbempid.setValue(delivery.getEmpid());

                txtSearchByOrder.setText("");
            }else{
                AlertController.errormessage("Delivery ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void txtSearchByDeliveryOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchByDateOnAction(ActionEvent actionEvent) throws SQLException {
        String duedate = txtSearchByDate.getText();
       // ObservableList<DeliveryTM> obList = DeliveryModel.getByDueDate(duedate);

        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();
        ArrayList<DeliveryDTO> all = deliveryBO.getByDueDate(duedate);

        for (DeliveryDTO dto : all) {
            obList.add(new DeliveryTM(dto.getOrdid(),dto.getDelid(),dto.getDelsts(),String.valueOf(dto.getDuedate()),String.valueOf(dto.getDeldate()),dto.getLoc(),dto.getEmpid()));
        }

        tblDelivery.setItems(obList);
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) {
    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) {
    }

    public void txtSearchByOrderKeyTyped(KeyEvent keyEvent) {
    }

    public void txtSearchByDeliveryIdKeyTyped(KeyEvent keyEvent) {
    }

    public void txtSearchByDeliveryStsKeyTyped(KeyEvent keyEvent) {
    }

    public void txtSearchByDateKeyTyped(KeyEvent keyEvent) {
    }

    private void setCellValueFactory() {
        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        coldeliid.setCellValueFactory(new PropertyValueFactory<>("delid"));
        coldelstatus.setCellValueFactory(new PropertyValueFactory<>("delstatus"));
        colduedate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        coldeldate.setCellValueFactory(new PropertyValueFactory<>("deldate"));
        collocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colempid.setCellValueFactory(new PropertyValueFactory<>("empid"));
    }

    private void getAll(){
        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();
        try {
            //obList = DeliveryModel.getAll();
            ArrayList<DeliveryDTO> all = deliveryBO.getAllDeliveries();

            for (DeliveryDTO dto : all) {
                obList.add(new DeliveryTM(dto.getOrdid(),dto.getDelid(),dto.getDelsts(),String.valueOf(dto.getDuedate()),String.valueOf(dto.getDeldate()),dto.getLoc(),dto.getEmpid()));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblDelivery.setItems(obList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbdeliverystatus.setDisable(true);
        txtlocation.setDisable(true);
        txtdelidate.setDisable(true);
        txtduedate.setDisable(true);
        cmbempid.setDisable(true);

        setCellValueFactory();
        getAll();
        loadEmployeeIds();

        cmbdeliverystatus.getItems().addAll("Completed","Not Yet Completed");
        cmbSearchByDeliverySts.getItems().addAll("Completed","Not Yet Completed","Pending");

        lblinvalidwrongdeldate.setVisible(false);
        lblinvalidwrongduedate.setVisible(false);
    }

    public void tblDeliveryOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblDelivery.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<DeliveryTM, ?>> columns = tblDelivery.getColumns();

        lblorderid.setText(columns.get(0).getCellData(row).toString());
        lbldelid.setText(columns.get(1).getCellData(row).toString());
        cmbdeliverystatus.setValue(columns.get(2).getCellData(row).toString());
        txtduedate.setText(columns.get(3).getCellData(row).toString());
        txtdelidate.setText(columns.get(4).getCellData(row).toString());
        txtlocation.setText(columns.get(5).getCellData(row).toString());
        cmbempid.setValue(columns.get(6).getCellData(row).toString());

        cmbdeliverystatus.setDisable(false);
        txtlocation.setDisable(false);
        txtdelidate.setDisable(false);
        txtduedate.setDisable(false);
        cmbempid.setDisable(false);
    }

    private void loadEmployeeIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
          //  List<String> ids = EmployeeModel.loadIds();

            List<String> ids = deliveryBO.loadEmployeeIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbempid.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void cmbSearchByDeliverySts(ActionEvent actionEvent) throws SQLException {
        String delists = String.valueOf(cmbSearchByDeliverySts.getValue());
       // ObservableList<DeliveryTM> obList = DeliveryModel.getByDeliveryStatus(delists);
        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();

        ArrayList<DeliveryDTO> all = deliveryBO.getByDeliveryStatus(delists);
        for (DeliveryDTO dto : all) {
            obList.add(new DeliveryTM(dto.getOrdid(),dto.getDelid(),dto.getDelsts(),String.valueOf(dto.getDuedate()),String.valueOf(dto.getDeldate()),dto.getLoc(),dto.getEmpid()));
        }
        tblDelivery.setItems(obList);

    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearchByOrder.setText("");
        cmbSearchByDeliverySts.setValue(null);
        txtSearchByDate.setText("");
        txtSearchByDeliveryId.setText("");
        getAll();
    }

    public void txtdelidateOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidwrongdeldate.setVisible(false);
    }


    public void txtduedateOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidwrongduedate.setVisible(false);
    }
}
