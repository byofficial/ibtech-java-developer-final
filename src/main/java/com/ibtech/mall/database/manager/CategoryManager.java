package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Category;
import com.ibtech.mall.database.entity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CategoryManager extends BaseManager<Category> implements ICrudRepo<Category> {
    private static Logger logger = LoggerFactory.getLogger(CategoryManager.class);

    @Override
    public boolean save(Category category) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Category(categoryName, status) values(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, Integer.parseInt(category.getStatus().toString()));
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Category created in database! Affected: " + affected);
        return affected > 0;
    }

    @Override
    public boolean update(Category category, long id) {
        int affected = 0;
        try {
            connect();
            String sql = "UPDATE Category set categoryName = ?, status = ? WHERE categoryId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, Integer.parseInt(category.getStatus().toString()));
            statement.setLong(3, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Category updated in database! Affected: " + affected);
        return affected > 0;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = null;
        try {
            connect();
            String sql = "SELECT * FROM Category";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            categoryList = parseList(rs);
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.info("Category listed success!");
        return categoryList;

    }

    @Override
    public Category findById(long id) {
        Category category = null;
        try {
            connect();
            String sql = "SELECT * FROM Category WHERE categoryId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = parse(resultSet);
            }
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (category != null) {
            logger.info("Category found successfully! Category id: " + category.getCategoryId());
        } else {
            logger.info("Category was not found!");
        }

        return category;
    }

    @Override
    public boolean delete(long id) {
        int affected = 0;
        try {
            connect();
            String sql = "DELETE FROM Category WHERE categoryId = ?";
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
    protected Category parse(ResultSet resultSet) throws Exception {
        long categoryId = resultSet.getLong("categoryId");
        String categoryName = resultSet.getString("categoryName");
        Status status = Status.fromInteger(resultSet.getInt("status"));
        return new Category(categoryId, categoryName, status);
    }

}
