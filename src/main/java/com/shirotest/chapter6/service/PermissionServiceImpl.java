package com.shirotest.chapter6.service;

import com.shirotest.chapter6.dao.PermissionDAO;
import com.shirotest.chapter6.dao.PermissionDAOImpl;
import com.shirotest.chapter6.entity.Permission;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDAO permissionDAO = new PermissionDAOImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDAO.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDAO.deletePermission(permissionId);
    }
}
