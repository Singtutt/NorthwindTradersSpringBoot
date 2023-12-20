package com.pluralsight;

import com.pluralsight.dao.ProductDAOInterface;
import com.pluralsight.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class NorthwindApplication implements CommandLineRunner {
    private final ProductDAOInterface pDAO;

    @Autowired
    public NorthwindApplication(ProductDAOInterface pDAO) {
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
                        \t1. List Products
                        \t2. Add Product Entry
                        \t3. Update Product Entry
                        \t4. Delete Product Entry
                        \t0. Exit
                        Enter your choice (0-4):\s""");
                if (scan.hasNextInt()) {
                    input = scan.nextInt();

                    switch (input) {
                        case 1:
                            System.out.println("\t=Listing Products=");
                            pDAO.getAll().forEach(System.out::println);
                            break;
                        case 2:
                            productADD(scan);
                            break;
                        case 3:
                            productUPDATE(scan);
                            break;
                        case 4:
                            productDELETE(scan);
                            break;
                        case 0:
                            System.out.println("\tExiting MainApp...");
                            break;
                    }
                } else {
                    System.out.println("Invalid Input. Please enter a valid Input (0-4)");
                    scan.next();
                    input = -1;
                }
            } while (input != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void productADD(Scanner scan) {
        System.out.println("""
                \nPlease Enter Product Details...

                \tProduct Name:\s""");
        scan.nextLine();
        String productName = scan.nextLine();
        System.out.println("\tSupplier ID: ");
        int supplierID = scan.nextInt();
        System.out.println("\tCategory ID: ");
        int categoryID = scan.nextInt();
        scan.nextLine();
        System.out.println("\tQuantity Per Unit: ");
        String quantityPerUnit = scan.nextLine();
        System.out.println("\tUnit Price: ");
        double unitPrice = scan.nextDouble();
        System.out.println("\tUnits In Stock: ");
        int unitsInStock = scan.nextInt();
        System.out.println("\tUnits On Order: ");
        int unitsOnOrder = scan.nextInt();
        System.out.println("\tReorder Level: ");
        int reorderLevel = scan.nextInt();
        boolean discontinued;
        while (true) {
            System.out.println("\tDiscontinued (True or False): ");
            String dInput = scan.next().toLowerCase();
            if (dInput.equals("true")) {
                discontinued = true;
                break;
            } else if (dInput.equals("false")) {
                discontinued = false;
                break;
            } else {
                System.out.println("Please enter a valid option (True or False)");
            }
        }

        Product newProduct = new Product(0, productName, supplierID, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
        pDAO.add(newProduct);
        System.out.println("\t=Product Successfully Added!=");
    }

    private void productUPDATE(Scanner scan) {
        System.out.println("""
                \nPlease Enter Product (ID) To Start Updating Entry...

                \tProduct ID:\s""");
        int updatingByID = scan.nextInt();
        scan.nextLine();

        Product dbProductEntry = pDAO.byID(updatingByID);
        if (dbProductEntry != null) {
            System.out.println("\tProduct Name:");
            String newProductName = scan.nextLine();
            System.out.println("\tSupplier ID: ");
            int newSupplierID = scan.nextInt();
            scan.nextLine();
            System.out.println("\tCategory ID: ");
            int newCategoryID = scan.nextInt();
            scan.nextLine();
            System.out.println("\tQuantity Per Unit: ");
            String newQuantityPerUnit = scan.nextLine();
            System.out.println("\tUnit Price: ");
            double newUnitPrice = scan.nextDouble();
            scan.nextLine();
            System.out.println("\tUnits In Stock: ");
            int newUnitsInStock = scan.nextInt();
            scan.nextLine();
            System.out.println("\tUnits On Order: ");
            int newUnitsOnOrder = scan.nextInt();
            scan.nextLine();
            System.out.println("\tReorder Level: ");
            int newReorderLevel = scan.nextInt();
            scan.nextLine();
            boolean newDiscontinued;
            while (true) {
                System.out.println("Discontinued (True or False): ");
                String dInput = scan.next().toLowerCase();
                if (dInput.equals("true")) {
                    newDiscontinued = true;
                    break;
                } else if (dInput.equals("false")) {
                    newDiscontinued = false;
                    break;
                } else {
                    System.out.println("Please enter a valid option (True or False)");
                }
            }

            Product updatedProduct = new Product(updatingByID, newProductName, newSupplierID, newCategoryID, newQuantityPerUnit, newUnitPrice, newUnitsInStock, newUnitsOnOrder, newReorderLevel, newDiscontinued);
            pDAO.update(updatedProduct);
            System.out.println("Product Successfully Updated!");
        } else {
            System.out.println("ERROR | Product ID Not Found!");
        }
    }

    private void productDELETE(Scanner scan) {
        System.out.println("""
                \nPlease Enter Product (ID) To Delete Entry...

                \tProduct ID:\s""");
        int deletingByID = scan.nextInt();
        pDAO.delete(deletingByID);
        System.out.println("Product Entry Successfully Removed!");
    }

}
