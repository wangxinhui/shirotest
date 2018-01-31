package com.shirotest.chapter6.dao;

import com.shirotest.chapter6.JdbcTemplateUtils;
import com.shirotest.chapter6.entity.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class RoleDAOImpl implements RoleDAO{

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Role createRole(Role role) {
        String sql = "insert into sys_roles(role,description,available) values (?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql,new String[]{"id"});
                psst.setString(1, role.getRole());
                psst.setString(2, role.getDescription());
                psst.setBoolean(3, role.getAvailable());
                return psst;
            }
        },generatedKeyHolder);
        role.setId(generatedKeyHolder.getKey().longValue());
        return role;
    }

    @Override
    public void deleteRole(Long roleId) {
        String sql = "delete from sys_users_roles where role_id = ?";
        jdbcTemplate.update(sql, roleId);
        sql = "delete from sys_roles WHERE id = ?";
        jdbcTemplate.update(sql,roleId);
    }

    @Override
    public void correlationPermission(Long roleId, Long... permissionIds) {
        if (permissionIds==null|| permissionIds.length == 0){
            return;
        }
        String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
        for(Long permissionId : permissionIds) {
            if(!exists(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

    @Override
    public void uncorrelationPermission(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
        for(Long permissionId : permissionIds) {
            if(exists(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

    private boolean exists(Long roleId, Long permissionId) {
        String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roleId, permissionId) != 0;
    }
}
