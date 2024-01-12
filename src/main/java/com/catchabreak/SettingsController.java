package com.catchabreak;

import java.io.IOException;
import javafx.fxml.FXML;

public class SettingsController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}