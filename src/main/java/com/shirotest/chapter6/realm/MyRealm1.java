package com.shirotest.chapter6.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @Author: by Administrator on 2018/2/8.
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "a";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo("zhang","123",getName());
    }
}
