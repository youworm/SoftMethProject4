package org.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.example.project4.backend.Order;
import org.example.project4.backend.StoreOrders;

import java.io.IOException;

/**
 * Controller for the main navigation screen of the RU Pizza application.
 * Handles switching between different views (Chicago, NY, Orders, Current Order)
 * while preserving shared application state.
 */
public class MainController {

    private Order currentOrder;
    private StoreOrders storeOrders;

    /**
     * Sets shared application data for the controller.
     *
     * @param currentOrder the active order being built
     * @param storeOrders the global store order list
     */
    public void setData(Order currentOrder, StoreOrders storeOrders) {
        this.currentOrder = currentOrder;
        this.storeOrders = storeOrders;
    }


    /**
     * Switches the current scene to a new FXML view while preserving shared data.
     *
     * @param event the action event triggering the scene change
     * @param fxmlFile the FXML file to load
     */
    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/project4/" + fxmlFile)
            );

            Parent root = loader.load();
            Object controller = loader.getController();

            if (controller instanceof OrderCurrentController) {
                ((OrderCurrentController) controller).setData(currentOrder, storeOrders);
            }

            if (controller instanceof OrdersController) {
                ((OrdersController) controller).setData(storeOrders, currentOrder);
            }

            if (controller instanceof ChicagoController) {
                ((ChicagoController) controller).setData(currentOrder, storeOrders);
            }

            if (controller instanceof NYController) {
                ((NYController) controller).setData(currentOrder, storeOrders);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("RU Pizza");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Initializes the controller (currently unused). */
    public void initialize() { }

    /**
     * Navigates to the Chicago pizza ordering view.
     *
     * @param event the button click event
     */
    @FXML
    protected void onChicagoClick(ActionEvent event) {
        switchScene(event, "chicago-view.fxml");
    }

    /**
     * Navigates to the New York pizza ordering view.
     *
     * @param event the button click event
     */
    @FXML
    protected void onNYClick(ActionEvent event) {
        switchScene(event, "ny-view.fxml");
    }

    /**
     * Navigates to the store orders (all orders) view.
     *
     * @param event the button click event
     */
    @FXML
    protected void onOrdersPlacedClick(ActionEvent event) {
        switchScene(event, "orders-view.fxml");
    }

    /**
     * Navigates to the current order view.
     *
     * @param event the button click event
     */
    @FXML
    protected void onCurrentOrderClick(ActionEvent event) {
        switchScene(event, "order-current-view.fxml");
    }

}