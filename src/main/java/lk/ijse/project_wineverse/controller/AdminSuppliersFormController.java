package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.project_wineverse.dto.SupplierDTO;
import lk.ijse.project_wineverse.view.tdm.SupplierTM;
import lk.ijse.project_wineverse.model.SupplierModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.TextFieldBorderController;
import lk.ijse.project_wineverse.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AdminSuppliersFormController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colcontact;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private ImageView logoutbtn1;

    @FXML
    private Label logoutlabel1;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtcontact;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private Label lblinvalidcontact;

    @FXML
    private Label lblinvalidemail;

    @FXML
    private Label invalidsupplieridformat;

    @FXML
    private TableView<SupplierTM> tblSupplier;

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

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();
        String email = txtemail.getText();

        if(id.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            AlertController.errormessage("Supplier details not added\nPlease make sure to fill out all the required fields before you try to add supplier details again");
        }else{
            boolean result = AlertController.okconfirmmessage("Are you sure you want to update this suppliers' details?");
            if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                if (ValidateField.contactCheck(contact)) {
                    if (ValidateField.emailCheck(email)) {
                        if(result==true) {

                            SupplierDTO supplier = new SupplierDTO(id, name, address, contact, email);

                            try {
                                boolean isUpdated = SupplierModel.update(supplier);
                                if (isUpdated) {
                                    AlertController.confirmmessage("Supplier Details Updated");
                                    txtid.setText("");
                                    txtname.setText("");
                                    txtaddress.setText("");
                                    txtcontact.setText("");
                                    txtemail.setText("");

                                    getAll();
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                                AlertController.errormessage("something went wrong!");
                            }
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

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        System.out.println(id +"out");
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();
        String email = txtemail.getText();

        if(id.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            AlertController.errormessage("Supplier details not added\nPlease make sure to fill out all the required fields before you try to add supplier details again");
        }else{
            if(ValidateField.supplierIdCheck(id)) {
                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                    if (ValidateField.contactCheck(contact)) {
                        if (ValidateField.emailCheck(email) || email.isEmpty()) {

                            SupplierDTO supplier = new SupplierDTO(id, name, address, contact, email);

                            try {
                                boolean isSaved = SupplierModel.save(supplier);
                                if (isSaved) {
                                    AlertController.confirmmessage("Supplier Added Successfully");
                                    txtid.setText("");
                                    txtname.setText("");
                                    txtaddress.setText("");
                                    txtcontact.setText("");
                                    txtemail.setText("");

                                    getAll();
                                }
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
            }else{
                invalidsupplieridformat.setVisible(true);
                txtid.setStyle("-fx-text-fill: red");
            }
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void getAll(){
        ObservableList<SupplierTM> obList = null;
        try {
            obList = SupplierModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblSupplier.setItems(obList);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtemail.setText("");

        try {
            SupplierDTO supplier = SupplierModel.findById(id);
            if(supplier!=null) {
                txtid.setText(supplier.getId());
                txtname.setText(supplier.getName());
                txtaddress.setText(supplier.getAddress());
                txtcontact.setText(supplier.getContact());
                txtemail.setText(supplier.getEmail());
                txtid.setDisable(true);
                txtSearch.setText("");
            }else{
                AlertController.errormessage("Employee ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) {
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());
    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<SupplierTM> obList = SupplierModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>(){
                @Override
                public boolean test(SupplierTM supplierTM) {
                    return String.valueOf(supplierTM.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblSupplier.setItems(filteredData);
        } else {
            tblSupplier.setItems(obList);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this supplier?");
        if(result==true) {

            try {
                boolean isDeleted = SupplierModel.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Supplier Deleted Successfully");
                    txtid.setText("");
                    txtname.setText("");
                    txtaddress.setText("");
                    txtcontact.setText("");
                    txtemail.setText("");
                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtemail.setText("");

        setCellValueFactory();
        getAll();

        TextFieldBorderController.txtfieldbordercolor(txtid);
        TextFieldBorderController.txtfieldbordercolor(txtname);
        TextFieldBorderController.txtfieldbordercolor(txtaddress);
        TextFieldBorderController.txtfieldbordercolor(txtcontact);
        TextFieldBorderController.txtfieldbordercolor(txtemail);

        lblinvalidemail.setVisible(false);
        lblinvalidcontact.setVisible(false);

        invalidsupplieridformat.setVisible(false);
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblSupplier.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SupplierTM, ?>> columns = tblSupplier.getColumns();

        txtid.setText(columns.get(0).getCellData(row).toString());
        txtname.setText(columns.get(1).getCellData(row).toString());
        txtaddress.setText(columns.get(3).getCellData(row).toString());
        txtcontact.setText(columns.get(2).getCellData(row).toString());
        txtemail.setText(columns.get(4).getCellData(row).toString());
        txtid.setDisable(true);
        txtSearch.setText("");
    }

    public void txtemailOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidemail.setVisible(false);
    }

    public void txtcontactOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidcontact.setVisible(false);
    }

    public void clearbtnOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtemail.setText("");
        txtid.setDisable(false);
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearch.setText("");
        getAll();
    }

    public void clearlblOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtemail.setText("");
        txtid.setDisable(false);
    }

    public void txtidOnMouseClicked(MouseEvent mouseEvent) {
        invalidsupplieridformat.setVisible(false);
        txtid.setStyle("-fx-text-fill: black");
    }

    public void seeSupplyLoadOnMousePressed(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/supplydetailsform.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }
}

