package com.catchabreak;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatsController {

    @FXML
    Label numOfBreaksLabel = new Label();
    @FXML
    Label timeSpentlLabel = new Label();
    @FXML
    Label timerStartedLabel = new Label();
    @FXML
    Label timerStoppedLabel = new Label();
    @FXML
    Label timerRestartedLabel = new Label();

    public void initialize(){

        numOfBreaksLabel.setText(Integer.toString(PreferencesUtil.getNumberOfBreaks()));
        timerStartedLabel.setText(Integer.toString(PreferencesUtil.getNumberOfTimerStarted()));
        timerStoppedLabel.setText(Integer.toString(PreferencesUtil.getNumberOfTimerStopped()));
        timerRestartedLabel.setText(Integer.toString(PreferencesUtil.getNumberOfTimerRestarted()));
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.getSceneManager().switchScene("primary");
    }
}
