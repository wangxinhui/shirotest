package com.shirotest.chapter6;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 简单的数据库连接池
 * @Author: by Administrator on 2018/1/30.
 */
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if (jdbcTemplate == null){
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate(){
        DruidDataSource dataSource  = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("shiro");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(10);
        return new JdbcTemplate(dataSource);
    }
}
