package com.ibtech.mall.database.entity.enums;

public enum FeaturedCategory {
    ACTIVE,
    PASSIVE;


    public static FeaturedCategory fromInteger(long x) {
        switch ((int) x) {
            case 0:
                return ACTIVE;
            case 1:
                return PASSIVE;
        }
        return null;
    }
}
