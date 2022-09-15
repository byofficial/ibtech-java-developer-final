package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ProductManager extends BaseManager<Product> implements ICrudRepo<Product> {

    @Override
    public boolean save(Product product) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Product(productName, imagePath, salesPrice, categoryId) values(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getImagePath());
            statement.setDouble(3, product.getSalesPrice());
            statement.setLong(4, product.getCategoryId());
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affected > 0;
    }

    @Override
    public boolean update(Product product, long id) {
        int affected = 0;
        try {
            connect();
            String sql = "UPDATE Product set productName = ?, imagePath=?,salesPrice = ?, categoryId=? WHERE productId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getImagePath());
            statement.setDouble(3, product.getSalesPrice());
            statement.setLong(4, product.getCategoryId());
            statement.setLong(5, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affected > 0;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = null;
        try {
            connect();
            String sql = "SELECT p.*, c.categoryname FROM Product as p join Category c on p.categoryId = c.categoryid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            productList = parseList(rs);
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;

    }

    @Override
    public Product findById(long id) {
        Product product = null;
        try {
            connect();
            String sql = "SELECT * FROM Product WHERE productId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = parse(resultSet);
            }
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    public List<Product> findByCategoryId(long id) {
        List<Product> productList = null;
        try {
            connect();
            String sql = "SELECT * FROM Product WHERE categoryId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            productList = parseList(rs);
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public boolean delete(long id) {
        int affected = 0;
        try {
            connect();
            String sql = "DELETE FROM Product WHERE productId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affected > 0;
    }

    @Override
    protected Product parse(ResultSet resultSet) throws Exception {
        long productId = resultSet.getLong("productId");
        String productName = resultSet.getString("productName");
        String imagePath = resultSet.getString("imagePath");
        double salesPrice = resultSet.getDouble("salesPrice");
        long categoryId = resultSet.getLong("categoryId");
        Product product = new Product(productId, productName, imagePath, salesPrice);
        product.setCategoryId(categoryId);
        return product;
    }
}
