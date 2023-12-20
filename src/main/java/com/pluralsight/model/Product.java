package com.pluralsight.model;

import java.util.Objects;

public class Product {
    private int productID, supplierID, categoryID, unitsInStock, unitsOnOrder, reorderLevel;
    private String productName, quantityPerUnit;
    private double unitPrice;
    private boolean discontinued;

    public Product(int productID, String productName, int supplierID, int categoryID, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, boolean discontinued) {
        this.productID = productID;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.productName = productName;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.discontinued = discontinued;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProductID() == product.getProductID() &&
               Double.compare(product.getUnitPrice(), getUnitPrice()) == 0 &&
               Objects.equals(getProductName(), product.getProductName()) &&
               getCategoryID() == product.getCategoryID();
    }
    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }
    @Override
    public String toString() {
        String inputDiscontinued = discontinued ? "True" : "False"; // For readability within UserInterface functionality, instead of 0/1
        return String.format("| %-3d | %-40s | %-10d | %-10d | %-20s | %7.2f | %-10d | %-10d | %-10d | %12s |%n",
                productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, inputDiscontinued);
    }
}
