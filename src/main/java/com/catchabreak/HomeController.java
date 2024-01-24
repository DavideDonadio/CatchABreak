package com.catchabreak;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeController {

    // VARIABLES ------------------------

    @FXML
    private Label timerLabel = new Label();
    @FXML
    private Label workLabel = new Label();
    @FXML
    private Label breakLabel = new Label();
    @FXML
    private ImageView stopTimerImage = new ImageView();
    @FXML
    private ImageView playTimerImage = new ImageView();
    @FXML
    private ImageView restartTimerImage = new ImageView();

    private int WORKTIME = 10;
    private int timerSeconds = WORKTIME;
    private int BREAKTIME = 5;
    private int numOfBreaks = 0;
    Boolean inABreak = false;

    Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));

    // ----------------------------------

    public void initialize() {
        // Initialize the timer label
        startTimer(timeLine);

        // Initialize images event handlers	
        playTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            timeLine.play();
        });

        stopTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            timeLine.pause();
        });

        restartTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            handleRestartImageClick();
        });
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

    private void decrementTimer(){

        timerSeconds--;
        setTimer(timerSeconds);

        if(timerSeconds == -1){

            if(!inABreak) 
                handleStartBreak();
            else 
                handleStopBreak();
        }
    }

    private void handleStartBreak(){

        TrayController.sendStartBreakNotification();

        workLabel.setVisible(false);
        breakLabel.setVisible(true);

        inABreak = true;
        numOfBreaks++;
        
        timeLine.pause();
        timerSeconds = BREAKTIME;
        setTimer(timerSeconds);
        startTimer(timeLine);
    }

    private void handleStopBreak(){

        TrayController.sendStopBreakNotification();

        breakLabel.setVisible(false);
        workLabel.setVisible(true);

        inABreak = false;
        timeLine.pause();
        timerSeconds = WORKTIME;
        setTimer(timerSeconds);
        startTimer(timeLine);
    }

    private void handleRestartImageClick() {
        
        if(inABreak) timerSeconds = BREAKTIME;
        else timerSeconds = WORKTIME;

        setTimer(timerSeconds);
        startTimer(timeLine);
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
