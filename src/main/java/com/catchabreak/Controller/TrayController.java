package com.catchabreak.Controller;

import com.catchabreak.App;
import com.dustinredmond.fxtrayicon.FXTrayIcon;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public final class TrayController{

    static FXTrayIcon icon;

    public static void setupTray(Stage stage){

        icon = new FXTrayIcon(stage, TrayController.class.getResource("/icons/icon.png"));
        icon.show();
        initializeTrayMenuItems(stage);
    }

    static void initializeTrayMenuItems(Stage stage){

        MenuItem exit = new MenuItem("Exit");
        MenuItem settings = new MenuItem("Settings");
        MenuItem stats = new MenuItem("Stats");
        MenuItem info = new MenuItem("Info");
        icon.addMenuItems(settings, stats, info, exit);

        exit.setOnAction(action -> System.exit(0));
        settings.setOnAction(action -> App.getSceneManager().switchScene("settings"));
        stats.setOnAction(action -> App.getSceneManager().switchScene("stats"));
        info.setOnAction(action -> App.getSceneManager().switchScene("info"));

    }

    public static void sendStartBreakNotification(){
        icon.showMessage("Catch a Break!", "You have been working hard, it's time to catch a break!");
    }

    public static void sendStopBreakNotification(){
        icon.showMessage("Break is over!", "It's show time, back to work!");
    }
}