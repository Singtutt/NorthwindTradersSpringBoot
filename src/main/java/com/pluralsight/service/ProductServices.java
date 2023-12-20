package com.pluralsight.service;

import com.pluralsight.dao.ProductDAOInterface;
import com.pluralsight.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    private final ProductDAOInterface productDAO;

    @Autowired
    public ProductServices(ProductDAOInterface productDAO) {
        this.productDAO = productDAO;
    }
    public void addProduct(Product product) {
        productDAO.add(product);
    }
    public void updateProduct(Product product) {
        productDAO.update(product);
    }
    public void deleteProduct(int productID) {
        productDAO.delete(productID);
    }
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }
    public Product getProductByID(int productID) {
        return productDAO.byID(productID);
    }
}
