package com.catchabreak;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;


public class TimerController {

    static Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));
    static private int WORKTIME = 1200;
    static private int timerSeconds = WORKTIME;
    static private int BREAKTIME = 300;
    static private int numOfBreaks = 0;
    static Boolean inABreak = false;

    static void handleStartBreak() {

        TrayController.sendStartBreakNotification();

        inABreak = true;
        numOfBreaks++;
        
        timeLine.pause();
        timerSeconds = BREAKTIME;
        startTimer();

        TimerModel.setTimerSeconds(timerSeconds);
    }

    static void handleRestartImageClick() {

        if(inABreak) timerSeconds = BREAKTIME;
        else timerSeconds = WORKTIME;

        startTimer();
        TimerModel.setTimerSeconds(timerSeconds);
    }

    static void handleStopBreak() {

        TrayController.sendStopBreakNotification();

        inABreak = false;
        timeLine.pause();
        timerSeconds = WORKTIME;
        startTimer();

        TimerModel.setTimerSeconds(timerSeconds);
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

    static int getWorkTimeMinutes(){

        return (WORKTIME / 60);
    }

    static int getBreakTimeMinutes(){

        return (BREAKTIME / 60);
    }

    static int getWorkTimeSeconds(){

        return WORKTIME;
    }

    static int getBreakTimeSeconds(){

        return BREAKTIME;
    }

    static void setWorkTime(int minutes){

        WORKTIME =  minutes * 60;
    }

    static void setBreakTime(int minutes){

        BREAKTIME =  minutes * 60;
    }
}
