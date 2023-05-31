package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_wineverse.dto.EmployeeDTO;
import lk.ijse.project_wineverse.dto.tm.EmployeeTM;
import lk.ijse.project_wineverse.model.EmployeeModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.TextFieldBorderController;
import lk.ijse.project_wineverse.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AdminEmployeeController {

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
    private TableColumn<?, ?> empcoladdress;

    @FXML
    private TableColumn<?, ?> empcolcontact;

    @FXML
    private TableColumn<?, ?> empcoldob;

    @FXML
    private TableColumn<?, ?> empcolemail;

    @FXML
    private TableColumn<?, ?> empcolid;

    @FXML
    private TableColumn<?, ?> empcoljob;

    @FXML
    private TableColumn<?, ?> empcolname;

    @FXML
    private TableColumn<?, ?> empcolnic;

    @FXML
    private ImageView logoutbtn1;

    @FXML
    private Label logoutlabel1;

    @FXML
    private TextField txtempaddress;

    @FXML
    private TextField txtempcontact;

    @FXML
    private DatePicker txtempdob;

    @FXML
    private TextField txtempemail;

    @FXML
    private TextField txtempid;

    @FXML
    private TextField txtempjob;

    @FXML
    private TextField txtempname;

    @FXML
    private TextField txtempnic;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblinvalidcontact;

    @FXML
    private Label lblinvalidemail;

    @FXML
    private Label lblinvalidnic;

    @FXML
    private Label lblinvalidemployeeid;

    @FXML
    void initialize() {
        setCellValueFactory();
        getAll();
        assert adminchangingPane != null : "fx:id=\"adminchangingPane\" was not injected: check your FXML file 'adminemployeeform.fxml'.";
        assert logoutbtn1 != null : "fx:id=\"logoutbtn1\" was not injected: check your FXML file 'adminemployeeform.fxml'.";
        assert logoutlabel1 != null : "fx:id=\"logoutlabel1\" was not injected: check your FXML file 'adminemployeeform.fxml'.";

        TextFieldBorderController.txtfieldbordercolor(txtempid);
        TextFieldBorderController.txtfieldbordercolor(txtempname);
        TextFieldBorderController.txtfieldbordercolor(txtempnic);
        TextFieldBorderController.datepickerbordercolor(txtempdob);
        TextFieldBorderController.txtfieldbordercolor(txtempjob);
        TextFieldBorderController.txtfieldbordercolor(txtempcontact);
        TextFieldBorderController.txtfieldbordercolor(txtempaddress);
        TextFieldBorderController.txtfieldbordercolor(txtempemail);

        lblinvalidemail.setVisible(false);
        lblinvalidcontact.setVisible(false);
        lblinvalidnic.setVisible(false);
        lblinvalidemployeeid.setVisible(false);
    }

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.project_wineverse.view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void logoutbtnMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.project_wineverse.view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtempid.getText();
        String name = txtempname.getText();
        String nic = txtempnic.getText();
        String dob = String.valueOf(txtempdob.getValue());
        String job = txtempjob.getText();
        String contact = txtempcontact.getText();
        String address = txtempaddress.getText();
        String email = txtempemail.getText();

        if(id.isEmpty() || name.isEmpty() || nic.isEmpty() || dob.isEmpty() || job.isEmpty() || contact.isEmpty() || address.isEmpty()){
            AlertController.errormessage("Employee not saved successfully.\nPlease make sure to fill out all the required fields.");
        }else{
            if(ValidateField.employeeIdCheck(id)) {
                if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                    if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email)) {
                        if (ValidateField.nicCheck(nic) || ValidateField.contactCheck(contact)) {
                            if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                                if (ValidateField.contactCheck(contact)) {
                                    if (ValidateField.emailCheck(email)) {
                                        if (ValidateField.nicCheck(nic)) {

                                            EmployeeDTO employee = new EmployeeDTO(id, name, nic, dob, job, contact, address, email);

                                            try {
                                                boolean isSaved = EmployeeModel.save(employee);
                                                if (isSaved) {
                                                    AlertController.confirmmessage("New employee added successfully");
                                                    txtempid.setText(null);
                                                    txtempname.setText(null);
                                                    txtempdob.setValue(null);
                                                    txtempaddress.setText(null);
                                                    txtempcontact.setText(null);
                                                    txtempemail.setText(null);
                                                    txtempjob.setText(null);
                                                    txtempnic.setText(null);
                                                    getAll();
                                                }
                                            }catch(SQLIntegrityConstraintViolationException e){
                                            AlertController.errormessage("This Employee ID already exists.");
                                            } catch (SQLException e) {
                                                System.out.println(e);
                                                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                                            }
                                        } else {
                                            lblinvalidnic.setVisible(true);
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
                            lblinvalidnic.setVisible(true);
                            lblinvalidcontact.setVisible(true);
                        }
                    } else {
                        lblinvalidemail.setVisible(true);
                        lblinvalidnic.setVisible(true);
                    }
                } else {
                    lblinvalidemail.setVisible(true);
                    lblinvalidcontact.setVisible(true);
                    lblinvalidnic.setVisible(true);
                }
            }else{
                lblinvalidemployeeid.setVisible(true);
                lblinvalidemployeeid.setStyle("-fx-text-fill: red");
            }
        }

    }

    private void setCellValueFactory() {
        empcolid.setCellValueFactory(new PropertyValueFactory<>("id"));
        empcolname.setCellValueFactory(new PropertyValueFactory<>("name"));
        empcoldob.setCellValueFactory(new PropertyValueFactory<>("nic"));
        empcoladdress.setCellValueFactory(new PropertyValueFactory<>("dob"));
        empcolcontact.setCellValueFactory(new PropertyValueFactory<>("job"));
        empcolemail.setCellValueFactory(new PropertyValueFactory<>("contact"));
        empcoljob.setCellValueFactory(new PropertyValueFactory<>("address"));
        empcolnic.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void getAll(){
        ObservableList<EmployeeTM> obList = null;
        try {
            obList = EmployeeModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblEmployee.setItems(obList);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        txtempid.setText(null);
        txtempname.setText(null);
        txtempdob.setValue(null);
        txtempaddress.setText(null);
        txtempcontact.setText(null);
        txtempemail.setText(null);
        txtempjob.setText(null);
        txtempnic.setText(null);

        try {
            EmployeeDTO employee = EmployeeModel.findById(id);
            if(employee!=null) {
                txtempid.setText(employee.getId());
                txtempname.setText(employee.getName());
                txtempnic.setText(employee.getNic());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                txtempdob.setValue(date);

                txtempjob.setText(employee.getJob());
                txtempcontact.setText(employee.getContact());
                txtempaddress.setText(employee.getAddress());
                txtempemail.setText(employee.getEmail());

                txtSearch.setText("");

            }else{
                AlertController.errormessage("Employee ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) throws SQLException {

    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<EmployeeTM> obList = EmployeeModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTM> filteredData = obList.filtered(new Predicate<EmployeeTM>(){
                @Override
                public boolean test(EmployeeTM employeetm) {
                    return String.valueOf(employeetm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEmployee.setItems(filteredData);
        } else {
            tblEmployee.setItems(obList);
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
        String id = txtempid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this employee?");
        if(result==true) {
            try {
                boolean isDeleted = EmployeeModel.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Employee Deleted Successfully");
                    txtempid.setText(null);
                    txtempname.setText(null);
                    txtempdob.setValue(null);
                    txtempaddress.setText(null);
                    txtempcontact.setText(null);
                    txtempemail.setText(null);
                    txtempjob.setText(null);
                    txtempnic.setText(null);
                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
        String id = txtempid.getText();
        String name = txtempname.getText();
        String nic = txtempnic.getText();
        String dob = String.valueOf(txtempdob.getValue());
        String job = txtempjob.getText();
        String contact = txtempcontact.getText();
        String address = txtempaddress.getText();
        String email = txtempemail.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this employees' details?");
        if(ValidateField.employeeIdCheck(id)) {
            if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email)) {
                    if (ValidateField.nicCheck(nic) || ValidateField.contactCheck(contact)) {
                        if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                            if (ValidateField.contactCheck(contact)) {
                                if (ValidateField.emailCheck(email)) {
                                    if (ValidateField.nicCheck(nic)) {
                                        if (result == true) {

                                            EmployeeDTO employee = new EmployeeDTO(id, name, nic, dob, job, contact, address, email);
                                            try {
                                                boolean isUpdated = EmployeeModel.update(employee);
                                                if (isUpdated) {
                                                    AlertController.confirmmessage("Employee Details Updated");
                                                    txtempid.setText(null);
                                                    txtempname.setText(null);
                                                    txtempdob.setValue(null);
                                                    txtempaddress.setText(null);
                                                    txtempcontact.setText(null);
                                                    txtempemail.setText(null);
                                                    txtempjob.setText(null);
                                                    txtempnic.setText(null);
                                                    getAll();
                                                }
                                            }catch(SQLIntegrityConstraintViolationException e){
                                                System.out.println(e);
                                                AlertController.errormessage(String.valueOf(e.getMessage()));
                                            } catch (Exception e) {
                                                System.out.println(e);
                                                AlertController.errormessage("something went wrong!");
                                            }
                                        }
                                    } else {
                                        lblinvalidnic.setVisible(true);
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
                        lblinvalidnic.setVisible(true);
                        lblinvalidcontact.setVisible(true);
                    }
                } else {
                    lblinvalidemail.setVisible(true);
                    lblinvalidnic.setVisible(true);
                }
            } else {
                lblinvalidemail.setVisible(true);
                lblinvalidcontact.setVisible(true);
                lblinvalidnic.setVisible(true);
            }
        }else{
            lblinvalidemployeeid.setVisible(true);
            lblinvalidemployeeid.setStyle("-fx-text-fill: red");
        }
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<EmployeeTM, ?>> columns = tblEmployee.getColumns();

        txtempid.setText(columns.get(0).getCellData(row).toString());
        txtempname.setText(columns.get(1).getCellData(row).toString());
        txtempnic.setText(columns.get(2).getCellData(row).toString());
        txtempdob.setValue(LocalDate.parse(columns.get(3).getCellData(row).toString()));
        txtempjob.setText(columns.get(4).getCellData(row).toString());
        txtempcontact.setText(columns.get(5).getCellData(row).toString());
        txtempaddress.setText(columns.get(6).getCellData(row).toString());
        txtempemail.setText(columns.get(7).getCellData(row).toString());

        txtSearch.setText("");
    }

    public void txtemailOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidemail.setVisible(false);
    }

    public void txtcontactOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidcontact.setVisible(false);
    }

    public void txtempnicOnAction(ActionEvent actionEvent) {
    }

    public void txtempnicOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidnic.setVisible(false);
    }

    public void txtempidOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidemployeeid.setVisible(false);
        lblinvalidemployeeid.setStyle("-fx-text-fill: black");

    }

    public void clearbtnOnMouseClicked(MouseEvent mouseEvent) {
        txtempid.setText(null);
        txtempname.setText(null);
        txtempdob.setValue(null);
        txtempaddress.setText(null);
        txtempcontact.setText(null);
        txtempemail.setText(null);
        txtempjob.setText(null);
        txtempnic.setText(null);
    }

    public void clearlblOnMouseClicked(MouseEvent mouseEvent) {
        txtempid.setText(null);
        txtempname.setText(null);
        txtempdob.setValue(null);
        txtempaddress.setText(null);
        txtempcontact.setText(null);
        txtempemail.setText(null);
        txtempjob.setText(null);
        txtempnic.setText(null);
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearch.setText("");
        getAll();
    }
}
