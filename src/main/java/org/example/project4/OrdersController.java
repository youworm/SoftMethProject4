package org.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.project4.backend.Order;
import org.example.project4.backend.StoreOrders;

import java.io.IOException;

public class OrdersController {

    private StoreOrders storeOrders;
    private Order currentOrder;

    @FXML
    private ComboBox<Integer> orderComboBox;

    @FXML
    private TextArea orderDetailsArea;

    public void setData(StoreOrders storeOrders, Order currentOrder) {
        this.storeOrders = storeOrders;
        this.currentOrder = currentOrder;
        refreshOrders();
    }

    private void refreshOrders() {
        if (orderComboBox == null || orderDetailsArea == null) {
            return;
        }

        orderComboBox.getItems().clear();
        orderDetailsArea.clear();

        if (storeOrders == null || storeOrders.isEmpty()) {
            return;
        }

        for (Order order : storeOrders.getOrders()) {
            orderComboBox.getItems().add(order.getNumber());
        }
    }

    @FXML
    public void showOrderDetails() {
        if (storeOrders == null) {
            return;
        }

        Integer selectedOrderNumber = orderComboBox.getValue();
        if (selectedOrderNumber == null) {
            return;
        }

        Order selectedOrder = storeOrders.findOrder(selectedOrderNumber);
        if (selectedOrder != null) {
            orderDetailsArea.setText(selectedOrder.toString());
        }
    }

    @FXML
    public void cancelOrder() {
        if (storeOrders == null) {
            return;
        }

        Integer selectedOrderNumber = orderComboBox.getValue();
        if (selectedOrderNumber == null) {
            return;
        }

        boolean removed = storeOrders.removeOrder(selectedOrderNumber);

        if (removed) {
            refreshOrders();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Canceled");
            alert.setHeaderText(null);
            alert.setContentText("Order #" + selectedOrderNumber + " was canceled.");
            alert.showAndWait();
        }
    }

    @FXML
    public void exportOrders() {
        if (storeOrders == null || storeOrders.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Orders");
            alert.setHeaderText(null);
            alert.setContentText("There are no orders to export.");
            alert.showAndWait();
            return;
        }

        try {
            storeOrders.exportOrders("orders.txt");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Orders exported to orders.txt");
            alert.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("Could not export orders.");
            alert.showAndWait();
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