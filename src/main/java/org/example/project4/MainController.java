package org.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    protected void onChicagoClick() {
        System.out.println("Chicago Style clicked");
        // later: switch scene
    }

    @FXML
    protected void onNYClick() {
        System.out.println("NY Style clicked");
    }
}
