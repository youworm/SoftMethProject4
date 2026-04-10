package org.example.project4.backend;

import java.util.ArrayList;

public class Order {
    private static final double NJ_SALES_TAX = 0.06625;
    private static int nextOrderNumber = 1;

    private int number;
    private ArrayList<Pizza> pizzas;

    public Order() {
        this.number = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        if (pizza != null) {
            pizzas.add(pizza);
        }
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public void clearOrder() {
        pizzas.clear();
    }

    public boolean isEmpty() {
        return pizzas.isEmpty();
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    public void clear() {
        pizzas.clear();
    }

    public double getSalesTax() {
        return getSubtotal() * NJ_SALES_TAX;
    }

    public double getTotal() {
        return getSubtotal() + getSalesTax();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order #").append(number).append("\n");

        for (Pizza pizza : pizzas) {
            builder.append(pizza).append("\n");
        }

        builder.append("Subtotal: $")
                .append(String.format("%.2f", getSubtotal()))
                .append("\n");

        builder.append("Sales Tax: $")
                .append(String.format("%.2f", getSalesTax()))
                .append("\n");

        builder.append("Order Total: $")
                .append(String.format("%.2f", getTotal()));

        return builder.toString();
    }
}