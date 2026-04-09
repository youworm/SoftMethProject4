package org.example.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class MainController {
    @FXML
    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/org/example/project4/" + fxmlFile)
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onChicagoClick(ActionEvent event) {
        switchScene(event, "chicago-view.fxml");
    }

    @FXML
    protected void onNYClick(ActionEvent event) {
        switchScene(event, "ny-view.fxml");
    }
}
