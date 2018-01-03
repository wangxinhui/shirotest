package com.shirotest.chapter1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAuthencatiorSuccess {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestAuthencatiorSuccess.class);
    @Test
    public void testAllSuccess(){
        login("classpath:shiro-authenticator-all-success.ini");


        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();
        Assert.assertEquals(2,collection.asList().size());
        Assert.assertEquals(true,subject.isAuthenticated());
    }
    private void login(String configFile){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang","123");
        subject.login(token);
    }
}
