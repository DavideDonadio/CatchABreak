package com.catchabreak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.dustinredmond.fxtrayicon.FXTrayIcon;

import java.io.IOException;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        FXTrayIcon icon = new FXTrayIcon(stage, getClass().getResource("/icons/icon.png"));
        icon.show();

        stage.getIcons().add(new Image(App.class.getResourceAsStream("/icons/icon.png")));

        scene = new Scene(loadFXML("primary"), 350, 200);
        //scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm()); // Non sembra funzionare con i menu items dell'icona nel tray
        stage.setScene(scene);
        stage.show();
    }

    public void initializeTrayMenuItems(FXTrayIcon icon){

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        MenuItem settings = new MenuItem("Settings");
        icon.addMenuItems(exit, settings);
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