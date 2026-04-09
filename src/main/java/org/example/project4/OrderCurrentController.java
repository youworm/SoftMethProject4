package org.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrderCurrentController {

    @FXML private ListView currentOrderList;

    @FXML private Label numPizzasLabel;
    @FXML private Label taxLabel;
    @FXML private Label subtotalLabel;
    @FXML private Label totalLabel;

    @FXML
    public void initialize() {
        // later: bind to current Order model
    }

    @FXML
    public void removePizza() {
        // later logic
    }

    @FXML
    public void placeOrder() {
        // move current order → final orders list
    }
}