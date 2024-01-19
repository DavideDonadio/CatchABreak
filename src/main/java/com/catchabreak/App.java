package com.catchabreak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        stage.getIcons().add(new Image(App.class.getResourceAsStream("/icons/icon.png"))); // Setting app icon
        TrayController.setupTray(stage);

        scene = new Scene(loadFXML("primary"), 350, 200);
        //scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm()); // Non sembra funzionare con i menu items dell'icona nel tray
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}