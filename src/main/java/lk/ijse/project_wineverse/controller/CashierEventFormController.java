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
import jfxtras.scene.control.LocalTimeTextField;
import lk.ijse.project_wineverse.dto.EventDTO;
import lk.ijse.project_wineverse.dto.tm.EventTM;
import lk.ijse.project_wineverse.model.EmployeeModel;
import lk.ijse.project_wineverse.model.EventModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.TextFieldBorderController;
import lk.ijse.project_wineverse.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CashierEventFormController implements Initializable {

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> empcolcontact;

    @FXML
    private TableColumn<?, ?> empcoldob;

    @FXML
    private TableColumn<?, ?> empcolid;

    @FXML
    private TableColumn<?, ?> empcoljob;

    @FXML
    private TableColumn<?, ?> empcolname;

    @FXML
    private TableColumn<?, ?> empcolnic;

    @FXML
    private ImageView logoutbtn;

    @FXML
    private Label logoutlabel;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<EventTM> tblEvent;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtempid;

    @FXML
    private DatePicker txteventdate;

    @FXML
    private TextField txteventid;

    @FXML
    private TextField txteventname;

    @FXML
    private LocalTimeTextField txteventtime;

    @FXML
    private ComboBox cmbempid;

    @FXML
    private TextField txteventtype;

    @FXML
    private TableColumn<?, ?> colempid;

    @FXML
    private TableColumn<?, ?> coleventdate;

    @FXML
    private TableColumn<?, ?> coleventid;

    @FXML
    private TableColumn<?, ?> coleventname;

    @FXML
    private TableColumn<?, ?> coleventtime;

    @FXML
    private TableColumn<?, ?> coleventtype;

    @FXML
    private Label invalideventidformat;

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

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblEvent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<EventTM, ?>> columns = tblEvent.getColumns();

        cmbempid.setValue(columns.get(0).getCellData(row).toString());
        txteventid.setText(columns.get(1).getCellData(row).toString());
        txteventname.setText(columns.get(2).getCellData(row).toString());
        txteventtype.setText(columns.get(3).getCellData(row).toString());
        txteventdate.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
        txteventtime.setLocalTime(LocalTime.parse(columns.get(5).getCellData(row).toString()));

        txtSearch.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String empid;
        if(cmbempid.getValue()==null){
            empid=null;
        }else{
            empid = String.valueOf(cmbempid.getValue());
        }
        String eventid = txteventid.getText();
        String eventname = txteventname.getText();
        String eventtype = txteventtype.getText();
        String eventdate = String.valueOf(txteventdate.getValue());
        String eventtime = String.valueOf(txteventtime.getLocalTime());

        if(eventid.isEmpty() || eventname.isEmpty() || txteventdate.getValue()==null || txteventtime.getLocalTime()==null){
            AlertController.errormessage("Event details are not complete to be saved.\nPlease make sure to fill out all the required fields.");
        }else{
            if(ValidateField.eventIdCheck(eventid)) {
                EventDTO event = new EventDTO(eventid, eventname, eventtype, eventdate, eventtime, empid);

                try {
                    boolean isSaved = EventModel.save(event);
                    if (isSaved) {
                        AlertController.confirmmessage("Event Created Successfully");
                        cmbempid.setValue(null);
                        txteventname.setText("");
                        txteventid.setText("");
                        txteventtype.setText("");
                        txteventdate.getEditor().clear();
                        txteventtime.setLocalTime(null);

                        getAll();
                    }
                }catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(e);
                    new Alert(Alert.AlertType.ERROR, "Duplicate Event ID").show();
                } catch (Exception e) {
                    System.out.println(e);
                    new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                }
            }else{
                invalideventidformat.setVisible(true);
                txteventid.setStyle("-fx-text-fill: red");
            }
        }
    }

//    String filepath=null;
    public void btnaddimageOnAction(ActionEvent actionEvent) {
//        FileChooser fileChooser = new FileChooser();
//        File selectedFile=fileChooser.showOpenDialog(null);
//
//        if (selectedFile == null) {
//            System.out.println("filepath is null");
//        }else if(selectedFile != null) {
//            File file = new File(filepath);
//            if (file.exists()) {
//                filepath = selectedFile.getAbsolutePath();
//                // Do something with the selected file
//            } else {
//                System.out.println("File not found: " + filepath);
//                // Handle the case where the file does not exist
//            }
//        }
    }

    private void getAll(){
        ObservableList<EventTM> obList = null;
        try {
            obList = EventModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblEvent.setItems(obList);
    }

    private void setCellValueFactory() {
        colempid.setCellValueFactory(new PropertyValueFactory<>("empid"));
        coleventid.setCellValueFactory(new PropertyValueFactory<>("eventid"));
        coleventname.setCellValueFactory(new PropertyValueFactory<>("eventname"));
        coleventtype.setCellValueFactory(new PropertyValueFactory<>("eventtype"));
        coleventdate.setCellValueFactory(new PropertyValueFactory<>("eventdate"));
        coleventtime.setCellValueFactory(new PropertyValueFactory<>("eventtime"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this events' details?");
        if(result==true) {

            try {
                String empid;
                if(cmbempid.getValue()==null){
                    empid=null;
                }else{
                    empid = String.valueOf(cmbempid.getValue());
                }
                String eventid = txteventid.getText();
                String eventname = txteventname.getText();
                String eventtype = txteventtype.getText();
                String eventdate = String.valueOf(txteventdate.getValue());
                String eventtime = String.valueOf(txteventtime.getLocalTime());

                EventDTO event = new EventDTO(eventid,eventname,eventtype,eventdate,eventtime,empid);
                boolean isUpdated = EventModel.update(event);
                if (isUpdated) {
                    AlertController.confirmmessage("Event Details Updated");
                    cmbempid.setValue(null);
                    txteventid.setText("");
                    txteventname.setText("");
                    txteventtype.setText("");
                    txteventdate.getEditor().clear();
                    txteventtime.setLocalTime(null);

                    getAll();
                }
            }catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
        String eventid = txteventid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this salary details?");
        if(result==true) {
            try {
                boolean isDeleted = EventModel.delete(eventid);
                if (isDeleted) {
                    AlertController.confirmmessage("Salary details Deleted Successfully");
                    cmbempid.setValue(null);
                    txteventid.setText("");
                    txteventname.setText("");
                    txteventtype.setText("");
                    txteventdate.getEditor().clear();
                    txteventtime.setLocalTime(null);

                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        cmbempid.setValue(null);
        txteventid.setText("");
        txteventname.setText("");
        txteventtype.setText("");
        txteventdate.getEditor().clear();
        txteventtime.setLocalTime(null);

        try {
            EventDTO event = EventModel.findById(id);
            if(event!=null) {
                cmbempid.setValue(event.getEmpid());
                txteventid.setText(event.getEventid());
                txteventname.setText(event.getEventname());
                txteventtype.setText(event.getEventtype());
                txteventdate.setValue(LocalDate.parse(event.getEventdate()));
                txteventtime.setLocalTime(LocalTime.parse(event.getEventtime()));

                txtSearch.setText("");
            }else{
                AlertController.errormessage("Event ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());
    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<EventTM> obList = EventModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EventTM> filteredData = obList.filtered(new Predicate<EventTM>(){
                @Override
                public boolean test(EventTM eventtm) {
                    return eventtm.getEventid().toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEvent.setItems(filteredData);
        } else {
            tblEvent.setItems(obList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFieldBorderController.comboboxbordercolor(cmbempid);
        TextFieldBorderController.txtfieldbordercolor(txteventid);
        TextFieldBorderController.txtfieldbordercolor(txteventname);
        TextFieldBorderController.txtfieldbordercolor(txteventtype);
        TextFieldBorderController.datepickerbordercolor(txteventdate);
        TextFieldBorderController.localtimebordercolor(txteventtime);

        setCellValueFactory();
        getAll();

        loadEmployeeIds();

        invalideventidformat.setVisible(false);

    }

    public void txteventidOnMouseClicked(MouseEvent mouseEvent) {
        invalideventidformat.setVisible(false);
        txteventid.setStyle("-fx-text-fill: black");
    }

    public void clearbtnOnMouseClicked(MouseEvent mouseEvent) {
        cmbempid.setValue(null);
        txteventid.setText("");
        txteventname.setText("");
        txteventtype.setText("");
        txteventdate.getEditor().clear();
        txteventtime.setLocalTime(null);
    }

    public void clearlblOnMouseClicked(MouseEvent mouseEvent) {
        cmbempid.setValue(null);
        txteventid.setText("");
        txteventname.setText("");
        txteventtype.setText("");
        txteventdate.getEditor().clear();
        txteventtime.setLocalTime(null);
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearch.setText("");
        getAll();
    }

    public void addimagegroupOnMousePressed(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk.ijse.project_wineverse.view/addeventimageform.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }

    public void addimagesbtnOnMousePressed(MouseEvent mouseEvent) {
    }
}
