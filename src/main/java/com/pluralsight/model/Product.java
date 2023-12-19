package com.pluralsight.model;

import java.util.Objects;

public class Product {
    private int productId;
    private String name;
    private String category;
    private double price;

    public Product(int productId, String name, String category, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProductId() == product.productId &&
               Double.compare(product.price, price) == 0 &&
               Objects.equals(name, product.name) &&
               Objects.equals(category, product.category);
    }
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
    @Override
    public String toString() {
        return String.format("| %-3d | %-10s | %-10s | %5.2f |%n", productId, name, category, price);
    }
}
