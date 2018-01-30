package com.shirotest.chapter6.dao;

import com.shirotest.chapter6.entity.Permission;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public interface PermissionDAO {
    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);
}
