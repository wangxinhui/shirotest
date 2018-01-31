package com.shirotest.chapter6.service;

import com.shirotest.chapter6.dao.RoleDAO;
import com.shirotest.chapter6.dao.RoleDAOImpl;
import com.shirotest.chapter6.entity.Role;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class RoleServiceImpl implements RoleService{

    private RoleDAO roleDAO = new RoleDAOImpl();
    @Override
    public Role createRole(Role role) {
        return roleDAO.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDAO.deleteRole(roleId);

    }

    @Override
    public void correlationPermission(Long roleId, Long... permissionIds) {
        roleDAO.correlationPermission(roleId,permissionIds);
    }

    @Override
    public void uncorrelationPermission(Long roleId, Long... permissionIds) {
        roleDAO.uncorrelationPermission(roleId,permissionIds);

    }
}
