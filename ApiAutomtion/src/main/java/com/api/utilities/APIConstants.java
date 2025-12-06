package com.api.utilities;

public class APIConstants {
    public static final String BASE_URL = ConfigLoader.getProperty("base.url");

    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String PRODUCTS_ENDPOINT = "/products";
    public static final String UPDATE_PRODUCT_ENDPOINT = "/products/{id}";

    public static String authToken;
    public static int createdProductId;
    public static Double createdProductPrice;
    public static String createdProductitle;
    public static Double PriceProduct;
    public static String TitleProduct;
}
