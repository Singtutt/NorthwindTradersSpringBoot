package com.pluralsight.dao;

import com.pluralsight.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCProductDAO implements ProductDAOInterface {
    private final DataSource ds;

    @Autowired
    public JDBCProductDAO(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Product byID(int productID) {
        String queryID = """
                SELECT *
                FROM Products
                WHERE ProductID = ?""";
        try (Connection conn = ds.getConnection();
             PreparedStatement prep = conn.prepareStatement(queryID)) {
            prep.setInt(1, productID);
            try (ResultSet rs = prep.executeQuery()) {
                if(rs.next()) {
                    return new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getInt("SupplierID"),
                            rs.getInt("CategoryID"),
                            rs.getString("QuantityPerUnit"),
                            rs.getDouble("UnitPrice"),
                            rs.getInt("UnitsInStock"),
                            rs.getInt("UnitsOnOrder"),
                            rs.getInt("ReorderLevel"),
                            rs.getBoolean("Discontinued")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Product product) {
        String queryUPDATE = """
                UPDATE Products
                SET ProductName = ?, SupplierID = ?, CategoryID = ?, QuantityPerUnit = ?, UnitPrice = ?, UnitsInStock = ?, UnitsOnOrder = ?, ReorderLevel = ?, Discontinued = ?
                WHERE ProductID = ?""";
        try (Connection conn = ds.getConnection();
        PreparedStatement prep = conn.prepareStatement(queryUPDATE)) {
            prep.setString(1, product.getProductName());
            prep.setInt(2, product.getSupplierID());
            prep.setInt(3, product.getCategoryID());
            prep.setString(4, product.getQuantityPerUnit());
            prep.setDouble(5, product.getUnitPrice());
            prep.setInt(6, product.getUnitsInStock());
            prep.setInt(7, product.getUnitsOnOrder());
            prep.setInt(8, product.getReorderLevel());
            prep.setBoolean(9, product.isDiscontinued());
            prep.setInt(10, product.getProductID());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int productID) {
        String queryDELETE = """
                DELETE
                FROM Products
                WHERE ProductID = ?""";
        try (Connection conn = ds.getConnection();
             PreparedStatement prep = conn.prepareStatement(queryDELETE)) {
            prep.setInt(1, productID);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Product product) {
        String queryADD = """
                INSERT INTO Products
                (ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        prepQuery(queryADD, product);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String queryALL = """
                SELECT *
                FROM Products""";
        try (Connection conn = ds.getConnection();
             PreparedStatement prep = conn.prepareStatement(queryALL);
             ResultSet rs = prep.executeQuery()) {
            while (rs.next()) {
                Product rsProduct = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("SupplierID"),
                        rs.getInt("CategoryID"),
                        rs.getString("QuantityPerUnit"),
                        rs.getDouble("UnitPrice"),
                        rs.getInt("UnitsInStock"),
                        rs.getInt("UnitsOnOrder"),
                        rs.getInt("ReorderLevel"),
                        rs.getBoolean("Discontinued")
                );
                products.add(rsProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private void prepQuery(String query, Product product) {
        try (Connection conn = ds.getConnection();
             PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setInt(1, product.getProductID());
            prep.setString(2, product.getProductName());
            prep.setInt(3, product.getSupplierID());
            prep.setInt(4, product.getCategoryID());
            prep.setString(5, product.getQuantityPerUnit());
            prep.setDouble(6, product.getUnitPrice());
            prep.setInt(7, product.getUnitsInStock());
            prep.setInt(8, product.getUnitsOnOrder());
            prep.setInt(9, product.getReorderLevel());
            prep.setBoolean(10, product.isDiscontinued());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
