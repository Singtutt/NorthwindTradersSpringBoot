package com.pluralsight.service;

import com.pluralsight.dao.ProductDAO;
import com.pluralsight.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    private final ProductDAO simpleProductDAO;

    @Autowired
    public ProductServices(ProductDAO simpleProductDAO) {
        this.simpleProductDAO = simpleProductDAO;
    }

    public void addProduct(Product product) {
        simpleProductDAO.add(product);
    }

    public List<Product> getAllProducts() {
        return simpleProductDAO.getAll();
    }
}
