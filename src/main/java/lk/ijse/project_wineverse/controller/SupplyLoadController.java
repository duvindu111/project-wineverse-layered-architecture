package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.SupplyLoadBO;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.PlaceSupplyLoadDTO;
import lk.ijse.project_wineverse.view.tdm.AddSupplyLoadTM;
import lk.ijse.project_wineverse.controller.util.AlertController;
import lk.ijse.project_wineverse.controller.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplyLoadController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addicon;

    @FXML
    private AnchorPane cashchangingPane;

    @FXML
    private Label balancelbl;

    @FXML
    private JFXButton btnAddSupplyLoad;

    @FXML
    private JFXButton btnAddToLoad;

    @FXML
    private ComboBox<String> cmbitemcode;

    @FXML
    private ComboBox<String> cmbsuppid;

    @FXML
    private TableColumn<?, ?> colaction;

    @FXML
    private TableColumn<?, ?> colcategory;

    @FXML
    private TableColumn<?, ?> colitemcode;

    @FXML
    private TableColumn<?, ?> colitemname;

    @FXML
    private TableColumn<?, ?> colquantity;

    @FXML
    private ImageView lblbalance;

    @FXML
    private Label lblchangingcategory;

    @FXML
    private Label lblchangingsuppname;

    @FXML
    private Label lblchangingitmname;

    @FXML
    private Label lblchangingqtyonhands;

    @FXML
    private Label lblitemcategory;

    @FXML
    private Label lblitemname;

    @FXML
    private Label lblitemqtyonhand;

    @FXML
    private Label lblloadid;

    @FXML
    private Label lblsuppliername;

    @FXML
    private Label lblsupplydate;

    @FXML
    private Label lblsupplytime;

    @FXML
    private Label lbltotalprice;

    @FXML
    private ImageView logoutbtn1;

    @FXML
    private Label logoutlabel1;

    @FXML
    private ImageView seesupplydet;

    @FXML
    private TextField supplyqty;

    @FXML
    private TableView<?> tblplaceOrder;

    @FXML
    private TextField txttotalprice;

    @FXML
    private TableView<AddSupplyLoadTM> tblplaceLoad;

    @FXML
    private Label txtmoremoney;

    @FXML
    private AnchorPane adminchangingPane;

    SupplyLoadBO supplyLoadBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLYLOAD_BO);

    private void loadCustomerIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
        //  List<String> ids = SupplierModel.loadIds();
            List<String> ids = supplyLoadBO.loadSupplierIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbsuppid.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
          //  List<String> codes = ItemModel.loadCodes();
            List<String> codes =supplyLoadBO.loadItemCodes();

            for (String code : codes) {
                obList.add(code);
            }
            cmbitemcode.setItems(obList);
        } catch (SQLException e) {
        }
    }

    private void generateNextLoadId() {
        try {
         // String id = CashierSupplyLoadModel.getNextSupplyLoadId();
            String id = supplyLoadBO.getNextSupplyLoadId();
            String nextloadid= splitOrderId(id);

            lblloadid.setText(nextloadid);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("LOAD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "LOAD-" + digit;
        }
        return "LOAD-001";
    }

    @FXML
    void addiconOnMouseClicked(MouseEvent event) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/newsupplierform.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnDeleteMousePressed(MouseEvent event) {

    }

    private void setCellValueFactory() {
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colitemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colaction.setCellValueFactory(new PropertyValueFactory<>("removebtn"));
    }

    private ObservableList<AddSupplyLoadTM> obList = FXCollections.observableArrayList();
    Button btnremove;

    @FXML
    void btnaddcartOnAction(ActionEvent event) {
        try {

            if (!supplyqty.getText().isEmpty()) {
                if (ValidateField.numberCheck(supplyqty.getText())) {
                    String itemcode = cmbitemcode.getValue();
                    String itemname = lblchangingitmname.getText();
                    String category = lblchangingcategory.getText();
                    Integer quantity = Integer.valueOf(supplyqty.getText());
                    btnremove = new Button("Remove");
                    btnremove.setCursor(Cursor.HAND);

                    setRemoveBtnOnAction(btnremove); /* set action to the btnRemove */
                    if (!obList.isEmpty()) {
                        for (int i = 0; i < tblplaceLoad.getItems().size(); i++) {
                            if (colitemcode.getCellData(i).equals(itemcode)) {
                                quantity += (int) colquantity.getCellData(i);

                                obList.get(i).setQuantity(quantity);

                                tblplaceLoad.refresh();
                                return;
                            }
                        }
                    }

                    AddSupplyLoadTM tm = new AddSupplyLoadTM(itemcode, itemname, category, quantity, btnremove);

                    obList.add(tm);
                    tblplaceLoad.setItems(obList);

                    supplyqty.setText("");
                } else {
                    AlertController.errormessage("Invalid input found in item quantity field.\nMake sure to input an integer value");
                    supplyqty.setStyle("-fx-text-fill: red");
                }
            } else {
                AlertController.errormessage("Quantity field can't be empty. " +
                        "Please make sure to fill all fields the next time you try to add to the load ");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("btnAddToCart");
        }
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                int index = tblplaceLoad.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblplaceLoad.refresh();
            }
        });
    }

    @FXML
    void btnplaceorderOnAction(ActionEvent event) {
        if (!txttotalprice.getText().isEmpty()) {
            String loadid = lblloadid.getText();
            String suppid = cmbsuppid.getValue();
            String totalprice = txttotalprice.getText();

            if (ValidateField.priceCheck(totalprice)) {

                List<PlaceSupplyLoadDTO> placeSupplyLoadList = new ArrayList<>();

                for (int i = 0; i < tblplaceLoad.getItems().size(); i++) {
                    AddSupplyLoadTM addSupplyLoadTM = obList.get(i);

                    PlaceSupplyLoadDTO placeSupplyLoad = new PlaceSupplyLoadDTO(
                            addSupplyLoadTM.getItemcode(),
                            addSupplyLoadTM.getQuantity()
                    );
                    placeSupplyLoadList.add(placeSupplyLoad);
                }

                boolean isPlaced = false;
                try {
              //      isPlaced = CashierSupplyLoadModel.placeLoad(loadid, suppid, totalprice, placeSupplyLoadList);
                    isPlaced = supplyLoadBO.placeLoad(loadid, suppid, totalprice, placeSupplyLoadList);
                    if (isPlaced) {
                        AlertController.confirmmessage("Load Added Successfully");
                        generateNextLoadId();
                        cmbsuppid.setValue(null);
                        cmbitemcode.setValue(null);
                        lblchangingsuppname.setText("");
                        lblchangingitmname.setText("");
                        lblchangingcategory.setText("");
                        lblchangingqtyonhands.setText("");
                        txttotalprice.setText("");
                        tblplaceLoad.getItems().clear();
                    } else {
                        AlertController.errormessage("Supply Load Not Placed");
                    }
                } catch (Exception e) {
                }
            } else {
                AlertController.errormessage("Invalid input found in load payment field.\nMake sure to input an integer value");
                txttotalprice.setStyle("-fx-text-fill: red");
            }
        } else {
            AlertController.errormessage("Load payment field can't be empty. " +
                    "Please make sure to fill that field before you try to add the load ");
        }
    }

    @FXML
    void cmbSuppIdOnAction(ActionEvent event) {
        String supp_id = cmbsuppid.getValue();

        try {
            String name = supplyLoadBO.getSupplierName(supp_id);
            lblchangingsuppname.setText(name);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    ItemDTO item;

    @FXML
    void cmbitemcodeOnAction(ActionEvent event) {
        String itemcode = cmbitemcode.getValue();

        try {
            item = supplyLoadBO.findBy(itemcode);
            lblchangingitmname.setText(item.getName());
            lblchangingcategory.setText(item.getCategory());

            lblchangingqtyonhands.setText(String.valueOf(item.getQuantity()));
        } catch (Exception e) {

        }
    }

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
    void seeorderdetOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert addicon != null : "fx:id=\"addicon\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert adminchangingPane != null : "fx:id=\"adminchangingPane\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert balancelbl != null : "fx:id=\"balancelbl\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert btnAddSupplyLoad != null : "fx:id=\"btnAddSupplyLoad\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert btnAddToLoad != null : "fx:id=\"btnAddToLoad\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert cmbitemcode != null : "fx:id=\"cmbitemcode\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert cmbsuppid != null : "fx:id=\"cmbsuppid\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert colaction != null : "fx:id=\"colaction\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert colcategory != null : "fx:id=\"colcategory\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert colitemcode != null : "fx:id=\"colitemcode\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert colitemname != null : "fx:id=\"colitemname\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert colquantity != null : "fx:id=\"colquantity\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblbalance != null : "fx:id=\"lblbalance\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblchangingcategory != null : "fx:id=\"lblchangingcategory\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblchangingsuppname != null : "fx:id=\"lblchangingcusname\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblchangingitmname != null : "fx:id=\"lblchangingitmname\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblchangingqtyonhands != null : "fx:id=\"lblchangingqtyonhands\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblitemcategory != null : "fx:id=\"lblitemcategory\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblitemname != null : "fx:id=\"lblitemname\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblitemqtyonhand != null : "fx:id=\"lblitemqtyonhand\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblloadid != null : "fx:id=\"lblloadid\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblsuppliername != null : "fx:id=\"lblsuppliername\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblsupplydate != null : "fx:id=\"lblsupplydate\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lblsupplytime != null : "fx:id=\"lblsupplytime\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert lbltotalprice != null : "fx:id=\"lbltotalprice\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert logoutbtn1 != null : "fx:id=\"logoutbtn1\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert logoutlabel1 != null : "fx:id=\"logoutlabel1\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert seesupplydet != null : "fx:id=\"seesupplydet\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert supplyqty != null : "fx:id=\"supplyqty\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert tblplaceOrder != null : "fx:id=\"tblplaceOrder\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";
        assert txtmoremoney != null : "fx:id=\"txtmoremoney\" was not injected: check your FXML file 'addsupplyloadform.fxml'.";

        loadCustomerIds();
        loadItemCodes();
        generateNextLoadId();
        setCellValueFactory();

        TimeAndDateController timeobject = new TimeAndDateController();
        timeobject.timenow(lblsupplytime, lblsupplydate);
    }

    public void supplyqtyOnMouseClicked(MouseEvent mouseEvent) {
        supplyqty.setStyle("-fx-text-fill: black");
    }

    public void txttotalpriceOnMouseClicked(MouseEvent mouseEvent) {
        txttotalprice.setStyle("-fx-text-fill: black");
    }

    public void seesupplyloaddetOnMouseClicked(MouseEvent mouseEvent) {
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
