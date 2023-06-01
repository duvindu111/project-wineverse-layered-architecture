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
import lk.ijse.project_wineverse.bo.custom.CashierCustomerBO;
import lk.ijse.project_wineverse.bo.custom.impl.CashierCustomerBOImpl;
import lk.ijse.project_wineverse.dto.CustomerDTO;
import lk.ijse.project_wineverse.view.tdm.CustomerTM;
import lk.ijse.project_wineverse.model.CustomerModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.TextFieldBorderController;
import lk.ijse.project_wineverse.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CashierCustomerController implements Initializable {

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colcontact;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private ImageView logoutbtn;

    @FXML
    private Label logoutlabel;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtcontact;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private Label lblinvalidemail;

    @FXML
    private Label lblinvalidcontact;

    @FXML
    private Label lbltotorders;

    @FXML
    private Label lbltotalordersbycustomer;

    @FXML
    private Label lblinvalidcustid;

    CashierCustomerBO cashierCustomerBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER_BO);

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void logoutbtnMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) throws SQLException {
        TablePosition pos = tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<CustomerTM, ?>> columns = tblCustomer.getColumns();

        txtid.setText(columns.get(0).getCellData(row).toString());
        txtid.setDisable(true);
        txtname.setText(columns.get(1).getCellData(row).toString());
        txtemail.setText(columns.get(2).getCellData(row).toString());
        txtcontact.setText(columns.get(3).getCellData(row).toString());

        ///////////
        String id = txtid.getText();
        int ordercount = cashierCustomerBO.orderCountByCustID(id);
        lbltotalordersbycustomer.setVisible(true);
        lbltotorders.setVisible(true);
        lbltotorders.setText(String.valueOf(ordercount));

        txtSearch.setText("");
    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = txtid.getText();
        String name = txtname.getText();
        String email = txtemail.getText();
        String contact = txtcontact.getText();

        if (id.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            AlertController.errormessage("Customer details not saved.\nPlease make sure to fill out all the required fields.");
        } else {
            if (ValidateField.custIdCheck(id)) {
                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                    if (ValidateField.contactCheck(contact)) {
                        if (ValidateField.emailCheck(email) || email.isEmpty()) {
                            try {
                                boolean isSaved = cashierCustomerBO.saveCustomer(new CustomerDTO(id, name, email, contact));
                                if (isSaved) {
                                    AlertController.confirmmessage("Customer Added Successfully");
                                    txtid.setText("");
                                    txtname.setText("");
                                    txtemail.setText("");
                                    txtcontact.setText("");
                                    getAll();
                                }
                            } catch (SQLIntegrityConstraintViolationException e) {
                                System.out.println(e);
                                new Alert(Alert.AlertType.ERROR, "Duplicate Customer ID").show();
                            } catch (SQLException e) {
                                System.out.println(e);
                                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                            }
                        } else {
                            lblinvalidemail.setVisible(true);
                        }
                    } else {
                        lblinvalidcontact.setVisible(true);
                    }
                } else {
                    lblinvalidemail.setVisible(true);
                    lblinvalidcontact.setVisible(true);
                }
            } else {
                lblinvalidcustid.setVisible(true);
                txtid.setStyle("-fx-text-fill: red");
            }
        }
    }

    private void getAll() {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        try {
            ArrayList<CustomerDTO> all = cashierCustomerBO.getAllCustomers();

            for (CustomerDTO c : all) {
                obList.add(new CustomerTM(c.getId(),c.getName(),c.getEmail(),c.getContact()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblCustomer.setItems(obList);
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        txtid.setText("");
        txtname.setText("");
        txtemail.setText("");
        txtcontact.setText("");
        lbltotorders.setText("");

        try {
            CustomerDTO customer = cashierCustomerBO.findByCustomerId(id);
            int ordercount = cashierCustomerBO.orderCountByCustID(id);
            if (customer != null) {
                txtid.setText(customer.getId());
                txtid.setDisable(true);
                txtname.setText(customer.getName());
                txtemail.setText(customer.getEmail());
                txtcontact.setText(customer.getContact());
                lbltotalordersbycustomer.setVisible(true);
                lbltotorders.setVisible(true);
                lbltotorders.setText(String.valueOf(ordercount));

                txtSearch.setText("");
            } else {
                AlertController.errormessage("Customer ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) {
    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue = txtSearch.getText().trim();
      //  ObservableList<CustomerTM> obList = CustomerModel.getAll();

        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        ArrayList<CustomerDTO> all = cashierCustomerBO.getAllCustomers();

        for (CustomerDTO c : all) {
            obList.add(new CustomerTM(c.getId(),c.getName(),c.getEmail(),c.getContact()));
        }


        if (!searchValue.isEmpty()) {
            ObservableList<CustomerTM> filteredData = obList.filtered(new Predicate<CustomerTM>() {
                @Override
                public boolean test(CustomerTM eventtm) {
                    return eventtm.getId().toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblCustomer.setItems(filteredData);
        } else {
            tblCustomer.setItems(obList);
        }
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this customers' details?");
        if (result == true) {

            String id = txtid.getText();
            String name = txtname.getText();
            String email = txtemail.getText();
            String contact = txtcontact.getText();

            if (id.isEmpty() || name.isEmpty() || contact.isEmpty()) {
                AlertController.errormessage("Customer details not updated.\nPlease make sure to fill out all the required fields.");
            } else {
                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                    if (ValidateField.contactCheck(contact)) {
                        if (ValidateField.emailCheck(email)) {
                            try {
                                CustomerDTO customer = new CustomerDTO(id, name, email, contact);
                                boolean isUpdated = cashierCustomerBO.updateCustomer(customer);
                                if (isUpdated) {
                                    AlertController.confirmmessage("Customer Details Updated");
                                    txtid.setText("");
                                    txtname.setText("");
                                    txtemail.setText("");
                                    txtcontact.setText("");

                                    getAll();
                                }
                            } catch (SQLIntegrityConstraintViolationException e) {
                                System.out.println(e);
                                AlertController.errormessage(e.getMessage());
                            } catch (Exception e) {
                                System.out.println(e);
                                AlertController.errormessage("something went wrong!");
                            }
                        } else {
                            lblinvalidemail.setVisible(true);
                        }
                    } else {
                        lblinvalidcontact.setVisible(true);
                    }
                } else {
                    lblinvalidemail.setVisible(true);
                    lblinvalidcontact.setVisible(true);
                }
            }
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
        String id = txtid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this customer?");
        if (result == true) {
            try {
                boolean isDeleted = cashierCustomerBO.deleteCustomer(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Customer Deleted Successfully");
                    txtid.setText("");
                    txtname.setText("");
                    txtemail.setText("");
                    txtcontact.setText("");

                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();

        TextFieldBorderController.txtfieldbordercolor(txtid);
        TextFieldBorderController.txtfieldbordercolor(txtname);
        TextFieldBorderController.txtfieldbordercolor(txtemail);
        TextFieldBorderController.txtfieldbordercolor(txtcontact);

        lblinvalidemail.setVisible(false);
        lbltotalordersbycustomer.setVisible(false);
        lblinvalidcustid.setVisible(false);
        lblinvalidcontact.setVisible(false);
    }

    public void txtemailOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidemail.setVisible(false);
        lbltotalordersbycustomer.setVisible(false);
        lbltotorders.setVisible(false);
    }

    public void txtcontactOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidcontact.setVisible(false);
        lbltotalordersbycustomer.setVisible(false);
        lbltotorders.setVisible(false);
    }

    public void txtidOnMouseClicked(MouseEvent mouseEvent) {
        lbltotalordersbycustomer.setVisible(false);
        lbltotorders.setVisible(false);
        lblinvalidcustid.setVisible(false);
        txtid.setStyle("-fx-text-fill: black");
    }

    public void txtnameOnMouseClicked(MouseEvent mouseEvent) {
        lbltotalordersbycustomer.setVisible(false);
        lbltotorders.setVisible(false);
    }

    public void clearbtnOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText("");
        txtname.setText("");
        txtemail.setText("");
        txtcontact.setText("");
        txtid.setDisable(false);
    }

    public void clearlblOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText("");
        txtname.setText("");
        txtemail.setText("");
        txtcontact.setText("");
        txtid.setDisable(false);
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearch.setText("");
        getAll();
    }
}
