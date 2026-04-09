package org.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import org.example.project4.backend.Size;
import org.example.project4.backend.Topping;
import org.example.project4.backend.Pizza;
import org.example.project4.backend.PizzaFactory;
import org.example.project4.backend.ChicagoPizza;


public class ChicagoController {

    private Pizza currentPizza;
    private PizzaFactory factory = new ChicagoPizza();

    @FXML
    private ComboBox<String> pizzaTypeBox;

    @FXML
    private ListView<Topping> availableToppings;

    @FXML
    private ListView<Topping> selectedToppings;

    @FXML
    private RadioButton smallBtn;

    @FXML
    private RadioButton mediumBtn;

    @FXML
    private RadioButton largeBtn;

    private ToggleGroup sizeGroup;

    @FXML
    public void initialize() {

        // SIZE GROUP
        sizeGroup = new ToggleGroup();
        smallBtn.setToggleGroup(sizeGroup);
        mediumBtn.setToggleGroup(sizeGroup);
        largeBtn.setToggleGroup(sizeGroup);

        smallBtn.setSelected(true);

        // TOPPINGS LISTS
        availableToppings.setItems(
                FXCollections.observableArrayList(Topping.values())
        );

        selectedToppings.setItems(
                FXCollections.observableArrayList()
        );

        // PIZZA TYPE OPTIONS
        pizzaTypeBox.setItems(
                FXCollections.observableArrayList(
                        "Deluxe",
                        "BBQ Chicken",
                        "Meatzza",
                        "Build Your Own"
                )
        );

        pizzaTypeBox.setOnAction(e -> createPizza());
    }

    // ================= CORE LOGIC =================

    private void createPizza() {

        String type = pizzaTypeBox.getValue();
        if (type == null) return;

        switch (type) {

            case "Deluxe":
                currentPizza = factory.createDeluxe();
                setLocked(true);
                break;

            case "BBQ Chicken":
                currentPizza = factory.createBBQChicken();
                setLocked(true);
                break;

            case "Meatzza":
                currentPizza = factory.createMeatzza();
                setLocked(true);
                break;

            case "Build Your Own":
                currentPizza = factory.createBuildYourOwn();
                setLocked(false);
                break;
        }

        currentPizza.setSize(getSelectedSize());
        updateSelectedToppings();
    }

    private void setLocked(boolean locked) {
        availableToppings.setDisable(locked);
        selectedToppings.setDisable(locked);
    }

    // ================= TOPPINGS =================

    @FXML
    private void addTopping() {
        Topping selected = availableToppings.getSelectionModel().getSelectedItem();

        if (selected == null || currentPizza == null) return;

        if (currentPizza.getToppings().size() >= 5) return;

        currentPizza.addTopping(selected);
        updateSelectedToppings();
    }

    @FXML
    private void removeTopping() {
        Topping selected = selectedToppings.getSelectionModel().getSelectedItem();

        if (selected == null || currentPizza == null) return;

        currentPizza.removeTopping(selected);
        updateSelectedToppings();
    }

    private void updateSelectedToppings() {
        selectedToppings.setItems(
                FXCollections.observableArrayList(currentPizza.getToppings())
        );
    }


    @FXML
    private void updateSize() {
        if (currentPizza != null) {
            currentPizza.setSize(getSelectedSize());
        }
    }

    private Size getSelectedSize() {
        if (smallBtn.isSelected()) return Size.SMALL;
        if (mediumBtn.isSelected()) return Size.MEDIUM;
        return Size.LARGE;
    }
}