package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartManager extends BaseManager<Cart> {
    public List<Cart> getCartAllProducts(ArrayList<Cart> cartList) {
        List<Cart> carts = new ArrayList<>();
        try {
            connect();
            if (cartList.size() > 0) {
                for (Cart cartItem : cartList) {
                    String sql = "SELECT * FROM Product WHERE productId=?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setLong(1, cartItem.getProductId());
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        Cart cart = new Cart();
                        cart.setProductId(rs.getLong("productId"));
                        cart.setProductName(rs.getString("productName"));
                        cart.setCategoryId(rs.getLong("categoryId"));
                        cart.setImagePath(rs.getString("imagePath"));
                        cart.setSalesPrice(rs.getDouble("salesPrice") * cartItem.getQuantity());
                        cart.setQuantity(cartItem.getQuantity());
                        carts.add(cart);
                    }
                }
            }
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carts;
    }

    public double getCartTotalPriceCalculate(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            connect();
            if (cartList.size() > 0) {
                for (Cart cartItem : cartList) {
                    String sql = "select salesPrice from Product where productId=?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setLong(1, cartItem.getProductId());
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        sum += rs.getDouble("salesPrice") * cartItem.getQuantity();
                    }
                }
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public boolean save(Cart cart) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Cart(productId, productName, imagePath, salesPrice, quantity) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, cart.getProductId());
            statement.setString(2, cart.getProductName());
            statement.setString(3, cart.getImagePath());
            statement.setDouble(4, cart.getSalesPrice());
            statement.setLong(5, cart.getQuantity());
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affected > 0;
    }

    @Override
    protected Cart parse(ResultSet resultSet) throws Exception {
        long cartId = resultSet.getLong("cartId");
        long productId = resultSet.getLong("productId");
        String getProductName = resultSet.getString("getProductName");
        String imagePath = resultSet.getString("imagePath");
        double salesPrice = resultSet.getDouble("salesPrice");
        long quantity = resultSet.getLong("quantity");
        return new Cart(cartId, productId, getProductName, imagePath, salesPrice, quantity);
    }

}
