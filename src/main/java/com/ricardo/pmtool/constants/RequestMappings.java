package com.ricardo.pmtool.constants;

public class RequestMappings
{
    public static final String PRODUCT_DETAILS = "api/product/{code}";
    public static final String PRODUCT_IMAGE_URL = "api/productImage/{code}";
    public static final String SOLR_PRODUCTS = "api/solr/products";
    public static final String SOLR_SEARCH_PRODUCTS = "api/solr/searchProducts";
    public static final String ADD_TO_CART = "/api/addtocart";
    public static final String CURRENT_CART = "/api/currentCart";
    public static final String CURRENT_CART_COUNT = "/api/currentCart/count";
    public static final String CART_DELETE_ENTRY = "/api/currentCart/deleteEntry/{productCode}";
    public static final String CART_UPDATE = "/api/currentCart/update";
    public static final String PLACE_ORDER = "/api/placeOrder";
    public static final String LAST_PLACED_ORDER = "/api/lastPlacedOrder";

    public static final String ALL_PRODUCTS = "/api/admin/allProducts";
    public static final String TEST_GET = "/api/testget";
    public static final String PRODUCTS = "/api/admin/products";
    public static final String UPLOAD_PRODUCT_IMAGE = "api/upload/productImage/{code}";
    public static final String ADMIN_ORDERS = "/api/admin/orders";
    public static final String ORDER_DETAILS = "/api/admin/order/{code}";
    public static final String DELETE_ORDER = "/api/admin/delete/order/{code}";
    public static final String ADMIN_CREATE_USER = "/api/createUser";
    public static final String ADMIN_CREATE_PRODUCT = "/api/admin/createProduct";
    public static final String ADMIN_UPDATE_PRODUCT = "/api/admin/updateProduct";
    public static final String ADMIN_USERS = "/api/admin/users";

    public static final String SOLR_PRODUCTS_INDEXING = "api/solr/products/index";
}
