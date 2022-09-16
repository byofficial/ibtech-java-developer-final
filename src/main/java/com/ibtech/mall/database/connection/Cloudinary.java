package com.ibtech.mall.database.connection;


import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

public class Cloudinary {

    private static final String CLOUDINARY_CLOUD_NAME = System.getenv("CLOUDINARY_CLOUD_NAME");
    private static final String CLOUDINARY_API_KEY = System.getenv("CLOUDINARY_API_KEY");
    private static final String CLOUDINARY_API_SECRET = System.getenv("CLOUDINARY_API_SECRET");

    public Cloudinary(Map map) {

    }

    public Cloudinary() {
    }

    public void connect() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_CLOUD_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET));

    }
}
