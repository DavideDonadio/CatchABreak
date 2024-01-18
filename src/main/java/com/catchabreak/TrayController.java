package com.catchabreak;

import com.dustinredmond.fxtrayicon.FXTrayIcon;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class TrayController{

    private FXTrayIcon icon;

    public void setupTray(Stage stage){

        icon = new FXTrayIcon(stage, getClass().getResource("/icons/icon.png"));
        icon.show();

        initializeTrayMenuItems();
    }

    public void initializeTrayMenuItems(){

        MenuItem exit = new MenuItem("Exit");
        MenuItem settings = new MenuItem("Settings");
        MenuItem stats = new MenuItem("Stats");

        icon.addMenuItems(settings, stats, exit);
    }
}