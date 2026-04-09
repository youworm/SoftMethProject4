package org.example.project4.backend;

public class BBQChicken extends Pizza {

    private static final double SMALL_PRICE = 16.99;
    private static final double MEDIUM_PRICE = 18.99;
    private static final double LARGE_PRICE = 20.99;

    public BBQChicken() {
        super();
        addTopping(Topping.BBQ_CHICKEN);
        addTopping(Topping.GREEN_PEPPER);
        addTopping(Topping.PROVOLONE);
        addTopping(Topping.CHEDDAR);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return SMALL_PRICE;
            case MEDIUM: return MEDIUM_PRICE;
            case LARGE: return LARGE_PRICE;
            default: return 0;
        }
    }
}