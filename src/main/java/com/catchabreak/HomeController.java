package com.catchabreak;

import java.io.IOException;

import javafx.animation.Animation;
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
    private ImageView stopTimerImage = new ImageView();
    @FXML
    private ImageView playTimerImage = new ImageView();
    @FXML
    private ImageView restartTimerImage = new ImageView();

    private int WORKTIME = 300;
    private int timerSeconds = WORKTIME;
    private int BREAKTIME = 60;
    private int numOfBreaks = 0;
    private int numGlassesWater = 0;
    Boolean isTimerPaused = false;
    Boolean inABreak = false;

    Timeline normalTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));
    Timeline breakTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));

    // ----------------------------------

    public void initialize() {
        // Initialize the timer label
        startTimer(normalTimeline);


        // Initialize images event handlers	
        playTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            handlePlayImageClick();
        });

        stopTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            handleStopImageClick();
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

        inABreak = true;
        normalTimeline.pause();
        timerSeconds = BREAKTIME;
        numOfBreaks++;
        setTimer(timerSeconds);
        startTimer(breakTimeLine);
    }

    private void handleStopBreak(){

        TrayController.sendStopBreakNotification();

        inABreak = false;
        breakTimeLine.pause();
        timerSeconds = WORKTIME;
        setTimer(timerSeconds);
        startTimer(normalTimeline);
    }

    private void handleStopImageClick() {
        
        if(normalTimeline.getStatus() == Animation.Status.RUNNING)
            normalTimeline.pause();
        
        else if(breakTimeLine.getStatus() == Animation.Status.RUNNING)
            breakTimeLine.pause();
    }

    private void handlePlayImageClick() {

        if(normalTimeline.getStatus() == Animation.Status.PAUSED)
            normalTimeline.play();
        
        else if(breakTimeLine.getStatus() == Animation.Status.PAUSED)
            breakTimeLine.play();
    }

    private void handleRestartImageClick() {
        
        if(inABreak){

            timerSeconds = BREAKTIME;
            setTimer(timerSeconds);
            startTimer(breakTimeLine);
        }
        else{

            timerSeconds = WORKTIME;
            setTimer(timerSeconds);
            startTimer(normalTimeline);
        }
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }
}
