package ru.dins.services;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by key on 21.03.2017.
 */
public enum Role implements GrantedAuthority {
    USER;
    @Override
    public String getAuthority() {
        return name();
    }
}
