package com.catchabreak;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class TimerManager {

    private static TimerManager instance;
    private HomeController homeController;
    private  Timeline timeLine;
    private int WORKTIME_MINUTES = 20;
    private int BREAKTIME_MINUTES = 5;
    private int numOfBreaks = 0;
    Boolean inABreak = false;

    public TimerManager() {
        this.timeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementTimer()));
    }
    
    public static TimerManager getInstance(HomeController homeController) {
        
        if (instance == null)
            instance = new TimerManager();
        
        instance.setHomeController(homeController);
        return instance;
    }

    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }

    public void retrieveSettings(){

        WORKTIME_MINUTES = PreferencesUtil.getWorkTimeMinutes();
        BREAKTIME_MINUTES = PreferencesUtil.getBreakTimeMinutes();

        if(timeLine.getStatus() == Animation.Status.STOPPED)
            TimerModel.setTimerSeconds(getWorkTimeSeconds());
    }

    public void handleStartBreak() {

        TrayController.sendStartBreakNotification();

        inABreak = true;
        numOfBreaks++;
        
        timeLine.pause();
        TimerModel.setTimerSeconds(getBreakTimeSeconds());
        homeController.displayMessage(inABreak);
        startTimer();
    }

    public void handleRestartImageClick() {

        if(inABreak) TimerModel.setTimerSeconds(getBreakTimeSeconds());
        else TimerModel.setTimerSeconds(getWorkTimeSeconds());

        startTimer();
    }

    public void handleStopBreak() {

        TrayController.sendStopBreakNotification();

        inABreak = false;
        timeLine.pause();
        TimerModel.setTimerSeconds(getWorkTimeSeconds());
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
    }

    @FXML
    public void pauseTimer() {
        timeLine.pause();
    }

    public int getWorkTimeSeconds(){
        return (WORKTIME_MINUTES * 60);
    }

    public int getBreakTimeMinutes(){
        return BREAKTIME_MINUTES;
    }

    public int getWorkTimeMinutes(){
        return WORKTIME_MINUTES;
    }

    public int getBreakTimeSeconds(){
        return (BREAKTIME_MINUTES * 60);
    }

    public void setWorkTimeMinutes(int minutes){
        WORKTIME_MINUTES = minutes;
    }

    public void setBreakTimeMinutes(int minutes){
        BREAKTIME_MINUTES = minutes;
    }
}
