package com.catchabreak;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.prefs.Preferences;

public class SettingsController {

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField workTextField;
    @FXML
    private TextField breakTextField;

    Preferences prefs = Preferences.userNodeForPackage(SettingsController.class);

    public void initialize() {

        workTextField.setText(Integer.toString(TimerController.getWorkTimeMinutes()));
        breakTextField.setText(Integer.toString(TimerController.getBreakTimeMinutes()));
    }

    // TODO: USER INPUT VALIDATION
    @FXML
    public void saveSettings(MouseEvent event) throws IOException {

        TimerController.setBreakTimeMinutes(Integer.parseInt(breakTextField.getText()));
        prefs.put("breakTimeSeconds", Integer.toString(TimerController.getBreakTimeSeconds()));

        TimerController.setWorkTimeMinutes(Integer.parseInt(workTextField.getText()));
        prefs.put("workTimeSeconds", Integer.toString(TimerController.getWorkTimeSeconds()));

        if(TimerController.timeLine.getStatus() == Animation.Status.STOPPED)
            TimerModel.setTimerSeconds(TimerController.getWorkTimeSeconds());
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