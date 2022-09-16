package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.entity.Account;
import com.ibtech.mall.web.servlet.user.LoginUserServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager extends BaseManager<Account> {
    private static Logger logger = LoggerFactory.getLogger(AccountManager.class);

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
            if (account != null) {
                logger.info("Account login was successful! Accound ID: " + account.getAccountId());
            } else {
                logger.info("Account login failed! Account Name: " + accountName);
            }

        } catch (SQLException e) {
            logger.error("SQL error. " + e.getMessage());

        } catch (Exception e) {
            logger.error("Login error. " + e.getMessage());
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
