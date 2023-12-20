package com.pluralsight.dao;

import com.pluralsight.model.Product;

import java.util.List;

public interface ProductDAOInterface {
    void add(Product product);
    void update(Product product);
    void delete(int productID);
    List<Product> getAll();
    Product byID(int productID);
}
