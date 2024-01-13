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
    private int breaksCounter = 0;
    private int TIMEBEFOREBREAK = 5;
    private int BREAKTIME = 30;
    private int numOfBreaks = 0;
    private int numGlassesWater = 0;
    Boolean isCounterPaused = false;

    Timeline normalTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> incrementCounter()));
    Timeline breakTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementCounter()));

    public void initialize() {
        // Initialize the counter label
        startCounter(normalTimeline);
    }

    @FXML
    private void startCounter(Timeline t) {

        t.setCycleCount(Timeline.INDEFINITE);
        setTimer(counter);
        t.play();
    }

    private void incrementCounter() {
        // Update the counter label with the current counter value
        breaksCounter = ++counter;
        setTimer(counter);
        if(breaksCounter == TIMEBEFOREBREAK + 1) handleStartBreak();
    }

    private void decrementCounter(){

        counter--;
        setTimer(counter);
        if(counter == 0) handleStopBreak();
    }

    private void handleStartBreak(){

        normalTimeline.stop();
        breaksCounter = 0;
        counter = BREAKTIME;
        numOfBreaks++;
        setTimer(BREAKTIME);
        startCounter(breakTimeLine);
    }

    private void handleStopBreak(){

    }

    private void setTimer(int seconds){
        counterLabel.setText(String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));
    }
    
    @FXML
    private void manageCounter(){

        isCounterPaused = !isCounterPaused;

        if(isCounterPaused){

            manageCounterButton.setText("Start");
            normalTimeline.pause();
        }
        else{

            manageCounterButton.setText("Stop");
            normalTimeline.play();
        }
    }

    @FXML
    private void restartCounter(){

        counter = 0;
        counterLabel.setText(String.format("%02d:%02d:%02d", counter / 3600, (counter % 3600) / 60, counter % 60));
        normalTimeline.playFromStart();
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
