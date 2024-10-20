package com.shoppingapp;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

class Order {
    private int orderId;
    private Customer customer;
    private Map<Product, Integer> products;
    private String status;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new HashMap<>();
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<Product, Integer> getProducts() { return products; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append(", Status: ").append(status).append("\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            sb.append("  Product: ").append(entry.getKey().getName()).append(", Quantity: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}