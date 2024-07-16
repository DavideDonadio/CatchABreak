package com.catchabreak;

import com.dustinredmond.fxtrayicon.FXTrayIcon;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public final class TrayController{

    static FXTrayIcon icon;

    static void setupTray(Stage stage){

        icon = new FXTrayIcon(stage, TrayController.class.getResource("/icons/icon.png"));
        icon.show();
        initializeTrayMenuItems();
    }

    static void initializeTrayMenuItems(){

        MenuItem exit = new MenuItem("Exit");
        MenuItem settings = new MenuItem("Settings");
        MenuItem stats = new MenuItem("Stats");
        icon.addMenuItems(settings, stats, exit);

        exit.setOnAction(action -> System.exit(0));
        settings.setOnAction(action -> App.getSceneManager().switchScene("settings"));
    }

    static void sendStartBreakNotification(){
        icon.showMessage("Catch a Break!", "You have been working hard, it's time to catch a break!");
    }

    static void sendStopBreakNotification(){
        icon.showMessage("Break is over!", "It's show time, back to work!");
    }

}