package com.restaurant.restaurant_web.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ADMIN, CUSTOMER, WAITER;

    @Override
    public String getAuthority(){
        return name();
    }
}
