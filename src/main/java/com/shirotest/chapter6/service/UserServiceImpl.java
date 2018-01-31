package com.shirotest.chapter6.service;

import com.shirotest.chapter6.dao.UserDAO;
import com.shirotest.chapter6.dao.UserDAOImpl;
import com.shirotest.chapter6.entity.User;

import java.util.Set;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class UserServiceImpl implements UserService{

    private UserDAO userDAO = new UserDAOImpl();
    private PasswordHelper passwordHelper = new PasswordHelper();
    @Override
    public User createUser(User user) {
        passwordHelper.encryptPassoword(user);
        return userDAO.createUser(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDAO.findOne(userId);
        if (user!=null){
           user.setPassword(newPassword);
           passwordHelper.encryptPassoword(user);
           userDAO.updateUser(user);
        }
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDAO.correlationRoles(userId,roleIds);

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDAO.uncorrelationRoles(userId,roleIds);

    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDAO.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDAO.findPermissions(username);
    }
}
