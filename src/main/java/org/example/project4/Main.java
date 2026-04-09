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
        Pizza p1 = new Deluxe();
        p1.setSize(Size.SMALL);
        System.out.println("Deluxe small: " + p1.price());

        Pizza p2 = new BBQChicken();
        p2.setSize(Size.LARGE);
        System.out.println("BBQ Chicken large: " + p2.price());

        Pizza p3 = new Meatzza();
        p3.setSize(Size.MEDIUM);
        System.out.println("Meatzza medium: " + p3.price());

        Pizza p4 = new BuildYourOwn();
        p4.setSize(Size.SMALL);
        p4.addTopping(Topping.SAUSAGE);
        p4.addTopping(Topping.ONION);
        System.out.println("BYO small 2 toppings: " + p4.price());

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
