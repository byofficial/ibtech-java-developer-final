package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CategoryManager extends BaseManager<Category> implements ICrudRepo<Category> {

    @Override
    public boolean save(Category category) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Category(categoryName) values(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affected > 0;
    }

    @Override
    public boolean update(Category category, long id) {
        int affected = 0;
        try {
            connect();
            String sql = "UPDATE Category set categoryName = ? WHERE categoryId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setLong(2, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
        }

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return affected > 0;
    }

    @Override
    protected Category parse(ResultSet resultSet) throws Exception {
        long categoryId = resultSet.getLong("categoryId");
        String categoryName = resultSet.getString("categoryName");
        return new Category(categoryId, categoryName);
    }

}
