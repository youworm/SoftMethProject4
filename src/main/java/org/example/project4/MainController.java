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

public class MainController {

    private Order currentOrder;
    private StoreOrders storeOrders;

    public void setData(Order currentOrder, StoreOrders storeOrders) {
        this.currentOrder = currentOrder;
        this.storeOrders = storeOrders;
    }

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

    @FXML
    protected void onChicagoClick(ActionEvent event) {
        switchScene(event, "chicago-view.fxml");
    }

    @FXML
    protected void onNYClick(ActionEvent event) {
        switchScene(event, "ny-view.fxml");
    }

    @FXML
    protected void onOrdersPlacedClick(ActionEvent event) {
        switchScene(event, "orders-view.fxml");
    }

    @FXML
    protected void onCurrentOrderClick(ActionEvent event) {
        switchScene(event, "order-current-view.fxml");
    }

}