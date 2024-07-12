package com.catchabreak;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;


public class TimerController {

    static Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));

    static private int WORKTIME_MINUTES = 20;
    static private int BREAKTIME_MINUTES = 5;
    static private int numOfBreaks = 0;
    static Boolean inABreak = false;

    static void handleStartBreak() {

        TrayController.sendStartBreakNotification();

        inABreak = true;
        numOfBreaks++;
        
        timeLine.pause();
        TimerModel.setTimerSeconds(BREAKTIME_MINUTES);
        startTimer();
    }

    static void handleRestartImageClick() {

        if(inABreak) TimerModel.setTimerSeconds(BREAKTIME_MINUTES);
        else TimerModel.setTimerSeconds(WORKTIME_MINUTES);

        startTimer();
    }

    static void handleStopBreak() {

        TrayController.sendStopBreakNotification();

        inABreak = false;
        timeLine.pause();
        TimerModel.setTimerSeconds(WORKTIME_MINUTES);
        startTimer();
    }

    static void decrementTimer() {

        TimerModel.setTimerSeconds(TimerModel.getTimerSeconds() - 1);

        if (TimerModel.getTimerSeconds() == -1) {
            if (!inABreak) 
                handleStartBreak();
            else 
                handleStopBreak();
        }
    }

    @FXML
    static void startTimer() {

        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    @FXML
    static void pauseTimer() {
        timeLine.pause();
    }

    static int getWorkTimeSeconds(){
        return (WORKTIME_MINUTES * 60);
    }

    static int getBreakTimeMinutes(){
        return (BREAKTIME_MINUTES / 60);
    }

    static int getWorkTimeMinutes(){
        return WORKTIME_MINUTES;
    }

    static int getBreakTimeSeconds(){
        return BREAKTIME_MINUTES;
    }

    static void setWorkTimeMinutes(int minutes){
        WORKTIME_MINUTES =  minutes * 60;
    }

    static void setBreakTimeMinutes(int minutes){
        BREAKTIME_MINUTES =  minutes * 60;
    }
}
