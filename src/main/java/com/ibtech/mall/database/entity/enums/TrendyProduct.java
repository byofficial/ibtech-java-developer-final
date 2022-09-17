package com.ibtech.mall.database.entity.enums;

public enum TrendyProduct {
    ACTIVE,
    PASSIVE;

    public static TrendyProduct fromInteger(long x) {
        switch ((int) x) {
            case 0:
                return ACTIVE;
            case 1:
                return PASSIVE;
        }
        return null;
    }

    public static long parseLong(TrendyProduct x) {
        switch (x) {
            case ACTIVE:
                return 0;
            case PASSIVE:
                return 1;
        }
        return -1;
    }

    public static String toString(TrendyProduct x) {
        switch (x) {
            case ACTIVE:
                return "0";
            case PASSIVE:
                return "1";
        }
        return "-1";
    }
}
