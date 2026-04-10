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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        MainController controller = fxmlLoader.getController();
        controller.setData(new Order(), new StoreOrders());

        stage.setTitle("RU Pizza");
        stage.setScene(scene);
        stage.show();
    }
}
