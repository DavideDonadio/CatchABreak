package com.catchabreak;

import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.Desktop;
import javafx.scene.image.ImageView;

public class InfoController {


    @FXML
    private Button backButton;
    @FXML
    private ImageView githubImage;
    
    
    public void initialize() {

        githubImage.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/DavideDonadio/CatchABreak"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
   

    @FXML
    private void switchToPrimary() throws IOException {
        App.getSceneManager().switchScene("primary");
    }
}
