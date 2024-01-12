package com.catchabreak;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label counterLabel = new Label();
    @FXML
    private Button manageCounterButton = new Button();
    @FXML
    private Button restartButton = new Button();
    
    private int counter = 0;
    Boolean isCounterPaused = false;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> incrementCounter()));

    public void initialize() {
        // Initialize the counter label
        startCounter();
    }

    @FXML
    private void startCounter() {
        // Create a timeline to update the counter every second;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void incrementCounter() {
        // Update the counter label with the current counter value
        counter+=1;
        counterLabel.setText(String.format("%02d:%02d:%02d", counter / 3600, (counter % 3600) / 60, counter % 60));
    }
    
    @FXML
    private void manageCounter(){

        isCounterPaused = !isCounterPaused;

        if(isCounterPaused){

            manageCounterButton.setText("Start");
            timeline.pause();
        }
        else{

            manageCounterButton.setText("Stop");
            timeline.play();
        }
    }

    @FXML
    private void restartCounter(){

        counter = 0;
        counterLabel.setText(String.format("%02d:%02d:%02d", counter / 3600, (counter % 3600) / 60, counter % 60));
        timeline.playFromStart();
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
