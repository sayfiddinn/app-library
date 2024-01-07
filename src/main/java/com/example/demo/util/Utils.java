package com.example.demo.util;


import com.example.demo.entity.enums.Authority;

import java.util.Arrays;
import java.util.List;

public interface Utils {

    String BEARER = "Bearer ";
    String BASIC = "Basic ";


    List<Authority> superAdminAuthority = Arrays.asList(Authority.values());
    List<Authority> adminAuthority = Arrays.asList(
            Authority.ADD_MODERATOR,
            Authority.DELETE_MODERATOR,
            Authority.SHOW_MODERATOR,
            Authority.SHOW_PROFILE,
            Authority.EDITE_PROFILE,
            Authority.DELETE_PROFILE,
            Authority.ADD_ROLE,
            Authority.CHANGE_ROLE,
            Authority.SHOW_ROLE,
            Authority.SHOW_USER,
            Authority.BOOK_CRUD);
    List<Authority> moderAuthority = Arrays.asList(
            Authority.SHOW_PROFILE,
            Authority.EDITE_PROFILE,
            Authority.DELETE_PROFILE,
            Authority.BOOK_CRUD);
    List<Authority> userAuthority = Arrays.asList(
            Authority.SHOW_PROFILE,
            Authority.EDITE_PROFILE,
            Authority.DELETE_PROFILE);

}
