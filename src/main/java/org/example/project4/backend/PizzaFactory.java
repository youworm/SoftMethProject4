package org.example.project4.backend;

public interface PizzaFactory {
    Pizza createDeluxe(); //create a Deluxe object and set the toppings/crust
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
