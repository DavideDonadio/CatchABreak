package com.catchabreak;

import java.io.IOException;

import javafx.animation.Animation;
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

    private TimerManager timerManager;

    // ----------------------------------

    public HomeController() {
        this.timerManager = TimerManager.getInstance(this);
    }

    public void initialize() {

        setUITimer(TimerModel.getTimerSeconds());

        // Modifies timer seconds on the UI every time timerSeconds gets updated
        TimerModel.timerSecondsProperty()
                .addListener((observable, oldValue, newValue) -> setUITimer(newValue.intValue()));

        updateButtonStates();

        playTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            timerManager.startTimer();
            updateButtonStates();
        });

        stopTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            timerManager.pauseTimer();
            updateButtonStates();
        });

        restartTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            timerManager.handleRestartImageClick();
            updateButtonStates();
        });
    }

    private void updateButtonStates() {
        
        Animation.Status status = timerManager.getTimeLineStatus();

        if (status == Animation.Status.STOPPED) {

            playTimerImage.setDisable(false);
            stopTimerImage.setDisable(true);
            restartTimerImage.setDisable(true);
        } 
        else if (status == Animation.Status.RUNNING) {

            playTimerImage.setDisable(true);
            stopTimerImage.setDisable(false);
            restartTimerImage.setDisable(false);
        } 
        else {
            playTimerImage.setDisable(false);
            stopTimerImage.setDisable(true);
            restartTimerImage.setDisable(false);
        }
    }

    public void setUITimer(int seconds) {
        timerLabel.setText(String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));
    }

    public void displayMessage(Boolean working) {

        if (working) {
            workLabel.setVisible(false);
            breakLabel.setVisible(true);
        } else {
            workLabel.setVisible(true);
            breakLabel.setVisible(false);
        }
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.getSceneManager().switchScene("settings");
    }

    @FXML
    private void switchToStats() throws IOException {
        App.getSceneManager().switchScene("stats");
    }

    @FXML
    private void switchToInfo() throws IOException {
        App.getSceneManager().switchScene("info");
    }
}
