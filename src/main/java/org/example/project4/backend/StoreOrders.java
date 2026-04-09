package org.example.project4.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> orders;

    public StoreOrders() {
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        if (order != null && !order.isEmpty()) {
            orders.add(order);
        }
    }

    public boolean removeOrder(int orderNumber) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getNumber() == orderNumber) {
                orders.remove(i);
                return true;
            }
        }
        return false;
    }

    public Order findOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void clear() {
        orders.clear();
    }

    public void exportOrders(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        for (Order order : orders) {
            writer.write(order.toString());
            writer.write("\n\n");
        }

        writer.close();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Order order : orders) {
            builder.append(order).append("\n\n");
        }

        return builder.toString();
    }
}