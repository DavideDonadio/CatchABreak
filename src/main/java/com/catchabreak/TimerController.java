package com.catchabreak;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class TimerController {

    static Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));
    static private int WORKTIME = 10;
    static private int timerSeconds = WORKTIME;
    static private int BREAKTIME = 5;
    static private int numOfBreaks = 0;
    static Boolean inABreak = false;

    static void handleStartBreak() {

        TrayController.sendStartBreakNotification();

        inABreak = true;
        numOfBreaks++;
        
        timeLine.pause();
        timerSeconds = BREAKTIME;
        startTimer();

        TimerModel.getInstance().setTimerSeconds(timerSeconds);
    }

    static int getTimerSeconds(){

        return timerSeconds;
    }

    static void handleRestartImageClick() {

        if(inABreak) timerSeconds = BREAKTIME;
        else timerSeconds = WORKTIME;

        startTimer();
        
        TimerModel.getInstance().setTimerSeconds(timerSeconds);
    }

    static void handleStopBreak() {

        TrayController.sendStopBreakNotification();

        inABreak = false;
        timeLine.pause();
        timerSeconds = WORKTIME;
        startTimer();

        TimerModel.getInstance().setTimerSeconds(timerSeconds);
    }

    static void decrementTimer() {

        TimerModel model = TimerModel.getInstance();
        model.setTimerSeconds(model.getTimerSeconds() - 1);

        if (model.getTimerSeconds() == -1) {
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
}
