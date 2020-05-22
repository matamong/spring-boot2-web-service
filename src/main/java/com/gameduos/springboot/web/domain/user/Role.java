package com.gameduos.springboot.web.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Role {
    GUEST("guest"),
    USER("user"),
    ADMIN("admin"),
    MASTER("master");

    private final String ROLE_PREFIX = "ROLE_";
    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getRoleType() { return ROLE_PREFIX + name.toUpperCase(); }

    public String getValue() { return name; }

    public boolean isEquals(String authority) {
        return this.name.equals(authority);
    }
}
