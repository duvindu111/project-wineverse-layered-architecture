package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.AddEventImageBO;
import lk.ijse.project_wineverse.model.AddEventImageModel;
import lk.ijse.project_wineverse.model.EmployeeModel;
import lk.ijse.project_wineverse.model.EventModel;
import lk.ijse.project_wineverse.util.AlertController;

public class AddEventImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbeventid;

    @FXML
    private JFXButton gtetheimagebtn;

    @FXML
    private JFXButton savebtn;

    @FXML
    private Label theimagelbl;

    @FXML
    private AnchorPane addeventimageancpane;

    AddEventImageBO addEventImageBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EVENTIMAGE_BO);

    private void loadEventIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
        //    List<String> ids = EventModel.loadIds();
            List<String> ids = addEventImageBO.loadEventIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbeventid.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    String filePath;
    @FXML
    void gtetheimagebtnOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            System.out.println(filePath);
            // Do something with the selected file
        }
    }

    @FXML
    void savebtnOnAction(ActionEvent event) throws FileNotFoundException, SQLException {
        try {
            String eventid = cmbeventid.getValue();
            InputStream in = new FileInputStream(filePath);

        //    boolean isSaved = AddEventImageModel.saveImage(eventid, in);
            boolean isSaved = addEventImageBO.saveEventImage(eventid,in);
            System.out.println(isSaved);
            if(isSaved){
                AlertController.confirmmessage("Event image added successfully");
                filePath=null;
                cmbeventid.setValue(null);
            }

            addeventimageancpane.getScene().getWindow().hide();
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println(e);
            AlertController.errormessage("Duplicate Event ID");
        }catch(NullPointerException e){
            System.out.println(e);
            AlertController.errormessage("Please fill all required fields before trying to add an image to an event");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void initialize() {
        assert cmbeventid != null : "fx:id=\"cmbeventid\" was not injected: check your FXML file 'addeventimageform.fxml'.";
        assert gtetheimagebtn != null : "fx:id=\"gtetheimagebtn\" was not injected: check your FXML file 'addeventimageform.fxml'.";
        assert savebtn != null : "fx:id=\"savebtn\" was not injected: check your FXML file 'addeventimageform.fxml'.";
        assert theimagelbl != null : "fx:id=\"theimagelbl\" was not injected: check your FXML file 'addeventimageform.fxml'.";

        loadEventIds();
    }

    public void deletebtnOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            String eventid = cmbeventid.getValue();

            boolean isDeleted = addEventImageBO.deleteEventImage(eventid);
            System.out.println(isDeleted);
            if(isDeleted){
                AlertController.confirmmessage("Event image deleted successfully");
                filePath=null;
                cmbeventid.setValue(null);
            }else{
                AlertController.errormessage("Can't delete image from the event.Please check the fields again");
            }
        }catch(NullPointerException e){
            System.out.println(e);
            AlertController.errormessage("Please select an event ID first");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
