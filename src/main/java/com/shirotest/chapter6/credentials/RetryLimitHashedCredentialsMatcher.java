package com.shirotest.chapter6.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如果密码输入正确清除cache中的记录；
 * 否则cache中的重试次数+1，如果超出5次那么抛出异常表示超出重试次数了。
 * @Author: by wxh on 2018/1/30.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() throws FileNotFoundException {
        CacheManager cacheManager = CacheManager.newInstance(this.getClass().getClassLoader().getResourceAsStream("chapter6/ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        Element element = passwordRetryCache.get(username);
        if (element == null){
            element = new Element(username,new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
        if (retryCount.incrementAndGet() > 5){
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token,info);
        if (matches){
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
