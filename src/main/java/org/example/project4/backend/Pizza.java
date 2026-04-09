package org.example.project4.backend;

import java.util.ArrayList;

public abstract class Pizza {
    private ArrayList<Topping> toppings; //Topping is a enum class
    private Crust crust; //Crust is a enum class
    private Size size; //Size is a enum class
    public abstract double price(); //polymorphism

    public Pizza() {
        toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size + " " + crust + " " + toppings + " $" +
                String.format("%.2f", price());
    }
}