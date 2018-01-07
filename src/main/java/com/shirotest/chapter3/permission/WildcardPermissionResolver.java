package com.shirotest.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

public class WildcardPermissionResolver implements PermissionResolver{
    @Override
    public Permission resolvePermission(String s) {
        if (s.startsWith("+")){
            return null;
        }
        return null;
    }
}
