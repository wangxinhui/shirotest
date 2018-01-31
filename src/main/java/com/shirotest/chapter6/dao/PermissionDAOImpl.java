package com.shirotest.chapter6.dao;

import com.shirotest.chapter6.JdbcTemplateUtils;
import com.shirotest.chapter6.entity.Permission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class PermissionDAOImpl implements PermissionDAO{

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
    @Override
    public Permission createPermission(final Permission permission) {
        final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql,new String[]{"id"});
                psst.setString(1,permission.getPermission());
                psst.setString(2, permission.getDescription());
                psst.setBoolean(3, permission.getAvailable());
                return psst;
            }
        },keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {
        String sql = "delete from sys_roles_permissions where permission_id = ?";
        jdbcTemplate.update(sql,permissionId);

        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, permissionId);

    }
}
