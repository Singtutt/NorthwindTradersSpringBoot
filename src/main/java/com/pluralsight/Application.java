package com.pluralsight;

import com.pluralsight.model.Product;
import com.pluralsight.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final ProductServices ps;

    @Autowired
    public Application(ProductServices ps) {
        this.ps = ps;
    }

    @Override
    public void run(String... args) {
        mainMenuFlow();
    }

    private void mainMenuFlow() {
        try (Scanner scan = new Scanner(System.in)) {
            int input;
            do {
                System.out.println("""
                        Main Menu:
                        1. List Products
                        2. Add Product
                        0. Exit
                        Enter your choice:\s""");
                input = scan.nextInt();

                switch (input) {
                    case 1:
                        System.out.println("Listing Products:");
                        ps.getAllProducts().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("""
                                Enter Product Details...
                                                            
                                Product ID:\s""");
                        int productId = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Product Name: ");
                        String productName = scan.nextLine();
                        System.out.print("Category: ");
                        String category = scan.nextLine();
                        System.out.print("Price: ");
                        double price = scan.nextDouble();
                        Product newProduct = new Product(productId, productName, category, price);
                        ps.addProduct(newProduct);
                        System.out.println("Product added successfully!");
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (input != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
