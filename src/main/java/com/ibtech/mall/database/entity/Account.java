package com.ibtech.mall.database.entity;

public class Account implements IEntity {
    private long accountId;
    private String accountName;
    private String accountPassword;

    public Account() {
    }

    public Account(long accountId, String accountName, String accountPassword) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
