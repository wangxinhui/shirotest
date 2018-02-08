package com.shirotest.chapter6.realm;

import com.shirotest.chapter6.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @Author: by Administrator on 2018/2/8.
 */
public class MyRealm3 implements Realm{
    @Override
    public String getName() {
        return "c";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = new User("zhang","123");
        return new SimpleAuthenticationInfo(user,"123",getName());
    }
}
