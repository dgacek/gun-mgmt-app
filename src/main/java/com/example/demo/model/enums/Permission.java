package com.example.demo.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    DICTIONARY_CREATE,
    DICTIONARY_READ,
    DICTIONARY_UPDATE,
    DICTIONARY_DELETE,

    MODEL_CREATE,
    MODEL_READ,
    MODEL_UPDATE,
    MODEL_DELETE,

    GUN_CREATE,
    GUN_READ,
    GUN_UPDATE,
    GUN_DELETE,

    ROLE_CREATE,
    ROLE_READ,
    ROLE_UPDATE,
    ROLE_DELETE,

    USER_CREATE,
    USER_READ,
    USER_UPDATE,
    USER_DELETE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
