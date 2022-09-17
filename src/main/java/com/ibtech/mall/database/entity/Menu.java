package com.ibtech.mall.database.entity;

import com.ibtech.mall.database.entity.enums.Status;

public class Menu {
    private long menuId;
    private String menuName;
    private String menuUrl;
    private String menuActive;
    private Status status;

    public Menu() {
    }

    public Menu(long menuId, String menuName, String menuUrl, String menuActive, Status status) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuActive = menuActive;
        this.status = status;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuActive() {
        return menuActive;
    }

    public void setMenuActive(String menuActive) {
        this.menuActive = menuActive;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
