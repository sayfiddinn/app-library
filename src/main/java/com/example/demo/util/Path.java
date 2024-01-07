package com.example.demo.util;

public interface Path {

    String API_VERSION = "/api/v1";
    String API_VERSION_ALL = "/api/v1/**";
    String BASE_PATH_USER = API_VERSION + "/user";
    String BASE_PATH_ADMIN = API_VERSION + "/admin";
    String BASE_PATH_MODER = API_VERSION + "/moder";
    String BASE_PATH_AUTH = API_VERSION + "/auth";
    String BASE_PATH_ROLE = API_VERSION + "/role";

    String NOT_FOUND = " Id not found";
    String SAVED = "Success saved";
    String UPDATED = "Success edited";
    String DELETED = "Success deleted";
    String NOT_ALLOWED = "Not allowed";
    String DOMAIN = "http://localhost:8080";


    String[] OPEN_PAGES = {
            BASE_PATH_AUTH + "/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };


}
