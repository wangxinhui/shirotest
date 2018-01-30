package com.shirotest.chapter6.dao;

import com.shirotest.chapter6.entity.User;

import java.util.Set;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public interface UserDAO {
    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
