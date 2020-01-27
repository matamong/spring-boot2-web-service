package com.gameduos.springboot.web.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public enum SocialType {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao");

    private final String ROLE_PREFIX = "ROLE_";
    private final String name;

    SocialType(String name) {
        this.name = name;
    }

    public String getRoleType() { return ROLE_PREFIX + name.toUpperCase(); }

    public String getValue() { return name; }

    public boolean isEquals(String authority) {
        return this.name.equals(authority);
    }
}
