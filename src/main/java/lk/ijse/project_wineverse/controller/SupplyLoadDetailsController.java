package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.SupplyLoadDetailsBO;
import lk.ijse.project_wineverse.dto.SupplyLoadDetailsDTO;
import lk.ijse.project_wineverse.view.tdm.SupplyLoadDetailTM;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.ValidateField;

public class SupplyLoadDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnShowAll;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colitemcode;

    @FXML
    private TableColumn<?, ?> colloadid;

    @FXML
    private TableColumn<?, ?> colprice;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colsupid;

    @FXML
    private TableColumn<?, ?> coltime;

    @FXML
    private Label orderdetailslbl;

    @FXML
    private TableView<SupplyLoadDetailTM> tblSupplyDetail;

    @FXML
    private TextField txtSearchLoadID;

    @FXML
    private TextField txtSearchdate;

    @FXML
    private TextField txtSearchsupID;

    SupplyLoadDetailsBO supplyLoadDetailsBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLYLOADDETAILS_BO);

    void getAll(){
        ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();
        try {
          //  obList = SupplyLoadDetailModel.getAll();
            ArrayList<SupplyLoadDetailsDTO> all = supplyLoadDetailsBO.getAll();

            for (SupplyLoadDetailsDTO dto : all) {
                obList.add(new SupplyLoadDetailTM(dto.getLoad_id(),dto.getSupp_id(),dto.getItem_code(),dto.getSupp_qty(),String.valueOf(dto.getDate()),String.valueOf(dto.getTime()),dto.getPrice()));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        tblSupplyDetail.setItems(obList);
    }

    private void setCellValueFactory() {
        colloadid.setCellValueFactory(new PropertyValueFactory<>("loadid"));
        colsupid.setCellValueFactory(new PropertyValueFactory<>("suppid"));
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("supp_qty"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    void btnShowAllOnAction(ActionEvent event) {
        getAll();
        txtSearchdate.setText("");
        txtSearchLoadID.setText("");
        txtSearchsupID.setText("");
    }

    @FXML
    void tblOrderDetailOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtSearchLoadIDOnAction(ActionEvent event) throws SQLException {
        String loadid = txtSearchLoadID.getText();
        if(ValidateField.loadIdCheck(loadid)) {
         //   ObservableList<SupplyLoadDetailTM> obList = SupplyLoadDetailModel.searchbyloadid(loadid);
            ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

            ArrayList<SupplyLoadDetailsDTO> all = supplyLoadDetailsBO.searchbyloadid(loadid);

            for (SupplyLoadDetailsDTO dto : all) {
                obList.add(new SupplyLoadDetailTM(dto.getLoad_id(),dto.getSupp_id(),dto.getItem_code(),dto.getSupp_qty(),String.valueOf(dto.getDate()),String.valueOf(dto.getTime()),dto.getPrice()));
            }

            tblSupplyDetail.setItems(obList);
        }else{
            AlertController.errormessage("Wrong LoadID Format\nRequired format : LOAD-***");
        }
    }

    @FXML
    void txtSearchdateOnAction(ActionEvent event) throws SQLException {
        String date = txtSearchdate.getText();
        if(ValidateField.dateCheck(date)) {
         //   ObservableList<SupplyLoadDetailTM> obList = SupplyLoadDetailModel.searchbyloaddate(date);
            ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

            ArrayList<SupplyLoadDetailsDTO> all = supplyLoadDetailsBO.searchbyloaddate(date);

            for (SupplyLoadDetailsDTO dto : all) {
                obList.add(new SupplyLoadDetailTM(dto.getLoad_id(),dto.getSupp_id(),dto.getItem_code(),dto.getSupp_qty(),String.valueOf(dto.getDate()),String.valueOf(dto.getTime()),dto.getPrice()));
            }

            tblSupplyDetail.setItems(obList);
        }else{
            AlertController.errormessage("Wrong Date Format\nRequired format : (yyyy-mm-dd)");
        }
    }

    @FXML
    void txtSearchsupIDOnAction(ActionEvent event) throws SQLException {
        String suppid = txtSearchsupID.getText();
        if(ValidateField.supplierIdCheck(suppid)) {
         //   ObservableList<SupplyLoadDetailTM> obList = SupplyLoadDetailModel.searchbysuppid(suppid);
            ObservableList<SupplyLoadDetailTM> obList = FXCollections.observableArrayList();

            ArrayList<SupplyLoadDetailsDTO> all = supplyLoadDetailsBO.searchbysuppid(suppid);

            for (SupplyLoadDetailsDTO dto : all) {
                obList.add(new SupplyLoadDetailTM(dto.getLoad_id(),dto.getSupp_id(),dto.getItem_code(),dto.getSupp_qty(),String.valueOf(dto.getDate()),String.valueOf(dto.getTime()),dto.getPrice()));
            }

            tblSupplyDetail.setItems(obList);
        }else{
            AlertController.errormessage("Wrong Supplier ID Format\nRequired format : sup***");
        }
    }

    @FXML
    void initialize() {
        assert btnShowAll != null : "fx:id=\"btnShowAll\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert coldate != null : "fx:id=\"coldate\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert colitemcode != null : "fx:id=\"colitemcode\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert colloadid != null : "fx:id=\"coloadid\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert colprice != null : "fx:id=\"colprice\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert colqty != null : "fx:id=\"colqty\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert colsupid != null : "fx:id=\"colsupid\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert coltime != null : "fx:id=\"coltime\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert orderdetailslbl != null : "fx:id=\"orderdetailslbl\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert tblSupplyDetail != null : "fx:id=\"tblSupplyDetail\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert txtSearchLoadID != null : "fx:id=\"txtSearchLoadID\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert txtSearchdate != null : "fx:id=\"txtSearchdate\" was not injected: check your FXML file 'supplydetailsform.fxml'.";
        assert txtSearchsupID != null : "fx:id=\"txtSearchsupID\" was not injected: check your FXML file 'supplydetailsform.fxml'.";

        setCellValueFactory();
        getAll();
    }

    public void tblSupplyLoadDetailOnMouseClicked(MouseEvent mouseEvent) {

    }
}
