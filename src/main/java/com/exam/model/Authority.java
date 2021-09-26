package com.exam.model;

import org.springframework.security.core.GrantedAuthority;

// implementing Granted authority and then implementing GrantedAuthority interface
public class Authority implements GrantedAuthority {

    private String authority;

//Generating authorities constructor
    public Authority(String authority) {
        this.authority = authority;
    }

    //implementing method of the interface
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
