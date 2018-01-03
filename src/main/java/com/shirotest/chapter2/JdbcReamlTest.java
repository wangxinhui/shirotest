package com.shirotest.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class JdbcReamlTest {

    @Test
    public void jdbcTest(){
        //获取SecurityManager工厂,指定ini文件初始化
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbc-reaml-shiro.ini");
//        得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
//        得到 Subject 及创建用户名/密码身份验证 Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
//        登录,即身份验证
        try{
//        身份验证失败
            subject.login(token);
        }catch (AuthenticationException e){
            System.out.println("login in error");
        }

//        断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());
//        退出
        subject.logout();
    }
}
