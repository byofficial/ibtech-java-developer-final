package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager extends BaseManager<Account> {

    public Account login(String accountName, String accountPassword) {
        Account account = null;
        try {
            connect();
            String sql = "SELECT * FROM Account WHERE accountName=? and accountPassword=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountName);
            statement.setString(2, accountPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = parse(resultSet);
            }
            disconnect();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    protected Account parse(ResultSet resultSet) throws Exception {
        long accountId = resultSet.getLong("accountId");
        String accountName = resultSet.getString("accountName");
        String accountPassword = resultSet.getString("accountPassword");
        return new Account(accountId, accountName, accountPassword);

    }
}
