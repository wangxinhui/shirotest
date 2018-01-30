package com.shirotest.chapter6.service;

import com.shirotest.chapter6.entity.Role;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class RoleServiceImpl implements RoleService{
    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {

    }

    @Override
    public void correlationPermission(Long roleId, Long... permisionIds) {

    }

    @Override
    public void uncorrelationPermission(Long roleId, Long... permisionIds) {

    }
}
