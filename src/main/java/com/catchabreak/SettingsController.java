package com.catchabreak;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Button backButton;
    @FXML
    private TextField workTextField;
    @FXML
    private TextField breakTextField;
    

    public void initialize() {

        workTextField.setText(Integer.toString(TimerController.getWorkTimeMinutes()));
        breakTextField.setText(Integer.toString(TimerController.getBreakTimeMinutes()));

        workTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            TimerController.setWorkTime(Integer.valueOf(newValue));
        });

        breakTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            
            TimerController.setBreakTime(Integer.valueOf(newValue));
        });
    }


    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage)backButton.getScene().getWindow();
        stage.setScene(scene);
    }


}