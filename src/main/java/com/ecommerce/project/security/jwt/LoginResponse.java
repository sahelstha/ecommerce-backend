package com.ecommerce.project.security.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LoginResponse {
    @Getter
    @Setter
    private String jwtToken;

    @Getter
    @Setter
    public String username;

    @Getter
    @Setter
    public List<String> roles;

    public LoginResponse(String username, String jwtToken, List<String> roles) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }
}
