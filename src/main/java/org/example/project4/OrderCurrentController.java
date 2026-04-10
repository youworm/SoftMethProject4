package org.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.project4.backend.Order;
import org.example.project4.backend.StoreOrders;
import org.example.project4.backend.Pizza;

import java.io.IOException;

/**
 * Controller for the current order screen.
 * Displays pizzas in the current order and allows modification,
 * clearing, and placing of the order.
 */
public class OrderCurrentController {

    private Order currentOrder;
    private StoreOrders storeOrders;

    @FXML
    private ListView<Pizza> currentOrderList;

    @FXML
    private Label orderNumberLabel;

    @FXML
    private Label numPizzasLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label totalLabel;

    /**
     * Initializes UI components with default values before data is loaded.
     */
    @FXML
    public void initialize() {
        // screen starts empty until data is passed in
        numPizzasLabel.setText("0");
        subtotalLabel.setText("$0.00");
        taxLabel.setText("$0.00");
        totalLabel.setText("$0.00");
    }

    /**
     * Sets shared application data and refreshes the view.
     *
     * @param currentOrder the active order being displayed
     * @param storeOrders the global store order list
     */
    public void setData(Order currentOrder, StoreOrders storeOrders) {
        this.currentOrder = currentOrder;
        this.storeOrders = storeOrders;
        refreshView();
    }

    /**
     * Refreshes all UI elements to reflect the current order state.
     */
    private void refreshView() {
        if (currentOrder == null) {
            currentOrderList.getItems().clear();
            orderNumberLabel.setText("");
            numPizzasLabel.setText("0");
            subtotalLabel.setText("$0.00");
            taxLabel.setText("$0.00");
            totalLabel.setText("$0.00");
            return;
        }

        currentOrderList.getItems().setAll(currentOrder.getPizzas());
        orderNumberLabel.setText(String.valueOf(currentOrder.getNumber()));
        numPizzasLabel.setText(String.valueOf(currentOrder.getPizzas().size()));
        subtotalLabel.setText("$" + String.format("%.2f", currentOrder.getSubtotal()));
        taxLabel.setText("$" + String.format("%.2f", currentOrder.getSalesTax()));
        totalLabel.setText("$" + String.format("%.2f", currentOrder.getTotal()));
    }

    /**
     * Removes the selected pizza from the current order.
     */
    @FXML
    public void removePizza() {
        Pizza selectedPizza = currentOrderList.getSelectionModel().getSelectedItem();

        if (selectedPizza == null || currentOrder == null) {
            return;
        }

        currentOrder.removePizza(selectedPizza);
        refreshView();
    }

    /**
     * Clears all pizzas from the current order.
     */
    @FXML
    public void clearOrder() {
        currentOrder.clear();
        refreshView();
    }

    /**
     * Places the current order into the store order list
     * and resets the current order.
     */
    @FXML
    public void placeOrder() {
        if (currentOrder == null || currentOrder.isEmpty() || storeOrders == null) {
            return;
        }

        storeOrders.addOrder(currentOrder);
        currentOrder = new Order();
        refreshView();
    }

    /**
     * Navigates back to the main menu screen while preserving data.
     *
     * @param event the button click event
     */
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
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}