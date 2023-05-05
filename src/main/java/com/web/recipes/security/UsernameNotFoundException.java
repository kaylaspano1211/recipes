package com.web.recipes.security;

import javax.security.sasl.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {
    public UsernameNotFoundException(String msg) {
        super(msg);
    }
    public UsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}