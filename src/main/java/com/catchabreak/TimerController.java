package com.catchabreak;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import java.util.prefs.Preferences;


public class TimerController {

    static Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));
    static Preferences prefs = Preferences.userNodeForPackage(TimerController.class);

    static private int WORKTIME = Integer.parseInt(prefs.get("workTimeSeconds", "1200"));
    static private int BREAKTIME = Integer.parseInt(prefs.get("breakTimeSeconds", "500"));
    static private int numOfBreaks = 0;
    static Boolean inABreak = false;

    static void handleStartBreak() {

        TrayController.sendStartBreakNotification();

        inABreak = true;
        numOfBreaks++;
        
        timeLine.pause();
        TimerModel.setTimerSeconds(BREAKTIME);
        startTimer();
    }

    static void handleRestartImageClick() {

        if(inABreak) TimerModel.setTimerSeconds(BREAKTIME);
        else TimerModel.setTimerSeconds(WORKTIME);

        startTimer();
    }

    static void handleStopBreak() {

        TrayController.sendStopBreakNotification();

        inABreak = false;
        timeLine.pause();
        TimerModel.setTimerSeconds(WORKTIME);
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

    static void setWorkTimeMinutes(int minutes){
        WORKTIME =  minutes * 60;
    }

    static void setBreakTimeMinutes(int minutes){
        BREAKTIME =  minutes * 60;
    }
}
