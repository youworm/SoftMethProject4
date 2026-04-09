package org.example.project4.backend;

public class Meatzza extends Pizza {

    private static final double SMALL_PRICE = 19.99;
    private static final double MEDIUM_PRICE = 21.99;
    private static final double LARGE_PRICE = 23.99;

    public Meatzza() {
        super();
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.BEEF);
        addTopping(Topping.HAM);
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