package com.catchabreak;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private Stage stage;
    private String cssPath;

    public SceneManager(Stage stage, String cssPath){

        this.stage = stage;
        this.cssPath = cssPath;
    }

    public void switchScene(String fxmlFileName) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName + "_" + PreferencesUtil.getTheme() + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
