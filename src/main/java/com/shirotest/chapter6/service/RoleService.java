package com.shirotest.chapter6.service;

import com.shirotest.chapter6.entity.Role;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public interface RoleService {
    Role createRole(Role role);
    void deleteRole (Long roleId);

    /**
     * 添加角色-权限关系
     * @param roleId
     * @param permisionIds
     */
    void correlationPermission(Long roleId,Long... permisionIds);

    /**
     * 删除角色-权限关系
     * @param roleId
     * @param permisionIds
     */
    void uncorrelationPermission(Long roleId,Long... permisionIds);
}
