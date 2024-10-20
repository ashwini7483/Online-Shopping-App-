package com.shoppingapp;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer created successfully!");
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getUserId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}