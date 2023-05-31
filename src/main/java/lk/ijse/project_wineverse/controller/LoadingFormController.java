package lk.ijse.project_wineverse.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadingFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ProgressBar proBar;

    @FXML
    private AnchorPane loadingAncPane;

    @FXML
    private ImageView loadingview;

    public void initialize() {
        assert loadingAncPane != null : "fx:id=\"loadingAncPane\" was not injected: check your FXML file 'loadingform.fxml'.";
        assert loadingview != null : "fx:id=\"loadingview\" was not injected: check your FXML file 'loadingform.fxml'.";
        assert proBar != null : "fx:id=\"proBar\" was not injected: check your FXML file 'loadingform.fxml'.";

        new ShowSplashScreen().start();
    }

    class ShowSplashScreen extends Thread {
        public void run() {
            try {
                for (int i = 0; i <= 10; i++) {
                    double x = i * (0.1);
                    proBar.setProgress(x);


                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_wineverse.view/loginform.fxml"));
                        stage.getIcons().add(new Image("lk.ijse.project_wineverse.assets/tick.png"));
                        stage.setTitle("THE WINE ATTIC");
                    } catch (IOException ex) {
                        Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    loadingAncPane.getScene().getWindow().hide();
                });
            } catch (Exception ex) {
                Logger.getLogger(LoadingFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
