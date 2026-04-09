package org.example.project4.backend;

public class Deluxe extends Pizza {

    private static final double SMALL_PRICE = 18.99;
    private static final double MEDIUM_PRICE = 20.99;
    private static final double LARGE_PRICE = 22.99;

    public Deluxe() {
        super();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
    }

    @Override
    public double price() {
        switch (size) {
            case SMALL: return SMALL_PRICE;
            case MEDIUM: return MEDIUM_PRICE;
            case LARGE: return LARGE_PRICE;
            default: return 0;
        }
    }
}