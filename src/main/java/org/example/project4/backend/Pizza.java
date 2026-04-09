package org.example.project4.backend;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Crust crust;
    protected Size size;

    public Pizza() {
        toppings = new ArrayList<>();
    }

    public abstract double price();

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}