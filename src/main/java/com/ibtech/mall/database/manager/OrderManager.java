package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Orders;
import com.ibtech.mall.database.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderManager extends BaseManager<Orders> {

    public boolean save(Orders orders) {
        int affected = 0;
        try {
            connect();
            String sql = "INSERT INTO Orders(productId, accountId, quantity) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, orders.getProductId());
            statement.setLong(2, orders.getAccountId());
            statement.setLong(3, orders.getQuantity());
            affected = statement.executeUpdate();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affected > 0;
    }

    public List<Orders> accountOrders(long orderId) {
        List<Orders> list = new ArrayList<>();
        try {
            connect();
            String sql = "select * from Orders WHERE accountId=? order by Orders.orderId desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                ProductManager productManager = new ProductManager();
                long productId = rs.getLong("productId");
                Product product = productManager.findById(productId);
                orders.setOrderId(rs.getLong("orderId"));
                orders.setProductId(productId);
                orders.setProductName(product.getProductName());
                orders.setDescription(product.getDescription());
                orders.setLongDescription(product.getLongDescription());
                orders.setCategoryId(product.getCategoryId());
                orders.setSalesPrice(product.getSalesPrice() * rs.getLong("quantity"));
                orders.setQuantity(rs.getLong("quantity"));
                list.add(orders);
            }
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cancelOrder(long orderId) {
        try {
            connect();
            String sql = "delete from Orders WHERE orderId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, orderId);
            statement.executeQuery();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Orders parse(ResultSet resultSet) throws Exception {
        long orderId = resultSet.getLong("orderId");
        long quantity = resultSet.getLong("quantity");
        return new Orders(orderId, quantity);
    }

}
