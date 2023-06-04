package lk.ijse.project_wineverse.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
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
import javafx.util.Callback;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.SalaryBO;
import lk.ijse.project_wineverse.dto.SalaryDTO;
import lk.ijse.project_wineverse.view.tdm.SalaryTM;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.TextFieldBorderController;
import lk.ijse.project_wineverse.util.ValidateField;

public class AdminSalaryController {

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
    private ComboBox<String> cmbempid;

    @FXML
    private TableColumn<?, ?> colempid;

    @FXML
    private TableColumn<SalaryTM, Double> colot;

    @FXML
    private TableColumn<?, ?> colpaymethod;

    @FXML
    private TableColumn<SalaryTM, Double> colslryamount;

    @FXML
    private TableColumn<?, ?> colslryid;

    @FXML
    private ImageView logoutbtn1;

    @FXML
    private Label logoutlabel1;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<SalaryTM> tblSalary;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtot;

    @FXML
    private ComboBox<?> txtpaymethod;

    @FXML
    private TextField txtsalary;

    @FXML
    private TextField txtsalaryid;

    @FXML
    private ComboBox cmbpaymethod;

    SalaryBO salaryBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SALARY_BO);

    @FXML
    void logoutbtnMousePressed(MouseEvent event) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void logoutlabelMousePressed(MouseEvent event) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void initialize() {
        assert adminchangingPane != null : "fx:id=\"adminchangingPane\" was not injected: check your FXML file 'adminsalaryform.fxml'.";
        assert logoutbtn1 != null : "fx:id=\"logoutbtn1\" was not injected: check your FXML file 'adminsalaryform.fxml'.";
        assert logoutlabel1 != null : "fx:id=\"logoutlabel1\" was not injected: check your FXML file 'adminsalaryform.fxml'.";
        cmbpaymethod.getItems().addAll("Bank Deposit", "Cash");
        loadEmployeeIds();

        setCellValueFactory();
        getAll();

        TextFieldBorderController.comboboxbordercolor(cmbempid);
        TextFieldBorderController.txtfieldbordercolor(txtsalaryid);
        TextFieldBorderController.txtfieldbordercolor(txtsalary);
        TextFieldBorderController.txtfieldbordercolor(txtot);
        TextFieldBorderController.comboboxbordercolor(cmbpaymethod);

        cmbempid.setValue("");
        txtsalaryid.setText("");
        txtsalary.setText("");
        txtot.setText("");
        cmbpaymethod.setValue("");
    }

    private void loadEmployeeIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = salaryBO.loadEmployeeIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbempid.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblSalary.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SalaryTM, ?>> columns = tblSalary.getColumns();

        cmbempid.setValue(columns.get(0).getCellData(row).toString());
        txtsalaryid.setText(columns.get(1).getCellData(row).toString());
        txtsalary.setText(columns.get(2).getCellData(row).toString());
        txtot.setText(columns.get(3).getCellData(row).toString());
        cmbpaymethod.setValue(columns.get(4).getCellData(row).toString());

        txtSearch.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            String empid = cmbempid.getValue();
            String slryid = txtsalaryid.getText();
            Double slryamount= Double.valueOf(txtsalary.getText());

            Double ot;
            if(txtot.getText().isEmpty()){
                System.out.println("if");
                ot=0.00;
            }else{
                System.out.println("else");
                ot= Double.valueOf(txtot.getText());
            }

            String paymethod = String.valueOf(cmbpaymethod.getValue());

            if(empid.isEmpty()|| cmbempid.getValue()==null || slryid.isEmpty() || txtsalary.getText().isEmpty() || paymethod.isEmpty()){
                AlertController.errormessage("Salary details can't be added.\nPlease make sure to fill out all the required fields.");
            }else{
                if(ValidateField.salaryIdCheck(slryid)) {
                        SalaryDTO salary = new SalaryDTO(empid, slryid, slryamount, ot, paymethod);
                        boolean isSaved = salaryBO.save(salary);
                        if (isSaved) {
                            AlertController.confirmmessage("New salary details added successfully");
                            cmbempid.setValue("");
                            txtsalaryid.setText("");
                            txtsalary.setText("");
                            txtot.setText("");
                            cmbpaymethod.setValue("");

                            getAll();
                        }
                }else{
                    txtsalaryid.setStyle("-fx-text-fill: red");
                    AlertController.errormessage("Wrong Salary ID format\nRequired format- \"year-firstThreeLettersOfMonthName-***\"\nEx:- 2020-jan-001");
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            AlertController.errormessage("Duplicate Salary ID");
        }catch (NumberFormatException e) {
            System.out.println(e);
            AlertController.errormessage("Wrong input format for Salary Amount or OverTime.\nPlease check the fields before you proceed.");
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    private void getAll(){
        ObservableList<SalaryTM> obList = FXCollections.observableArrayList();
        try {
            ArrayList<SalaryDTO> all = salaryBO.getAll();

            for (SalaryDTO dto : all) {
                obList.add(new SalaryTM(dto.getEmpid(),dto.getSlryid(),dto.getSlryamount(),dto.getOt(),dto.getPaymethod()));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblSalary.setItems(obList);
    }

    private void setCellValueFactory() {
        colempid.setCellValueFactory(new PropertyValueFactory<>("empid"));
        colslryid.setCellValueFactory(new PropertyValueFactory<>("slryid"));
        colslryamount.setCellValueFactory(new PropertyValueFactory<SalaryTM, Double>("slryamount"));
        colot.setCellValueFactory(new PropertyValueFactory<SalaryTM, Double>("ot"));
        colpaymethod.setCellValueFactory(new PropertyValueFactory<>("paymethod"));

        colslryamount.setCellFactory(new Callback<TableColumn<SalaryTM, Double>, TableCell<SalaryTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<SalaryTM, Double> call(TableColumn<SalaryTM, Double> column) {
                return new TableCell<SalaryTM, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });
        colot.setCellFactory(new Callback<TableColumn<SalaryTM, Double>, TableCell<SalaryTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<SalaryTM, Double> call(TableColumn<SalaryTM, Double> column) {
                return new TableCell<SalaryTM, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this employees' details?");
        if(result==true) {

            try {
                String empid = cmbempid.getValue();
                String slryid = txtsalaryid.getText();
                Double slryamount= Double.valueOf(txtsalary.getText());

                Double ot;
                if(txtot.getText().isEmpty()){
                    ot = 0.00;
                }else{
                    ot = Double.valueOf(txtot.getText());
                }

                String paymethod = String.valueOf(cmbpaymethod.getValue());

                    SalaryDTO salary = new SalaryDTO(empid, slryid, slryamount, ot, paymethod);
                  //  boolean isUpdated = SalaryModel.update(salary);
                boolean isUpdated = salaryBO.update(salary);
                    if (isUpdated) {
                        AlertController.confirmmessage("Salary Details Updated");
                        cmbempid.setValue("");
                        txtsalaryid.setText("");
                        txtsalary.setText("");
                        txtot.setText("");
                        cmbpaymethod.setValue("");

                        getAll();
                    }
            } catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
        String slryid = txtsalaryid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this salary details?");
        if(result==true) {
            try {
               // boolean isDeleted = SalaryModel.delete(slryid);
                boolean isDeleted = salaryBO.delete(slryid);
                if (isDeleted) {
                    AlertController.confirmmessage("Salary details Deleted Successfully");
                    cmbempid.setValue("");
                    txtsalaryid.setText("");
                    txtsalary.setText("");
                    txtot.setText("");
                    cmbpaymethod.setValue("");

                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) {
    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<SalaryTM> obList = FXCollections.observableArrayList();

        ArrayList<SalaryDTO> all = salaryBO.getAll();

        for (SalaryDTO dto : all) {
            obList.add(new SalaryTM(dto.getEmpid(),dto.getSlryid(),dto.getSlryamount(),dto.getOt(),dto.getPaymethod()));
        }

        if (!searchValue.isEmpty()) {
            ObservableList<SalaryTM> filteredData = obList.filtered(new Predicate<SalaryTM>(){
                @Override
                public boolean test(SalaryTM salarytm) {
                    return salarytm.getEmpid().toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblSalary.setItems(filteredData);
        } else {
            tblSalary.setItems(obList);
        }
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
    }

    public void clearbtnOnMouseClicked(MouseEvent mouseEvent) {
        cmbempid.setValue("");
        txtsalaryid.setText("");
        txtsalary.setText("");
        txtot.setText("");
        cmbpaymethod.setValue("");
    }

    public void clearlblOnMouseClicked(MouseEvent mouseEvent) {
        cmbempid.setValue("");
        txtsalaryid.setText("");
        txtsalary.setText("");
        txtot.setText("");
        cmbpaymethod.setValue("");
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearch.setText("");
        getAll();
    }

    public void txtsalaryidOnMouseClicked(MouseEvent mouseEvent) {

    }

    public void txtotOnMouseClicked(MouseEvent mouseEvent) {
        txtot.setStyle("-fx-text-fill: black");
    }
}
