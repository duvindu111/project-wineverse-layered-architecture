package lk.ijse.project_wineverse.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.view.tdm.ItemTM;
import lk.ijse.project_wineverse.model.ItemModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.TextFieldBorderController;
import lk.ijse.project_wineverse.util.ValidateField;

public class InventoryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane inventoryPane;

    @FXML
    private Label date;

    @FXML
    private Button homebtn;

    @FXML
    private Button homebtn1;

    @FXML
    private Button homebtn11;

    @FXML
    private Button homebtn111;

    @FXML
    private Button homebtn1111;

    @FXML
    private Button homebtn11112;

    @FXML
    private ImageView logoutbtn;

    @FXML
    private Label logoutlabel;

    @FXML
    private Label time;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtcategory;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtquantity;

    @FXML
    private TextField txtunitprice;

    @FXML
    private TableColumn<?, ?> colcategory;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colquantity;

    @FXML
    private TableColumn<ItemTM, Double> colunitprice;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private Label wrongitemcodeformat;

    @FXML
    private Label lblinvalidunitprice;

    @FXML
    private Label lblinvalidqty;

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
//        TimeAndDateController timeobject = new TimeAndDateController();
//        timeobject.timenow(time,date);

        wrongitemcodeformat.setVisible(false);

        assert inventoryPane != null : "fx:id=\"adminInventoryPane\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert homebtn != null : "fx:id=\"homebtn\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert homebtn1 != null : "fx:id=\"homebtn1\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert homebtn11 != null : "fx:id=\"homebtn11\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert homebtn111 != null : "fx:id=\"homebtn111\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert homebtn1111 != null : "fx:id=\"homebtn1111\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert homebtn11112 != null : "fx:id=\"homebtn11112\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert logoutbtn != null : "fx:id=\"logoutbtn\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert logoutlabel != null : "fx:id=\"logoutlabel\" was not injected: check your FXML file 'inventoryform.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'inventoryform.fxml'.";

        setCellValueFactory();
        getAll();

        txtid.setText("");
        txtname.setText("");
        txtunitprice.setText("");
        txtcategory.setText("");
        txtquantity.setText("");

        TextFieldBorderController.txtfieldbordercolor(txtid);
        TextFieldBorderController.txtfieldbordercolor(txtname);
        TextFieldBorderController.txtfieldbordercolor(txtunitprice);
        TextFieldBorderController.txtfieldbordercolor(txtcategory);
        TextFieldBorderController.txtfieldbordercolor(txtquantity);

        lblinvalidunitprice.setVisible(false);
        lblinvalidqty.setVisible(false);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            String id = txtid.getText();
            String name = txtname.getText();
            String unitprice = txtunitprice.getText();
            String category = txtcategory.getText();
            String qty = txtquantity.getText();

            if(id.isEmpty() || name.isEmpty() || String.valueOf(unitprice).isEmpty() || String.valueOf(qty).isEmpty()) {
                AlertController.errormessage("Item details not saved.\nPlease make sure to fill all the required fields ");
            }else{
                if(ValidateField.itemIdCheck(id)) {
                    if(ValidateField.priceCheck(unitprice)) {
                        if(ValidateField.numberCheck(qty)) {
                            ItemDTO item = new ItemDTO(id, name, unitprice, category, qty);

                            boolean isSaved = ItemModel.save(item);
                            if (isSaved) {
                                AlertController.confirmmessage("Item Added Successfully");
                                txtid.setText("");
                                txtname.setText("");
                                txtunitprice.setText("");
                                txtcategory.setText("");
                                txtquantity.setText("");

                                getAll();
                            } else {
                                AlertController.errormessage("Something went wrong");
                            }
                        }else{
                            lblinvalidqty.setVisible(true);
                        }
                    }else{
                        lblinvalidunitprice.setVisible(true);
                    }
                }else{
                    wrongitemcodeformat.setVisible(true);
                    txtid.setStyle("-fx-text-fill: red");
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            AlertController.errormessage("Duplicate Item ID");
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something went wrong");
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("code"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ////////////////////
        colunitprice.setCellFactory(new Callback<TableColumn<ItemTM, Double>, TableCell<ItemTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<ItemTM, Double> call(TableColumn<ItemTM, Double> column) {
                return new TableCell<ItemTM, Double>() {
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

        //////////////////////
    }

    private void getAll(){
        ObservableList<ItemTM> obList = null;
        try {
            obList = ItemModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblItem.setItems(obList);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateMousePressed(MouseEvent mouseEvent) {
        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this items' details?");
        if(result==true) {

            try {
                String id = txtid.getText();
                String name = txtname.getText();
                String unitprice = txtunitprice.getText();
                String category = txtcategory.getText();
                String quantity = txtquantity.getText();

                if(id.isEmpty() || name.isEmpty() || String.valueOf(unitprice).isEmpty() || String.valueOf(quantity).isEmpty()) {
                    AlertController.errormessage("Item details not saved.\nPlease make sure to fill all the required fields ");
                }else{
                    ItemDTO item = new ItemDTO(id, name, unitprice, category, quantity);

                    boolean isUpdated = ItemModel.update(item);
                    if (isUpdated) {
                        AlertController.confirmmessage("Item Details Updated");
                        txtid.setText("");
                        txtname.setText("");
                        txtunitprice.setText("");
                        txtcategory.setText("");
                        txtquantity.setText("");

                        getAll();
                    }
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e);
                AlertController.errormessage(e.getMessage());
            }catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void txtSearchKeyPressed(KeyEvent keyEvent) {
    }

    public void txtSearchKeyTyped(KeyEvent keyEvent) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<ItemTM> obList = ItemModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<ItemTM> filteredData = obList.filtered(new Predicate<ItemTM>(){
                @Override
                public boolean test(ItemTM itemTM) {
                    return String.valueOf(itemTM.getCode()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblItem.setItems(filteredData);
        } else {
            tblItem.setItems(obList);
        }
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        txtid.setText(null);
        txtname.setText(null);
        txtunitprice.setText(null);
        txtcategory.setText(null);
        txtquantity.setText(null);

        try {
            ItemDTO item = ItemModel.findById(id);
            if(item!=null) {
                txtid.setText(item.getCode());
                txtname.setText(item.getName());
                txtunitprice.setText(String.valueOf(item.getUnitprice()));
                txtcategory.setText(item.getCategory());
                txtquantity.setText(String.valueOf(item.getQuantity()));
                txtid.setDisable(true);
                txtSearch.setText("");
            }else{
                AlertController.errormessage("Item ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this item?");
        if(result==true) {

            try {
                boolean isDeleted = ItemModel.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Item Deleted Successfully");
                    txtid.setText(null);
                    txtname.setText(null);
                    txtunitprice.setText(null);
                    txtcategory.setText(null);
                    txtquantity.setText(null);
                    getAll();
                }
                else{
                    AlertController.errormessage("No item code selected");
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblItem.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<ItemTM, ?>> columns = tblItem.getColumns();

        txtid.setText(columns.get(0).getCellData(row).toString());
        txtname.setText(columns.get(1).getCellData(row).toString());
        txtunitprice.setText(columns.get(2).getCellData(row).toString());
        txtcategory.setText(columns.get(3).getCellData(row).toString());
        txtquantity.setText(columns.get(4).getCellData(row).toString());
        txtid.setDisable(true);
        txtSearch.setText("");
    }

    public void txtidOnMouseClicked(MouseEvent mouseEvent) {
        wrongitemcodeformat.setVisible(false);
        txtid.setStyle("-fx-text-fill: black");

    }

    public void clearbtnOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText("");
        txtname.setText("");
        txtunitprice.setText("");
        txtcategory.setText("");
        txtquantity.setText("");
        txtid.setDisable(false);
    }

    public void clearlblOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText("");
        txtname.setText("");
        txtunitprice.setText("");
        txtcategory.setText("");
        txtquantity.setText("");
        txtid.setDisable(false);
    }

    public void btnShowAllOnAction(ActionEvent actionEvent) {
        txtSearch.setText("");
        getAll();
    }

    public void txtunitpriceOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidunitprice.setVisible(false);
    }

    public void txtquantityOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidqty.setVisible(false);
    }
}
