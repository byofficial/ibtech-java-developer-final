package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Product;
import com.ibtech.mall.database.entity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ProductManager extends BaseManager<Product> implements ICrudRepo<Product> {
    private static Logger logger = LoggerFactory.getLogger(ProductManager.class);

    @Override
    public boolean save(Product product) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Product(productName, imagePath, salesPrice, categoryId, description, longDescription, status) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getImagePath());
            statement.setDouble(3, product.getSalesPrice());
            statement.setLong(4, product.getCategoryId());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getLongDescription());
            statement.setInt(7, Integer.parseInt(product.getStatus().toString()));
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Product created in database! Affected: " + affected);
        return affected > 0;
    }

    @Override
    public boolean update(Product product, long id) {
        int affected = 0;
        try {
            connect();
            String sql = "UPDATE Product set productName = ?, imagePath=?,salesPrice = ?, categoryId=?, description=?, longDescription=?, staus=? WHERE productId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getImagePath());
            statement.setDouble(3, product.getSalesPrice());
            statement.setLong(4, product.getCategoryId());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getLongDescription());
            statement.setInt(7, Integer.parseInt(product.getStatus().toString()));
            statement.setLong(8, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Product updated in database! Affected: " + affected);
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
            logger.error(e.getMessage());
        }
        logger.info("All Products listed successfully!");
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
            logger.error(e.getMessage());
        }

        if (product != null) {
            logger.info("Product found successfully! Category id: " + product.getProductId());
        } else {
            logger.info("Product was not found!");
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
            logger.error(e.getMessage());
        }

        logger.info("Products in this category have been successfully listed!");
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
            logger.error(e.getMessage());
        }

        logger.info("Category successfully deleted!");
        return affected > 0;
    }

    @Override
    protected Product parse(ResultSet resultSet) throws Exception {
        long productId = resultSet.getLong("productId");
        String productName = resultSet.getString("productName");
        String imagePath = resultSet.getString("imagePath");
        double salesPrice = resultSet.getDouble("salesPrice");
        long categoryId = resultSet.getLong("categoryId");
        String description = resultSet.getString("description");
        String longDescription = resultSet.getString("longDescription");
        Status status = Status.fromInteger(resultSet.getInt("status"));
        Product product = new Product(productId, productName, imagePath, salesPrice, description, longDescription, status);
        product.setCategoryId(categoryId);
        return product;
    }
}
