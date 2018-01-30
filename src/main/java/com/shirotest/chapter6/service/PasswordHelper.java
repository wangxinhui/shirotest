package com.shirotest.chapter6.service;

import com.shirotest.chapter6.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: by Administrator on 2018/1/30.
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algoreithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassoword(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algoreithmName,user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),hashIterations).toHex();
        user.setPassword(newPassword);
    }
}
