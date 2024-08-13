
package com.catchabreak;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import com.catchabreak.Controller.TrayController;
import com.catchabreak.Utils.*;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static SceneManager sceneManager;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {

        stage.getIcons().add(new Image(App.class.getResourceAsStream("/icons/icon.png"))); // Setting app icon
        stage.setResizable(false);
        manageExitButtonPress(stage);
        TrayController.setupTray(stage);

        sceneManager = new SceneManager(stage);
        sceneManager.switchScene("primary");
    }

    public void manageExitButtonPress(@SuppressWarnings("exports") Stage stage) {

        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((WindowEvent event) -> {

            if (PreferencesUtil.minimizeOnExit() == true) {

                stage.hide();
                event.consume();

            } else {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}