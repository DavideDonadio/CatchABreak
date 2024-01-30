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

    private TimerModel model = TimerModel.getInstance();

    // ----------------------------------

    public void initialize() {

        setTimer(model.getTimerSeconds());

        model.timerSecondsProperty().addListener((observable, oldValue, newValue) -> {
            // Update UI based on the new timer value
            setTimer(newValue.intValue());
        });

        // Initialize images event handlers	
        playTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            TimerController.timeLine.play();
        });

        stopTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            TimerController.timeLine.pause();
        });

        restartTimerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            TimerController.handleRestartImageClick();
        });
    }

    private void setTimer(int seconds){
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
