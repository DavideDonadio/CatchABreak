package com.catchabreak;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField workTextField;
    @FXML
    private TextField breakTextField;
    @FXML
    private CheckBox darkMode;

    public void initialize() {

        darkMode.setSelected(PreferencesUtil.getTheme().equals("dark") ? true : false);
        workTextField.setText(Integer.toString(PreferencesUtil.getWorkTimeMinutes()));
        breakTextField.setText(Integer.toString(PreferencesUtil.getBreakTimeMinutes()));
    }

    // TODO: USER INPUT VALIDATION
    @FXML
    public void saveSettings(MouseEvent event) throws IOException {

        PreferencesUtil.setTheme(darkMode.isSelected() ? "dark" : "light");
        PreferencesUtil.setBreakTimeMinutes(Integer.parseInt(breakTextField.getText()));
        PreferencesUtil.setWorkTimeMinutes(Integer.parseInt(workTextField.getText()));

        // Updates the timer's seconds in real time after saving the settings
        if(TimerController.timeLine.getStatus() == Animation.Status.STOPPED){

            if(TimerController.inABreak)
                TimerModel.setTimerSeconds(PreferencesUtil.getBreakTimeSeconds());
            else TimerModel.setTimerSeconds(PreferencesUtil.getWorkTimeSeconds());
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.getSceneManager().switchScene("primary_" + PreferencesUtil.getTheme());
    }
}