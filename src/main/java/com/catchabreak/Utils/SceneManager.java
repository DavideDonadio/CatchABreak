package com.catchabreak.Utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private Stage stage;

    public SceneManager(@SuppressWarnings("exports") Stage stage){

        this.stage = stage;
    }

    public void switchScene(String fxmlFileName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/catchabreak/" + fxmlFileName + "_" + PreferencesUtil.getTheme() + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
