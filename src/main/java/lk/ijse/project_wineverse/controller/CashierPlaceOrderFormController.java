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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_wineverse.db.DBConnection;
import lk.ijse.project_wineverse.dto.ItemDTO;
import lk.ijse.project_wineverse.dto.PlaceOrderDTO;
import lk.ijse.project_wineverse.dto.tm.PlaceOrderTM;
import lk.ijse.project_wineverse.model.CashierOrderModel;
import lk.ijse.project_wineverse.model.CustomerModel;
import lk.ijse.project_wineverse.model.ItemModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.EmailAttachment;
import lk.ijse.project_wineverse.util.ValidateField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

public class CashierPlaceOrderFormController {

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private ComboBox<String> cmbcustid;

    @FXML
    private ComboBox<String> cmbitemcode;

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
    private TableColumn<?, ?> colquantity_unitprice;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private Label lblchangingcategory;

    @FXML
    private Label lblchangingcusname;

    @FXML
    private Label lblchangingitmname;

    @FXML
    private Label lblchangingqtyonhands;

    @FXML
    private Label lblchangingunitprice;

    @FXML
    private Label lblcustomername;

    @FXML
    private Label lblitemcategory;

    @FXML
    private Label lblitemname;

    @FXML
    private Label lblitemqtyonhand;

    @FXML
    private Label lblitmunitprice;

    @FXML
    private Label lblorderdate;

    @FXML
    private Label lblorderid;

    @FXML
    private Label lblordertime;

    @FXML
    private Label lbltotalpay;

    @FXML
    private ImageView logoutbtn1;

    @FXML
    private Label logoutlabel1;

    public RadioButton getRadiodelivery() {
        return radiodelivery;
    }

    @FXML
    private RadioButton radiodelivery;

    @FXML
    private TableView<PlaceOrderTM> tblplaceOrder;

    @FXML
    private TextField orderqty;

    @FXML
    private Label balancelbl;

    @FXML
    private TextField txtpaidamount;

    @FXML
    private Label lblmoreneeded;

    @FXML
    private Label txtmoremoney;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private ImageView warningicon;

    @FXML
    void initialize() {
        TimeAndDateController timeobject = new TimeAndDateController();
        timeobject.timenow(lblordertime, lblorderdate);

        setCellValueFactory();
        generateNextOrderId();
        loadCustomerIds();
        loadItemCodes();

        lblmoreneeded.setVisible(false);
        btnPlaceOrder.setDisable(true);
    }

    private void setCellValueFactory() {
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colitemname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colquantity_unitprice.setCellValueFactory(new PropertyValueFactory<>("total"));
        colaction.setCellValueFactory(new PropertyValueFactory<>("removebtn"));
    }

    private void generateNextOrderId() {
        try {
            String id = CashierOrderModel.getNextOrderId();
            lblorderid.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = CustomerModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbcustid.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = ItemModel.loadCodes();

            for (String code : codes) {
                obList.add(code);
            }
            cmbitemcode.setItems(obList);
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

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

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        adminchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.project_wineverse.view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void cmbcustidOnAction(ActionEvent actionEvent) {
        String cust_id = cmbcustid.getValue();

        try {
            String name = CustomerModel.getCustName(cust_id);
            lblchangingcusname.setText(name);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    public void btnDeleteMousePressed(MouseEvent mouseEvent) {
    }

    ItemDTO item;

    public void cmbitemcodeOnAction(ActionEvent actionEvent) {
        String itemcode = cmbitemcode.getValue();

        try {
            item = ItemModel.findById(itemcode);
            lblchangingitmname.setText(item.getName());
            lblchangingunitprice.setText(String.valueOf(item.getUnitprice()));
            lblchangingcategory.setText(item.getCategory());

            if (Integer.parseInt(item.getQuantity()) > 0) {
                lblchangingqtyonhands.setText(String.valueOf(item.getQuantity()));
            } else {
                lblchangingqtyonhands.setText("Out Of Stock");
                AlertController.errormessage("item " + item.getName() + " out of stock");
            }
        } catch (Exception e) {

        }
    }

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    public void btnaddcartOnAction(ActionEvent actionEvent) {
        String itemcode = cmbitemcode.getValue();
        String itemname = lblchangingitmname.getText();
        String unitprice = lblchangingunitprice.getText();
        String category = lblchangingcategory.getText();
        Integer quantity = 0;
        try {
            quantity = Integer.valueOf(orderqty.getText());
        }catch (NumberFormatException e){
            System.out.println(e);
        }
        Double total = Double.parseDouble(lblchangingunitprice.getText()) * quantity;
        Button btnremove = new Button("Remove");
        btnremove.setCursor(Cursor.HAND);

        if (ValidateField.numberCheck(String.valueOf(orderqty.getText()))) {
            ///////////////////////
            if (quantity > Integer.parseInt(lblchangingqtyonhands.getText())) {
                AlertController.errormessage("Item " + itemname + " out of stock or not enough stock");
            } else {
                setRemoveBtnOnAction(btnremove); /* set action to the btnRemove */

                if (!obList.isEmpty()) {
                    int newval= Integer.parseInt(lblchangingqtyonhands.getText())-Integer.parseInt(orderqty.getText());
                    lblchangingqtyonhands.setText(String.valueOf(newval));
                    orderqty.setText("");////////////////
                    for (int i = 0; i < tblplaceOrder.getItems().size(); i++) {
                        if (colitemcode.getCellData(i).equals(itemcode)) {
                            quantity += (int) colquantity.getCellData(i);
                            total = quantity * Double.parseDouble(unitprice);

                            obList.get(i).setQuantity(quantity);
                            obList.get(i).setTotal(total);

                            tblplaceOrder.refresh();
                            calculateNetTotal();
                            return;
                        }
                    }
                }else{
                    int newval= Integer.parseInt(lblchangingqtyonhands.getText())-Integer.parseInt(orderqty.getText());
                    lblchangingqtyonhands.setText(String.valueOf(newval));
                }

                PlaceOrderTM tm = new PlaceOrderTM(itemcode, itemname, Double.parseDouble(unitprice), category, quantity, total, btnremove);

                obList.add(tm);
                tblplaceOrder.setItems(obList);
                calculateNetTotal();
                orderqty.setText("");
            }
            /////////////////////
        } else {
            AlertController.errormessage("Wrong input format for item quantity field");
        }
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                int index = tblplaceOrder.getSelectionModel().getSelectedIndex();

                TablePosition pos = tblplaceOrder.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                ObservableList<TableColumn<PlaceOrderTM, ?>> columns = tblplaceOrder.getColumns();

                String itemName = String.valueOf(columns.get(1).getCellData(row));
                int newval = Integer.parseInt(lblchangingqtyonhands.getText()) + Integer.parseInt(columns.get(4).getCellData(row).toString());
                if(itemName.equals(lblchangingitmname.getText())) {
                    lblchangingqtyonhands.setText(String.valueOf(newval));
                }

                obList.remove(index);
                tblplaceOrder.refresh();
                calculateNetTotal();
            }

        });
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblplaceOrder.getItems().size(); i++) {
            double total = (double) colquantity_unitprice.getCellData(i);
            netTotal += total;
        }
        lbltotalpay.setText(String.valueOf(netTotal));
    }

    public void btnplaceorderOnAction(ActionEvent actionEvent) throws JRException, MessagingException {
        String orderid = lblorderid.getText();
        String custid = cmbcustid.getValue();
        Boolean delivery = radiodelivery.isSelected();
        String ordpay = lbltotalpay.getText();

        List<PlaceOrderDTO> placeOrderList = new ArrayList<>();

        for (int i = 0; i < tblplaceOrder.getItems().size(); i++) {
            PlaceOrderTM placeOrderTM = obList.get(i);

            PlaceOrderDTO placeOrder = new PlaceOrderDTO(
                    placeOrderTM.getItemcode(),
                    placeOrderTM.getQuantity()
            );
            placeOrderList.add(placeOrder);
        }

        boolean isPlaced = false;
        if (ValidateField.priceCheck(ordpay)) {
            try {
                isPlaced = CashierOrderModel.placeOrder(orderid, custid, delivery, ordpay, placeOrderList);
                if (isPlaced) {
                    AlertController.confirmmessage("Order Placed");
                    String custId = cmbcustid.getValue();
                    String printcash = txtpaidamount.getText();
                    String balance = balancelbl.getText();
                    generateNextOrderId();
                    cmbcustid.setValue(null);
                    cmbitemcode.setValue(null);
                    lblchangingcusname.setText("");
                    lblchangingitmname.setText("");
                    lblchangingunitprice.setText("");
                    lblchangingcategory.setText("");
                    lblchangingqtyonhands.setText("");
                    lbltotalpay.setText("0/=");
                    radiodelivery.setSelected(false);
                    tblplaceOrder.getItems().clear();
                    txtpaidamount.setText("");
                    balancelbl.setText("");
                    lblmoreneeded.setVisible(false);
                    txtmoremoney.setText("");

                    btnPlaceOrder.setDisable(true);

                    boolean result1 = AlertController.okconfirmmessage("Do you want the bill ?");
                    if (result1) {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("param1", printcash);
                        parameters.put("param2", balance);

                        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.project_wineverse.reports/placeorder.jrxml");
                        try {
                            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
                            JasperViewer.viewReport(jasperPrint, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    boolean result2 = AlertController.okconfirmmessage("do you want to send the bill to the customer via email?");
                    if (result2) {
                        String email = CustomerModel.getEmailByCustID(custId);
                        try {
                            System.out.println(email);
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("param1", printcash);
                            parameters.put("param2", balance);
                            InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.project_wineverse.reports/placeorder.jrxml");
                            String outputFilePath = "C:/Users/Owner/OneDrive/Documents/IJSE/Assignments/project-wineverse/src/main/resources/lk.ijse.project_wineverse.jasperpdfs/output.pdf";
                            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
                            //JasperViewer.viewReport(jasperPrint, false);
                            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilePath);
                            EmailAttachment.emailAttachmentSend("duvinduthathsara@gmail.com", "Order Bill (The Wine Attic)", "RECEIPT", outputFilePath);
                            System.out.println("EmailSent");
                        } catch (Exception e) {
                            AlertController.errormessage("Customer Email Not Found");
                        }

                    }

                } else {
                    AlertController.errormessage("Order Not Placed");
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("SQL Error");
            }
        } else {
            AlertController.errormessage("Wrong input for paid amount field");
        }
    }

    public void addiconOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk.ijse.project_wineverse.view/newcustomerform.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();

    }

    public void txtpaidamountKeyTyped(KeyEvent keyEvent) {
        if (!txtpaidamount.getText().isEmpty()) {
            if (ValidateField.priceCheck(txtpaidamount.getText())) {
                double balance = Double.parseDouble(txtpaidamount.getText()) - Double.parseDouble(lbltotalpay.getText());
                if (balance >= 0) {
                    txtpaidamount.setStyle("-fx-text-fill: black");
                    balancelbl.setText(String.valueOf(balance));
                    lblmoreneeded.setVisible(false);
                    txtmoremoney.setText("");
                    btnPlaceOrder.setDisable(false);
                    warningicon.setVisible(false);
                    balancelbl.setVisible(true);
                } else if (balance < 0) {
                    txtpaidamount.setStyle("-fx-text-fill: black");
                    btnPlaceOrder.setDisable(true);
                    double positbalance = Math.abs(balance);
                    lblmoreneeded.setVisible(true);
                    txtmoremoney.setText(positbalance + "/=");
                    warningicon.setVisible(true);
                    balancelbl.setVisible(false);
                }
            } else {
                btnPlaceOrder.setDisable(true);
                warningicon.setVisible(true);
                txtpaidamount.setStyle("-fx-text-fill: red");
                balancelbl.setVisible(false);
                lblmoreneeded.setVisible(false);
                txtmoremoney.setText("");
            }
        }
    }

    public void radiodeliveryOnAction(ActionEvent actionEvent) {
        if (radiodelivery.isSelected()) {
            Stage stage = new Stage();
            stage.resizableProperty().setValue(false);
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk.ijse.project_wineverse.view/newdeliveryform.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.centerOnScreen();
            stage.show();
        }
    }

    public void seeorderdetOnMouseClicked(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk.ijse.project_wineverse.view/orderdetailform.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }
}
