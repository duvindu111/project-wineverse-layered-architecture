package lk.ijse.project_wineverse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project_wineverse.dto.SignUpDTO;
import lk.ijse.project_wineverse.model.SignUpModel;
import lk.ijse.project_wineverse.util.AlertController;
import lk.ijse.project_wineverse.util.ValidateField;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForgotPasswordFormController {

    @FXML
    private TextField recemailtxt;

    @FXML
    private Group recoverygroup;

    @FXML
    private Group renewgroup;

    @FXML
    private Group enterotpgroup;

    @FXML
    private TextField otptext;

    @FXML
    private Button submitbtn;

    @FXML
    private AnchorPane forgotpassAncPane;

    @FXML
    private Label timer;

    @FXML
    private Label resendlbl;

    @FXML
    private TextField recusername;

    @FXML
    private PasswordField passfield;

    @FXML
    private PasswordField conpassfield;

    private Timeline timeline;
    private SimpleIntegerProperty timeSeconds = new SimpleIntegerProperty();
    private final int START_TIME = 30;

    Random rand = new Random();

    public void timermethod(Label label){
        resendlbl.setVisible(false);
        timeSeconds.set(START_TIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME+1),
                new KeyValue(timeSeconds, 0)));
        timeline.setOnFinished(event -> {
            timer.setVisible(false);
            resendlbl.setVisible(true);
        });
        timeline.playFromStart();
        label.textProperty().bind(timeSeconds.asString());
    }

    String email;
    int randomnum;
    public void sendotpbtnMouseClicked(MouseEvent mouseEvent) throws MessagingException, SQLException {

        if(ValidateField.emailCheck(recemailtxt.getText())) {

            SignUpDTO signup = SignUpModel.findbyusername(recusername.getText());
                if (recemailtxt.getText().equals(signup.getEmail())) {

                    ////////////////////////////
                    timermethod(timer);
                    ///////////////////////////

                    email = recemailtxt.getText();
                    recoverygroup.setVisible(false);
                    enterotpgroup.setVisible(true);

                    randomnum = rand.nextInt(9000);
                    randomnum += 1000;

                    try {
                        OTPEmailController.sendEmail(email, "Test Email", randomnum + "");
                        System.out.println("Email sent successfully.");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }else{
                    AlertController.errormessage("Invalid Email Address");
                }
        }else{
            AlertController.errormessage("Invalid Email Format");
        }
    }

    public void submitbtnOnAction(ActionEvent actionEvent) {
        if(otptext.getText().equals(Integer. toString(randomnum))) {
            otptext.setDisable(true);
            submitbtn.setDisable(true);
            renewgroup.setVisible(true);
        }else{
            AlertController.errormessage("Wrong OTP");
        }
    }

    public void submitpassbtnOnAction(ActionEvent actionEvent) {
        if(passfield.getText().equals(conpassfield.getText())) {
            String username = recusername.getText();
            String newpassword = passfield.getText();

            try {
                boolean isUpdated = SignUpModel.update(username, newpassword);
                if (isUpdated) {
                    AlertController.confirmmessage("Password Changed");
                }
            } catch (SQLException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }
        }else{
            AlertController.errormessage("Passwords doesn't match");
        }
    }

    public void backOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_wineverse.view/loginform.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        forgotpassAncPane.getScene().getWindow().hide();
    }

    public void resendlblOnMousePressed(MouseEvent mouseEvent) {
        timermethod(timer);
        timer.setVisible(true);
        randomnum = rand.nextInt(9000);
        randomnum += 1000;
        try {
            OTPEmailController.sendEmail(email, "Test Email", randomnum + "");
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
