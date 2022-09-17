package com.ibtech.mall.database.entity;

import com.ibtech.mall.database.entity.enums.Status;

public class Account implements IEntity {
    private long accountId;
    private String accountName;
    private String accountPassword;
    private String accountEmail;
    private Status status;

    public Account() {
    }

    public Account(long accountId, String accountName, String accountPassword, String accountEmail, Status status) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountEmail = accountEmail;
        this.status = status;
    }

    public Account(long accountId, String accountName, String accountPassword, String accountEmail) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountEmail = accountEmail;

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

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public Status getStatus() {
        return status;
    }
}
