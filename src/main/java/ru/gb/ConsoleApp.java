package ru.gb;

import org.apache.commons.cli.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.cart.Cart;
import ru.gb.product.Product;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleApp {
    private Cart cart;
    private Scanner scanner;

    private static final String HELP = "Welcome to our shop! Here is the list of the supported actions:\n" +
                                        "1. Show all products\n" +
                                        "2. Add a product\n" +
                                        "3. Show the product\n" +
                                        "4. Remove a product\n" +
                                        "5. Clear the cart\n" +
                                        "6. Exit\n";

    private static final String ACTION_SELECT = "Please select the action: ";
    private static final String TABLE_HEADER = "|==== ID ====|========= Title =========|====== Cost ======|";

    public void init(Cart cart) {
        this.cart = cart;
        scanner = new Scanner(System.in);
    }


    public void run() {
        System.out.println(HELP);
        System.out.print(ACTION_SELECT);
        int action;

        while ((action = scanner.nextInt()) != 6) {
            switch (action) {
                case 1: {
                    showProducts();
                    break;
                }
                case 2: {
                    addProduct();
                    break;
                }
                case 3: {
                    showProduct();
                    break;
                }
                case 4: {
                    removeProduct();
                    break;
                }
                case 5: {
                    clearCart();
                    break;
                }
                default: {
                    System.out.println("The specified action doesn't exist.");
                }
            }

            System.out.print("\n" + ACTION_SELECT);
        }

        System.exit(0);
    }

    private void addProduct() {
        List<String> availableProducts = cart.getAvailableProductIds();
        askForProductId(availableProducts);
        cart.addToCart(scanner.next());
        showProducts();
    }

    private void removeProduct() {
        List<String> availableProducts = cart.getAddedProductIds();
        askForProductId(availableProducts);
        cart.removeFromCart(scanner.next());
        showProducts();
    }

    private void clearCart() {
        cart.clearCart();
        showProducts();
    }

    private void showProducts() {
        displayProductsTable(cart.getCart());
    }

    private void showProduct() {
        List<String> availableProducts = cart.getAddedProductIds();
        askForProductId(availableProducts);
        Product product = cart.getFromCartById(scanner.next());
        displayProductsTable(List.of(product));
    }

    private void displayProductsTable(List<Product> products) {
        System.out.println(TABLE_HEADER);

        for (Product product: products) {
            System.out.format("| %-10s | %-23s | %-16f |\n", product.getId(), product.getName(), product.getCost());
        }

        System.out.println(TABLE_HEADER);
    }

    private void askForProductId(List<String> productIds) {
        System.out.print("Please specify the product ID (" + String.join(", ", productIds) + "): ");
    }
}
