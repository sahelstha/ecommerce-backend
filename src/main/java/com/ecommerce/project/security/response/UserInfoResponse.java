package com.ecommerce.project.security.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserInfoResponse {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String jwtToken;

    @Getter
    @Setter
    public String username;

    @Getter
    @Setter
    public List<String> roles;

    public UserInfoResponse(Long id, String username, String jwtToken, List<String> roles) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }
    public UserInfoResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
