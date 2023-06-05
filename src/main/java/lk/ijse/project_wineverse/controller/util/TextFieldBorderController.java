package lk.ijse.project_wineverse.controller.util;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jfxtras.scene.control.LocalTimeTextField;

public class TextFieldBorderController {

    public static void txtfieldbordercolor(TextField txtfield){
        txtfield.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        txtfield.setOnMouseEntered((MouseEvent e) -> {
            txtfield.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        });
        txtfield.setOnMouseExited((MouseEvent e) -> {
            txtfield.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        });
    }

    public static void datepickerbordercolor(DatePicker datepicker){
        datepicker.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        datepicker.setOnMouseEntered((MouseEvent e) -> {
            datepicker.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        });
        datepicker.setOnMouseExited((MouseEvent e) -> {
            datepicker.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        });
    }

    public static void buttonbordercolor(Button button){
        button.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        button.setOnMouseEntered((MouseEvent e) -> {
            button.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 2 10px 10px 2;");
        });
        button.setOnMouseExited((MouseEvent e) -> {
            button.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        });
    }

    public static void localtimebordercolor(LocalTimeTextField lttf){
        lttf.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        lttf.setOnMouseEntered((MouseEvent e) -> {
            lttf.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        });
        lttf.setOnMouseExited((MouseEvent e) -> {
            lttf.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        });
    }

    public static void comboboxbordercolor(ComboBox cmb){
        cmb.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        cmb.setOnMouseEntered((MouseEvent e) -> {
            cmb.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        });
        cmb.setOnMouseExited((MouseEvent e) -> {
            cmb.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
        });
    }
}
