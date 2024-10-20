package com.shoppingapp;

import java.util.Scanner;

public class ShoppingApplication {
    public static void main(String[] args) {
        // Initialize services
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        Scanner scanner = new Scanner(System.in);

        // Main Menu
        while (true) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                adminMenu(scanner, productService, orderService);
            } else if (choice == 2) {
                customerMenu(scanner, customerService, productService, orderService);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void adminMenu(Scanner scanner, ProductService productService, OrderService orderService) {
        while (true) {
            // Display Admin Menu
            System.out.println("Admin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Update Order Status");
            System.out.println("5. View Orders");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            // Admin Menu Operations
            switch (choice) {
                case 1: // Add Product
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Stock Quantity: ");
                    int stock = scanner.nextInt();

                    Product product = new Product(id, name, price, stock);
                    productService.addProduct(product);
                    break;

                case 2: // Remove Product
                    System.out.print("Enter Product ID to remove: ");
                    int productId = scanner.nextInt();
                    productService.removeProduct(productId);
                    break;

                case 3: // View Products
                    System.out.println("Products:");
                    for (Product p : productService.getAllProducts()) {
                        System.out.println(p);
                    }
                    break;

                case 4: // Update Order Status
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String status = scanner.nextLine();
                    orderService.updateOrderStatus(orderId, status);
                    break;

                case 5: // View Orders
                    System.out.println("Orders:");
                    for (Order order : orderService.getAllOrders()) {
                        System.out.println(order);
                    }
                    break;

                case 6: // Return to main menu
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void customerMenu(Scanner scanner, CustomerService customerService, ProductService productService, OrderService orderService) {
        while (true) {
            // Display Customer Menu
            System.out.println("Customer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            // Customer Menu Operations
            switch (choice) {
                case 1: // Create Customer
                    System.out.print("Enter User ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    Customer customer = new Customer(id, username, email, address);
                    customerService.addCustomer(customer);
                    break;

                case 2: // View Customers
                    System.out.println("Customers:");
                    for (Customer cust : customerService.getAllCustomers()) {
                        System.out.println("User ID: " + cust.getUserId() + ", Username: " + cust.getUsername() + ", Email: " + cust.getEmail() + ", Address: " + cust.getAddress());
                    }
                    break;

                case 3: // Place Order
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    Customer cust = customerService.findCustomerById(customerId);

                    if (cust != null) {
                        Order order = new Order(orderService.getAllOrders().size() + 1, cust);
                        while (true) {
                            System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                            int productId = scanner.nextInt();
                            if (productId == -1) break;

                            System.out.print("Enter Quantity: ");
                            int quantity = scanner.nextInt();

                            Product product = productService.findProductById(productId);
                            if (product != null && product.getStockQuantity() >= quantity) {
                                order.addProduct(product, quantity);
                                product.setStockQuantity(product.getStockQuantity() - quantity);  // Deduct stock
                            } else {
                                System.out.println("Product not found or insufficient stock.");
                            }
                        }
                        orderService.placeOrder(order);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4: // View Orders
                    System.out.print("Enter Customer ID: ");
                    customerId = scanner.nextInt();
                    cust = customerService.findCustomerById(customerId);

                    if (cust != null) {
                        System.out.println("Orders:");
                        for (Order order : orderService.getOrdersByCustomer(cust)) {
                            System.out.println(order);
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 5: // View Products
                    System.out.println("Products:");
                    for (Product p : productService.getAllProducts()) {
                        System.out.println(p);
                    }
                    break;

                case 6: // Return to main menu
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
