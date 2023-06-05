package lk.ijse.project_wineverse.controller.util;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class BtnColorController {
    public static void btncolor(Button btn,AnchorPane anchorPane){
        btn.setStyle("-fx-background-color: #8c0c0c;");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            if (newAnchorPane.getId().equals("adminchangingPane")) {
                                btn.setStyle("-fx-background-color: #3C4043;");
                            } else {
                                btn.setStyle("-fx-background-color: #8c0c0c;");
                            }
                        }
                    }
                }
            }
        });

        /////////
        /////////////
    }

    public static void cashbtncolor(Button btn,AnchorPane anchorPane){
        btn.setStyle("-fx-background-color: #8c0c0c;");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            if (newAnchorPane.getId().equals("cashchangingPane")) {
                                btn.setStyle("-fx-background-color: #3C4043;");
                            } else {
                                btn.setStyle("-fx-background-color: #8c0c0c;");
                            }
                        }
                    }
                }
            }
        });
    }
}
