package com.catchabreak;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {

    // VARIABLES ------------------------

    @FXML
    private Label timerLabel = new Label();
    @FXML
    private Button manageTimerButton = new Button();
    @FXML
    private Button restartTimerButton = new Button();

    private int timerSeconds = 0;
    private int WORKTIME = 15;
    private int BREAKTIME = 8;
    private int numOfBreaks = 0;
    private int numGlassesWater = 0;
    Boolean isTimerPaused = false;
    Boolean inABreak = false;

    Timeline normalTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> incrementTimer()));
    Timeline breakTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));

    // ----------------------------------

    public void initialize() {
        // Initialize the timer label
        startTimer(normalTimeline);
    }

    @FXML
    private void startTimer(Timeline t) {

        t.setCycleCount(Timeline.INDEFINITE);
        setTimer(timerSeconds);
        t.play();
    }

    private void setTimer(int seconds){
        timerLabel.setText(String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));
    }

    private void incrementTimer() {
        // Update the timer label with the current counter value
        timerSeconds++;
        setTimer(timerSeconds);
        if(timerSeconds % (WORKTIME + 1) == 0) handleStartBreak();
    }

    private void decrementTimer(){

        timerSeconds--;
        setTimer(timerSeconds);
        if(timerSeconds == -1) handleStopBreak();
    }

    @FXML
    private void manageTimer(){

        isTimerPaused = !isTimerPaused;

        if(isTimerPaused){

            manageTimerButton.setText("Start");
            normalTimeline.pause();
        }
        else{

            manageTimerButton.setText("Stop");
            normalTimeline.play();
        }
    }

    @FXML
    private void restartTimer(){

        if(inABreak){

            timerSeconds = BREAKTIME;
            breakTimeLine.playFromStart();
        }
        else{

            timerSeconds = 0;
            normalTimeline.playFromStart();
        }
        setTimer(timerSeconds);
    }

    private void handleStartBreak(){

        TrayController.sendStartBreakNotification();

        inABreak = true;
        normalTimeline.stop();
        timerSeconds = BREAKTIME;
        numOfBreaks++;
        setTimer(timerSeconds);
        startTimer(breakTimeLine);
    }

    private void handleStopBreak(){

        TrayController.sendStopBreakNotification();

        inABreak = false;
        breakTimeLine.stop();
        timerSeconds = 0;
        setTimer(timerSeconds);
        startTimer(normalTimeline);
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
