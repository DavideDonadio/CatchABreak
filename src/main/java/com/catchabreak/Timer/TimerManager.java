package com.catchabreak.Timer;

import com.catchabreak.Controller.HomeController;
import com.catchabreak.Controller.TrayController;
import com.catchabreak.Utils.PreferencesUtil;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class TimerManager {

    private static TimerManager instance;
    private HomeController homeController;
    private  Timeline timeLine;
    Boolean inABreak = false;

    public TimerManager() {
        this.timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));
    }
    
    public static TimerManager getInstance(HomeController homeController) {
        
        if (instance == null)
            instance = new TimerManager();
        
        instance.setHomeController(homeController);
        if(instance.timeLine.getStatus() == Status.STOPPED)
            TimerModel.setTimerSeconds(PreferencesUtil.getWorkTimeSeconds());

        return instance;
    }

    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }

    public void handleStartBreak() {

        TrayController.sendStartBreakNotification();

        inABreak = true;
        PreferencesUtil.addBreak();
        
        timeLine.pause();
        TimerModel.setTimerSeconds(PreferencesUtil.getBreakTimeSeconds());
        homeController.displayMessage(inABreak);
        startTimer();
    }

    public void handleRestartImageClick() {

        if(inABreak) TimerModel.setTimerSeconds(PreferencesUtil.getBreakTimeSeconds());
        else TimerModel.setTimerSeconds(PreferencesUtil.getWorkTimeSeconds());

        PreferencesUtil.addTimerRestart();
        startTimerFromRestart();
    }

    public void handleStopBreak() {

        TrayController.sendStopBreakNotification();

        inABreak = false;
        timeLine.pause();
        TimerModel.setTimerSeconds(PreferencesUtil.getBreakTimeSeconds());
        homeController.displayMessage(inABreak);
        startTimer();
    }

    public void decrementTimer() {

        TimerModel.setTimerSeconds(TimerModel.getTimerSeconds() - 1);

        if (TimerModel.getTimerSeconds() == -1) {
            if (!inABreak) 
                handleStartBreak();
            else 
                handleStopBreak();
        }
    }

    @FXML
    public void startTimer() {

        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();    
        PreferencesUtil.addTimerStart();
    }

    @FXML
    private void startTimerFromRestart() {
        
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();   
    }

    @FXML
    public void pauseTimer() {
        timeLine.pause();
        PreferencesUtil.addTimerStop();
    }

    @SuppressWarnings("exports")
    public Animation.Status getTimeLineStatus(){
        return timeLine.getStatus();
    }

}