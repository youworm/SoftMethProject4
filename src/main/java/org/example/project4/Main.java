package org.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project4.backend.*;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/project4/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}