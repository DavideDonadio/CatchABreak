package com.catchabreak;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Button backButton = new Button();

    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage)backButton.getScene().getWindow();
        stage.setScene(scene);
    }
}