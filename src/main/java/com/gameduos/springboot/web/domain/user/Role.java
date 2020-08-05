package com.gameduos.springboot.web.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Role {
    GUEST("guest"),
    USER("user"),
    ADMIN("admin"),
    MASTER("master"),
    INTERVIEWER("interviewer");

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

    public Role findRole(String roleType){
        if(roleType.equals("ROLE_USER"))
            return Role.USER;
        if(roleType.equals("ROLE_ADMIN"))
            return Role.ADMIN;
        if(roleType.equals("ROLE_INTERVIEWER"))
            return Role.INTERVIEWER;
        return Role.USER;
    }
}
