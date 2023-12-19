package com.pluralsight;

import com.pluralsight.dao.ProductDAO;
import com.pluralsight.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class NorthwindApplication implements CommandLineRunner {
    private final ProductDAO pDAO;
    @Autowired
    public NorthwindApplication(ProductDAO pDAO) {
        this.pDAO = pDAO;
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
                        \nMain Menu:
                        1. List Products
                        2. Add Product
                        0. Exit
                        Enter your choice:\s""");
                input = scan.nextInt();

                switch (input) {
                    case 1:
                        System.out.println("\t=Listing Products=");
                        pDAO.getAll().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("""
                                \nPlease Enter Product Details...
                                                       
                                \tProduct ID:\s""");
                        int productId = scan.nextInt();
                        scan.nextLine();
                        System.out.print("\n\tProduct Name: ");
                        String productName = scan.nextLine();
                        System.out.print("\n\tCategory Name: ");
                        String category = scan.nextLine();
                        System.out.print("\n\tPrice: ");
                        double price = scan.nextDouble();
                        Product newProduct = new Product(productId, productName, category, price);
                        pDAO.add(newProduct);
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

}
