package com.catchabreak;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    @FXML
    private CheckBox minimizeOnExit;

    public void initialize() {

        minimizeOnExit.setSelected(PreferencesUtil.minimizeOnExit() ? true : false);
        darkMode.setSelected(PreferencesUtil.getTheme().equals("dark") ? true : false);
        workTextField.setText(Integer.toString(PreferencesUtil.getWorkTimeMinutes()));
        breakTextField.setText(Integer.toString(PreferencesUtil.getBreakTimeMinutes()));
        previousTheme = darkMode.isSelected() ? "dark" : "light";
    }
    
    @FXML
    public void saveSettings(@SuppressWarnings("exports") MouseEvent event) throws IOException {

        PreferencesUtil.setTheme(darkMode.isSelected() ? "dark" : "light");
        PreferencesUtil.setMinimizeOnExit(minimizeOnExit.isSelected() ? true : false);

        try {

            int breakTime = Integer.parseInt(breakTextField.getText());
            int workTime = Integer.parseInt(workTextField.getText());
            
            PreferencesUtil.setBreakTimeMinutes(breakTime);
            PreferencesUtil.setWorkTimeMinutes(workTime);
        } 
        catch(NumberFormatException e) {

            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("You must insert a valid number.");
            errorAlert.showAndWait();
        }

        // Refreshes the current scene if the user changes the theme
        if(!PreferencesUtil.getTheme().equals(previousTheme))
            refreshSettingsScene();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.getSceneManager().switchScene("primary");
    }

    @FXML
    private void refreshSettingsScene() throws IOException {
        App.getSceneManager().switchScene("settings");
    }
}