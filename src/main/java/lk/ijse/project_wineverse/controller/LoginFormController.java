package lk.ijse.project_wineverse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project_wineverse.bo.BOFactory;
import lk.ijse.project_wineverse.bo.custom.LoginBO;
import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.controller.util.AlertController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFormController {

    @FXML
    private PasswordField loginpassword;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginAncPane;

    @FXML
    private Label loginlbl;

    @FXML
    private ComboBox logincombo;

    @FXML
    private TextField loginusername;

    @FXML
    private Rectangle loadrec;

    @FXML
    private ImageView loadview;

    @FXML
    private Button loginbtn;

    @FXML
    private Label statusLabel;

    @FXML
    private ProgressBar pro;

    @FXML
    private Label showhidelbl;

    @FXML
    private TextField showpasstxt;

    LoginBO loginBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN_BO);

    @FXML
    void initialize() {
        assert loginAncPane != null : "fx:id=\"loginAncPane\" was not injected: check your FXML file 'loginform.fxml'.";
        logincombo.getItems().addAll("Admin", "Cashier");

        showhidelbl.setVisible(false);
    }

    public void loginbtnOnAction(ActionEvent actionEvent) {

        String username = loginusername.getText();
        String password = loginpassword.getText();
        String combo = String.valueOf(logincombo.getValue());

        if (username.isEmpty() && password.isEmpty() && logincombo.getSelectionModel().getSelectedIndex() == -1) {
            loginusername.setPromptText("Empty Username");
            loginusername.setStyle("-fx-prompt-text-fill: red;");
            loginpassword.setPromptText("Empty Password");
            loginpassword.setStyle("-fx-prompt-text-fill: red;");
            logincombo.setPromptText("Nothing Selected");
            logincombo.setStyle("-fx-prompt-text-fill: red;");
        } else if (username.isEmpty() && password.isEmpty() && logincombo.getSelectionModel().getSelectedIndex() != -1) {
            loginusername.setPromptText("Empty Username");
            loginusername.setStyle("-fx-prompt-text-fill: red;");
            loginpassword.setPromptText("Empty Password");
            loginpassword.setStyle("-fx-prompt-text-fill: red;");
        } else if (username.isEmpty() && logincombo.getSelectionModel().getSelectedIndex() == -1 && !password.isEmpty()) {
            loginusername.setPromptText("Empty Username");
            loginusername.setStyle("-fx-prompt-text-fill: red;");
            logincombo.setPromptText("Nothing Selected");
            logincombo.setStyle("-fx-prompt-text-fill: red;");
        } else if (password.isEmpty() && logincombo.getSelectionModel().getSelectedIndex() == -1 && !username.isEmpty()) {
            logincombo.setPromptText("Nothing Selected");
            logincombo.setStyle("-fx-prompt-text-fill: red;");
            loginpassword.setPromptText("Empty Password");
            loginpassword.setStyle("-fx-prompt-text-fill: red;");
        } else if (!username.isEmpty() && !password.isEmpty() && logincombo.getSelectionModel().getSelectedIndex() == -1) {
            logincombo.setPromptText("Nothing Selected");
            logincombo.setStyle("-fx-prompt-text-fill: red;");
        } else if (!username.isEmpty() && password.isEmpty() && logincombo.getSelectionModel().getSelectedIndex() != -1) {
            loginpassword.setPromptText("Empty Password");
            loginpassword.setStyle("-fx-prompt-text-fill: red;");
        }

        try {
         //   SignUpDTO signup = SignUpModel.findbyusername(username);
            SignUpDTO signup = loginBO.findByUsername(username);

            if (signup == null) {
                AlertController.errormessage("Wrong/Empty Username");
            }
//            if(!signup.getPassword().equals(password)){
//               AlertController.errormessage("Wrong Password");
//            }
            if (signup.getPassword().equals(password) && signup.getJob_title().equalsIgnoreCase(combo) && combo.equals("Admin")) {
                LoginMessageController.loginsuccessfulmsg();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                    loginbtn.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/admindashboardform.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("DashBoard");
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();
                }));
                timeline.play();
            } else if (signup.getPassword().equals(password) && signup.getJob_title().equalsIgnoreCase(combo) && combo.equals("Cashier")) {
                LoginMessageController.loginsuccessfulmsg();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                    loginbtn.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/cashierdashboardform.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("DashBoard");
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();
                }));
                timeline.play();
            } else {
                LoginMessageController.loginunsuccessfulmsg();
            }
        } catch (Exception e) {
            System.out.println(e);
            //AlertController.errormessage("something went wrong!");
        }
    }

    public void comboMouseClicked(MouseEvent mouseEvent) {
        logincombo.setPromptText("Admin/Cashier");
        logincombo.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void useMouseClicked(MouseEvent mouseEvent) {
        loginusername.setPromptText("USERNAME");
        loginusername.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void forgotpasswordMouseClicked(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/forgotpasswordform.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        loginAncPane.getScene().getWindow().hide();
    }

    public void loginpassMouseEntered(MouseEvent mouseEvent) {
        showhidelbl.setVisible(true);
    }

    public void loginpassMouseExited(MouseEvent mouseEvent) {
        showhidelbl.setVisible(false);
    }

    public void showhidelblOnAction(ActionEvent actionEvent) {
    }

    public void showhidelblMouseEntered(MouseEvent mouseEvent) {
        showhidelbl.setVisible(true);
        showpasstxt.setVisible(true);
        loginpassword.setVisible(false);
        showpasstxt.setText(loginpassword.getText());
    }

    public void showhidelblMouseExited(MouseEvent mouseEvent) {
    }

    public void showpasstxtMouseExited(MouseEvent mouseEvent) {
        showpasstxt.setVisible(false);
        loginpassword.setVisible(true);
    }

    public void signupMousePressed(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/signupform.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        loginAncPane.getScene().getWindow().hide();
    }

    public void passwordMouseClicked(MouseEvent mouseEvent) {
        loginpassword.setPromptText("PASSWORD");
        loginpassword.setStyle("-fx-prompt-text-fill: grey;");
    }
}


