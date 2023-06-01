package lk.ijse.project_wineverse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project_wineverse.util.BtnColorController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CashierDashController {

    @FXML
    private AnchorPane cashDashPane;

    @FXML
    private AnchorPane cashchangingPane;

    @FXML
    private Button cashcustbtn;

    @FXML
    private Button cashdelbtn;

    @FXML
    private Button cashhomebtn;

    @FXML
    private Button cashinventbtn;

    @FXML
    private Button cashorderbtn;

    @FXML
    private Button cashpaymentbtn;

    @FXML
    private Button cashpaymentbtn1;

    @FXML
    private Label date;

    @FXML
    private ImageView logoutbtn;

    @FXML
    private Label logoutlabel;

    @FXML
    private Label time;

    @FXML
    private ImageView adminfb;

    @FXML
    private ImageView admingoogle;

    @FXML
    private ImageView dragimage;

    @FXML
    private Group drag;

    @FXML
    private Button casheventbtn;

    @FXML
    private JFXButton cashnewSupplyLoadbtn;


    public void logoutbtnMousePressed(MouseEvent mouseEvent) throws IOException {
        cashchangingPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        cashDashPane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginform.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void initialize() {
        TimeAndDateController timeobject = new TimeAndDateController();
        timeobject.timenow(time,date);

        assert cashDashPane != null : "fx:id=\"cashDashPane\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashchangingPane != null : "fx:id=\"cashchangingPane\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashcustbtn != null : "fx:id=\"cashcustbtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashdelbtn != null : "fx:id=\"cashdelbtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashhomebtn != null : "fx:id=\"cashhomebtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashinventbtn != null : "fx:id=\"cashinventbtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashorderbtn != null : "fx:id=\"cashorderbtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashpaymentbtn != null : "fx:id=\"cashpaymentbtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert cashpaymentbtn1 != null : "fx:id=\"cashpaymentbtn1\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert logoutbtn != null : "fx:id=\"logoutbtn\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert logoutlabel != null : "fx:id=\"logoutlabel\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'cashierdashboardform.fxml'.";

        admingoogle.setVisible(false);
        adminfb.setVisible(false);
    }

    public void cashhomebtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/homeform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(cashhomebtn,cashchangingPane);
    }

    public void cashinventbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventoryform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(cashinventbtn,cashchangingPane);
    }

    public void cashordersOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierplaceorderform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(cashorderbtn,cashchangingPane);
    }

    public void cashpaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierpaymentform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
    }

    public void cashdeliveryOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierdeliveryform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(cashdelbtn,cashchangingPane);
    }

    public void cashcustOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashiercustomerform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(cashcustbtn,cashchangingPane);
    }

    public void cashEventOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashiereventform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(casheventbtn,cashchangingPane);
    }

    public void cashnewSupplyLoadbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/addsupplyloadform.fxml"));
        cashchangingPane.getChildren().clear();
        cashchangingPane.getChildren().add(load);
        BtnColorController.btncolor(cashnewSupplyLoadbtn,cashchangingPane);
    }

    public void fbOnMousePressed(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Facebook"));
    }

    public void googleOnMousePressed(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));
    }

    public void dragOnMouseEntered(MouseEvent mouseEvent) {
        drag.setVisible(false);
        admingoogle.setVisible(true);
        adminfb.setVisible(true);
    }

    public void dragOnMouseExited(MouseEvent mouseEvent) {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> {
            drag.setVisible(true);
            admingoogle.setVisible(false);
            adminfb.setVisible(false);
        });
        delay.play();
    }

    public void fbOnMouseMoved(MouseEvent mouseEvent) {

    }

    InnerShadow effect = new InnerShadow();
    public void fbOnMouseEntered(MouseEvent mouseEvent) {
        effect.setColor(Color.BLUE);
        adminfb.setEffect(effect);
    }

    DropShadow glowEffect = new DropShadow();
    public void fbOnMouseExited(MouseEvent mouseEvent) {
        glowEffect.setRadius(10);
        Color paint = new Color(0.1109, 0.1109, 0.5526, 1.0);
        glowEffect.setColor(paint);
        adminfb.setEffect(glowEffect);
    }

    public void googleOnMouseEntered(MouseEvent mouseEvent) {
        admingoogle.setEffect(effect);
    }

    public void googleOnMouseExited(MouseEvent mouseEvent) {
        glowEffect.setRadius(10);
        Color paint = new Color(0.1109, 0.1109, 0.5526, 1.0);
        glowEffect.setColor(paint);
        admingoogle.setEffect(glowEffect);
    }

}
