package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Menu;
import com.ibtech.mall.database.entity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MenuManager extends BaseManager<Menu> implements ICrudRepo<Menu> {
    private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

    @Override
    public boolean save(Menu menu) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Menu(menuName, menuUrl,menuActive,menuStatus) values(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, menu.getMenuName());
            statement.setString(2, menu.getMenuUrl());
            statement.setString(3, menu.getMenuActive());
            statement.setInt(4, Integer.parseInt(menu.getStatus().toString()));

            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Menu created in database! Affected: " + affected);
        return affected > 0;
    }

    @Override
    public boolean update(Menu menu, long id) {
        int affected = 0;
        try {
            connect();
            String sql = "UPDATE Menu set menuName=?, menuUrl=?,menuActive=?,menuStatus=? WHERE menuId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, menu.getMenuName());
            statement.setString(2, menu.getMenuUrl());
            statement.setString(3, menu.getMenuActive());
            statement.setInt(4, Integer.parseInt(menu.getStatus().toString()));
            statement.setLong(5, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Menu updated in database! Affected: " + affected);
        return affected > 0;
    }

    @Override
    public List<Menu> findAll() {
        List<Menu> menuList = null;
        try {
            connect();
            String sql = "SELECT * FROM Menu";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            menuList = parseList(rs);
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.info("Menu listed success!");
        return menuList;

    }

    @Override
    public Menu findById(long id) {
        Menu menu = null;
        try {
            connect();
            String sql = "SELECT * FROM Menu WHERE menuId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                menu = parse(resultSet);
            }
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        if (menu != null) {
            logger.info("Menu found successfully! Menu id: " + menu.getMenuId());
        } else {
            logger.info("Menu was not found!");
        }

        return menu;
    }

    @Override
    public boolean delete(long id) {
        int affected = 0;
        try {
            connect();
            String sql = "DELETE FROM Menu WHERE menuId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Menu successfully deleted!");
        return affected > 0;
    }

    @Override
    protected Menu parse(ResultSet resultSet) throws Exception {
        long menuId = resultSet.getLong("menuId");
        String menuName = resultSet.getString("menuName");
        String menuUrl = resultSet.getString("menuUrl");
        String menuActive = resultSet.getString("menuActive");
        Status status = Status.fromInteger(resultSet.getInt("status"));
        return new Menu(menuId, menuName, menuUrl, menuActive, status);
    }

}
