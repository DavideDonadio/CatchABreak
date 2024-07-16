package com.catchabreak;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    private String previousTheme;
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
        previousTheme = darkMode.isSelected() ? "dark" : "light";
    }

    // TODO: USER INPUT VALIDATION
    @FXML
    public void saveSettings(MouseEvent event) throws IOException {

        PreferencesUtil.setTheme(darkMode.isSelected() ? "dark" : "light");
        PreferencesUtil.setBreakTimeMinutes(Integer.parseInt(breakTextField.getText()));
        PreferencesUtil.setWorkTimeMinutes(Integer.parseInt(workTextField.getText()));

        // Refreshes the current scene if the user changes the theme
        if(!PreferencesUtil.getTheme().equals(previousTheme))
            refreshSettingsScene();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.getSceneManager().switchScene("primary_" + PreferencesUtil.getTheme());
    }

    @FXML
    private void refreshSettingsScene() throws IOException {
        App.getSceneManager().switchScene("settings_" + PreferencesUtil.getTheme());
    }
}