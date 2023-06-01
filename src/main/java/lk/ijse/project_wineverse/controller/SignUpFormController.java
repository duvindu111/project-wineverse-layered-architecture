package lk.ijse.project_wineverse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.model.SignUpModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.ValidateField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label alreadyhaveaccount;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private Button createbtn;

    @FXML
    private Label createlabel;

    @FXML
    private Label forgotpass;

    @FXML
    private Label loginhype;

    @FXML
    private Label loginlbl;

    @FXML
    private Rectangle logmainrectangle;

    @FXML
    private PasswordField signuppassword;

    @FXML
    private TextField signupusername;

    @FXML
    private TextField signupemail;

    @FXML
    private ComboBox<String> signupjobtitle;

    @FXML
    private AnchorPane signupAncPane;

    @FXML
    private Tooltip passtooltip;

    @FXML
    private Label passlabel;

    @FXML
    void loginbtnOnAction(ActionEvent event) {

    }

    @FXML
    void useMouseClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/assets/fonts/Offside.ttf"), 11);
        createlabel.setFont(customFont);

        assert alreadyhaveaccount != null : "fx:id=\"alreadyhaveaccount\" was not injected: check your FXML file 'signupform.fxml'.";
        assert confirmpassword != null : "fx:id=\"confirmpassword\" was not injected: check your FXML file 'signupform.fxml'.";
        assert createbtn != null : "fx:id=\"createbtn\" was not injected: check your FXML file 'signupform.fxml'.";
        assert forgotpass != null : "fx:id=\"forgotpass\" was not injected: check your FXML file 'signupform.fxml'.";
        assert loginhype != null : "fx:id=\"loginhype\" was not injected: check your FXML file 'signupform.fxml'.";
        assert loginlbl != null : "fx:id=\"loginlbl\" was not injected: check your FXML file 'signupform.fxml'.";
        assert logmainrectangle != null : "fx:id=\"logmainrectangle\" was not injected: check your FXML file 'signupform.fxml'.";
        assert signuppassword != null : "fx:id=\"signuppassword\" was not injected: check your FXML file 'signupform.fxml'.";
        assert signupusername != null : "fx:id=\"signupusername\" was not injected: check your FXML file 'signupform.fxml'.";

        createbtn.setDisable(true);
        passlabel.setVisible(false);

        signupjobtitle.getItems().addAll("Admin", "Cashier");

    }

    public void createbtnOnAction(ActionEvent actionEvent) {
        String username = signupusername.getText();
        String password = signuppassword.getText();
        String job_title = signupjobtitle.getValue();
        String email = signupemail.getText();

        if (!username.isEmpty() && !email.isEmpty() && !job_title.isEmpty()) {
            if (ValidateField.emailCheck(email)) {
                SignUpDTO signup = new SignUpDTO(username, password, job_title, email);
                try {
                    if (signuppassword.getText().equals(confirmpassword.getText())) {
                        boolean isSaved = SignUpModel.save(signup);
                        if (isSaved) {
                            AlertController.confirmmessage("Account Created");

                            ////////
                            Stage stage = new Stage();
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            signupAncPane.getScene().getWindow().hide();
                            ///////

                        }
                    } else {
                        AlertController.errormessage("Passwords doesn't match");
                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println(e);
                    AlertController.errormessage(e.getMessage());
                } catch (SQLException e) {
                    System.out.println(e);
                    AlertController.errormessage("Something went wrong");
                }
            } else {
                AlertController.errormessage("Wrong email format.please check the email field before proceeding");
            }
        } else {
            AlertController.errormessage("Please make sure to fill all the fields before you proceed");
        }
    }

    public void loginhypeMousePressed(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/loginform.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        signupAncPane.getScene().getWindow().hide();
    }

    public void signuppasswordOnKeyTyped(KeyEvent keyEvent) {
        String password = signuppassword.getText();

        // Check if the password is valid
        List<String> messages = ValidateField.getValidationMessages(password);
        if (messages.isEmpty()) {
            passlabel.setStyle("-fx-text-fill: #27cb27; -fx-background-color: black; -fx-background-radius: 10; -fx-font-family: Offside; -fx-padding: 10");
            passlabel.setText("strong valid password");
            createbtn.setDisable(false);
        } else {
            String message = String.join(", ", messages);
            passlabel.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-background-radius: 10; -fx-font-family: Offside; -fx-padding: 10");
            passlabel.setText(message);
            createbtn.setDisable(true);
        }
        passlabel.setVisible(true);
    }

    public void signuppasswordOnMouseEntered(MouseEvent mouseEvent) {
        passlabel.setVisible(true);
    }

    public void signuppasswordOnMouseExited(MouseEvent mouseEvent) {
        //passlabel.setVisible(false);
    }


}
