package lk.ijse.project_wineverse.controller;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
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
import lk.ijse.project_wineverse.util.LogOutController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminDashPane;

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
    private AnchorPane adminchangingPane;

    @FXML
    private Label time;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private ImageView adminfb;

    @FXML
    private ImageView admingoogle;

    @FXML
    private ImageView dragimage;

    @FXML
    private Group drag;

    @FXML
    private AnchorPane adminBtnPane;

    @FXML
    private Button adminhomebtn;

    @FXML
    private Button admininventbtn;

    @FXML
    private Button adminorderbtn;

    @FXML
    private Button adminreportbtn;

    @FXML
    private Button adminsalarybtn;

    @FXML
    private Button adminsupplierbtn;

    @FXML
    private Button adminemployeebtn;

    public void logoutbtnMousePressed(MouseEvent mouseEvent) throws IOException {
        LogOutController.logout(adminDashPane);
    }

    public void logoutlabelMousePressed(MouseEvent mouseEvent) throws IOException {
        LogOutController.logout(adminDashPane);
    }

    @FXML
    void initialize() {

        TimeAndDateController timeobject = new TimeAndDateController();
        timeobject.timenow(time,date);

        assert adminDashPane != null : "fx:id=\"adminDashPane\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert homebtn != null : "fx:id=\"homebtn\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert homebtn1 != null : "fx:id=\"homebtn1\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert homebtn11 != null : "fx:id=\"homebtn11\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert homebtn111 != null : "fx:id=\"homebtn111\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert homebtn1111 != null : "fx:id=\"homebtn1111\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert homebtn11112 != null : "fx:id=\"homebtn11112\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert logoutbtn != null : "fx:id=\"logoutbtn\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert logoutlabel != null : "fx:id=\"logoutlabel\" was not injected: check your FXML file 'admindashboardform.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'admindashboardform.fxml'.";

        admingoogle.setVisible(false);
        adminfb.setVisible(false);

        ////////////////////////animation for the fb and google icon
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), adminfb);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1), admingoogle);
        scaleTransition1.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition1.setAutoReverse(true);
        scaleTransition1.setFromX(1);
        scaleTransition1.setFromY(1);
        scaleTransition1.setToX(1.1);
        scaleTransition1.setToY(1.1);
        scaleTransition1.play();
        ////////////////////////
    }

    public void homebtnMouseEntered(MouseEvent mouseEvent) {

    }

    public void homebtnMouseExited(MouseEvent mouseEvent) {

    }

    public void adminhomebtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/homeform.fxml"));
        adminchangingPane.getChildren().clear();
        adminchangingPane.getChildren().add(load);
        BtnColorController.btncolor(adminhomebtn,adminchangingPane);
    }

    public void admininventbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventoryform.fxml"));
        adminchangingPane.getChildren().clear();
        adminchangingPane.getChildren().add(load);
        BtnColorController.btncolor(admininventbtn,adminchangingPane);
    }

    public void adminordersbtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/orderdetailform.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }

    public void adminemployeesbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminemployeeform.fxml"));
        adminchangingPane.getChildren().clear();
        adminchangingPane.getChildren().add(load);
        BtnColorController.btncolor(adminemployeebtn,adminchangingPane);
    }

    public void adminReportsOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminreportsform.fxml"));
        adminchangingPane.getChildren().clear();
        adminchangingPane.getChildren().add(load);
        BtnColorController.btncolor(adminreportbtn,adminchangingPane);
    }

    public void adminsuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminsuppliersform.fxml"));
        adminchangingPane.getChildren().clear();
        adminchangingPane.getChildren().add(load);
        BtnColorController.btncolor(adminsupplierbtn,adminchangingPane);
    }

    public void adminSalaryOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminsalaryform.fxml"));
        adminchangingPane.getChildren().clear();
        adminchangingPane.getChildren().add(load);
        BtnColorController.btncolor(adminsalarybtn,adminchangingPane);
    }

    public void dragOnMouseEntered(MouseEvent mouseEvent) {
        drag.setVisible(false);
        admingoogle.setVisible(true);
        adminfb.setVisible(true);
    }

    public void dragOnMouseExited(MouseEvent mouseEvent) throws InterruptedException {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> {
            drag.setVisible(true);
            admingoogle.setVisible(false);
            adminfb.setVisible(false);
        });
        delay.play();
    }

    public void googleOnMousePressed(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));
    }

    public void fbOnMousePressed(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Facebook"));
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

