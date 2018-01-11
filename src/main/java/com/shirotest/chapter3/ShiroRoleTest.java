package com.shirotest.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.security.PermissionCollection;
import java.util.ArrayList;
import java.util.Arrays;

public class ShiroRoleTest {

    @Test
    public void roleTest(){
        login("classpath:chapter3/shiro-role.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.hasRole("role1"));
        boolean[] booleans = subject.hasRoles(Arrays.asList("role1","role2","role3"));
        Assert.assertTrue(booleans[0]);
        Assert.assertTrue(booleans[1]);
        Assert.assertTrue(booleans[2]);

    }

    @Test
    public void roleTest1(){
        login("classpath:chapter3/shiro-role.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        //判断拥有角色：role1
        subject.checkRole("role3");

    }

    @Test
    public void roleTest2(){
        login("classpath:chapter3/shiro-permission.ini","wang","123");
        Subject subject = SecurityUtils.getSubject();
        //判断用户权限
        Assert.assertTrue(subject.isPermitted("user:update"));
        //判断用户权限
        Assert.assertTrue(subject.isPermittedAll("user:update","user:create"));
    }

    @Test
    public void roleTest3(){
        login("classpath:chapter3/shiro-jdbc-test.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
//        Assert.assertTrue(subject.hasRole("role1"));

        Assert.assertTrue(subject.isPermitted("menu:10"));

        //判断用户权限
//        Assert.assertTrue(subject.isPermitted("+user+1"));
        //判断用户权限
//        Assert.assertTrue(subject.isPermittedAll("user:update","user:create"));
    }




    private void login(String configFile,String username,String password){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
    }

}
