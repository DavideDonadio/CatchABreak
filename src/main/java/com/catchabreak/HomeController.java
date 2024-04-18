package com.catchabreak;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    // ----------------------------------

    public void initialize() {

        setUITimer(TimerModel.getTimerSeconds());

        // Modifies timer seconds on the UI every time timerSeconds gets updated
        TimerModel.timerSecondsProperty().addListener((observable, oldValue, newValue) ->
                setUITimer(newValue.intValue()));

        playTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                TimerController.startTimer());

        stopTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                TimerController.pauseTimer());

        restartTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                TimerController.handleRestartImageClick());
    }

    private void setUITimer(int seconds){
        timerLabel.setText(String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));
    }

    @FXML
    private void switchToSettings() throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage stage = (Stage) timerLabel.getScene().getWindow();
    stage.setScene(scene);
    }
}
