package com.shirotest.chapter6.dao;

import com.shirotest.chapter6.entity.User;

import java.util.Set;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
