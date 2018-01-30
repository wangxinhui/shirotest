package com.shirotest.chapter6.service;

import com.shirotest.chapter6.entity.Permission;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public interface PermissionService {
    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);
}
