package org.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.project4.backend.*;

import java.io.IOException;

public class NYController {

    private Order currentOrder;
    private StoreOrders storeOrders;
    private Pizza currentPizza;

    @FXML
    private ComboBox<String> pizzaTypeBox;

    @FXML
    private ComboBox<Size> sizeBox;

    @FXML
    private ListView<Topping> availableToppings;

    @FXML
    private ListView<Topping> selectedToppings;

    @FXML
    private Label crustLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView pizzaImage;

    public void setData(Order currentOrder, StoreOrders storeOrders) {
        this.currentOrder = currentOrder;
        this.storeOrders = storeOrders;
    }

    @FXML
    public void initialize() {
        pizzaTypeBox.getItems().addAll("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own");
        sizeBox.getItems().addAll(Size.values());
        availableToppings.getItems().addAll(Topping.values());
        selectedToppings.getItems().clear();
        crustLabel.setText("");
        priceLabel.setText("$0.00");

        try {
            Image image = new Image(
                    getClass().getResource("/org/example/project4/images/ny.png").toExternalForm()
            );
            pizzaImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            pizzaImage.setImage(null);
        }
    }

    @FXML
    public void createPizza() {
        String type = pizzaTypeBox.getValue();
        Size size = sizeBox.getValue();

        if (type == null) {
            return;
        }

        PizzaFactory factory = new NYPizza();

        switch (type) {
            case "Deluxe":
                currentPizza = factory.createDeluxe();
                break;
            case "BBQ Chicken":
                currentPizza = factory.createBBQChicken();
                break;
            case "Meatzza":
                currentPizza = factory.createMeatzza();
                break;
            case "Build Your Own":
                currentPizza = factory.createBuildYourOwn();
                break;
            default:
                return;
        }

        selectedToppings.getItems().setAll(currentPizza.getToppings());
        crustLabel.setText(String.valueOf(currentPizza.getCrust()));
        updateImage();

        if (size != null) {
            currentPizza.setSize(size);
            priceLabel.setText("$" + String.format("%.2f", currentPizza.price()));
        } else {
            priceLabel.setText("$0.00");
        }
    }

    @FXML
    public void addTopping() {
        if (currentPizza == null) {
            return;
        }

        if (!(currentPizza instanceof BuildYourOwn)) {
            return;
        }

        Topping topping = availableToppings.getSelectionModel().getSelectedItem();
        if (topping == null) {
            return;
        }

        if (currentPizza.getToppings().contains(topping)) {
            return;
        }

        if (currentPizza.getToppings().size() >= 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Topping Limit");
            alert.setHeaderText(null);
            alert.setContentText("Build Your Own pizzas can have at most 5 toppings.");
            alert.showAndWait();
            return;
        }

        currentPizza.addTopping(topping);
        refreshView();
    }

    @FXML
    public void removeTopping() {
        if (currentPizza == null) {
            return;
        }

        if (!(currentPizza instanceof BuildYourOwn)) {
            return;
        }

        Topping topping = selectedToppings.getSelectionModel().getSelectedItem();
        if (topping == null) {
            return;
        }

        currentPizza.removeTopping(topping);
        refreshView();
    }

    @FXML
    public void addToOrder() {
        if (currentPizza == null || currentOrder == null) {
            return;
        }

        if (currentPizza.getSize() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Size");
            alert.setHeaderText(null);
            alert.setContentText("Please select a size before adding the pizza to the order.");
            alert.showAndWait();
            return;
        }

        currentOrder.addPizza(currentPizza);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Added");
        alert.setHeaderText(null);
        alert.setContentText("Pizza added to current order.");
        alert.showAndWait();

        currentPizza = null;
        pizzaTypeBox.setValue(null);
        sizeBox.setValue(null);
        refreshView();
    }

    private void refreshView() {
        if (currentPizza == null) {
            selectedToppings.getItems().clear();
            crustLabel.setText("");
            priceLabel.setText("$0.00");

            try {
                Image image = new Image(
                        getClass().getResource("/org/example/project4/images/ny.png").toExternalForm()
                );
                pizzaImage.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                pizzaImage.setImage(null);
            }

            return;
        }

        selectedToppings.getItems().setAll(currentPizza.getToppings());
        crustLabel.setText(String.valueOf(currentPizza.getCrust()));

        if (currentPizza.getSize() != null) {
            priceLabel.setText("$" + String.format("%.2f", currentPizza.price()));
        } else {
            priceLabel.setText("$0.00");
        }

        updateImage();
    }

    private void updateImage() {
        if (currentPizza == null) {
            pizzaImage.setImage(null);
            return;
        }

        String path = "";

        if (currentPizza instanceof Deluxe) {
            path = "/org/example/project4/images/deluxe.png";
        } else if (currentPizza instanceof BBQChicken) {
            path = "/org/example/project4/images/bbq.png";
        } else if (currentPizza instanceof Meatzza) {
            path = "/org/example/project4/images/meatzza.png";
        } else if (currentPizza instanceof BuildYourOwn) {
            path = "/org/example/project4/images/byo.png";
        }

        try {
            Image image = new Image(getClass().getResource(path).toExternalForm());
            pizzaImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            pizzaImage.setImage(null);
        }
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/project4/main-view.fxml")
            );
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.setData(currentOrder, storeOrders);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("RU Pizza");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}