package org.example.managementproduct.model;

import org.example.managementproduct.entity.Category;
import org.example.managementproduct.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productModel extends basemodel{

    public List<Product> getAllProduct() throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, p.quantity, p.color, c.id as categoryId, c.name as categoryName\n" +
                "FROM product p\n" +
                "JOIN category c ON p.category = c.id;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            long price = rs.getLong("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");

            int categoryId = rs.getInt("categoryId");
            String categoryName = rs.getString("categoryName");
            Category category = new Category(categoryId, categoryName);

            Product product = new Product(id, name, price, quantity, color, category);
            products.add(product);
        }
        return products;
    }
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    public List<Category> getAllCategory() throws SQLException {
        String sql = "SELECT * FROM category";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Category category = new Category(id, name);
            categories.add(category);
        }
        return categories;
    }

    public void insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product(name, price, quantity, color, category) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, product.getName());
        ps.setLong(2, product.getPrice());
        ps.setInt(3, product.getQuantity());
        ps.setString(4, product.getColor());
        ps.setInt(5, product.getCategory().getId());
        ps.executeUpdate();
    }


}
