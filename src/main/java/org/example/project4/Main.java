package org.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project4.backend.*;

import java.io.IOException;

/**
 * Main entry point for the RU Pizza JavaFX application.
 * Loads the main FXML view and initializes shared application data.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by loading the main scene,
     * initializing controllers, and displaying the primary stage.
     *
     * @param stage the primary stage provided by JavaFX
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        MainController controller = fxmlLoader.getController();
        controller.setData(new Order(), new StoreOrders());

        stage.setTitle("RU Pizza");
        stage.setScene(scene);
        stage.show();
    }
}
