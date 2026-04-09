package org.example.project4.backend;

public class BuildYourOwn extends Pizza {

    private static final double SMALL_BASE = 10.99;
    private static final double MEDIUM_BASE = 12.99;
    private static final double LARGE_BASE = 14.99;
    private static final double TOPPING_PRICE = 1.69;
    private static final int MAX_TOPPINGS = 5;

    public BuildYourOwn() {
        super();
    }

    @Override
    public double price() {
        double base;

        switch (getSize()) {
            case SMALL:
                base = SMALL_BASE;
                break;
            case MEDIUM:
                base = MEDIUM_BASE;
                break;
            case LARGE:
                base = LARGE_BASE;
                break;
            default:
                return 0;
        }

        return base + (getToppings().size() * TOPPING_PRICE);
    }

    @Override
    public void addTopping(Topping topping) {
        if (getToppings().size() >= MAX_TOPPINGS) {
            return;
        }
        super.addTopping(topping);
    }
}