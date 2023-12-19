package com.pluralsight.dao;

import com.pluralsight.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO implements ProductDAOInterface {
    private List<Product> products;

    public ProductDAO() {
        products = new ArrayList<>();
        products.add(new Product(1, "Product1", "Category1", 20.00));
        products.add(new Product(1, "Product2", "Category2", 15.00));
        products.add(new Product(1, "Product3", "Category3", 5.00));
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
