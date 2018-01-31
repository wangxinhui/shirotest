package com.shirotest.chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: by Administrator on 2018/1/31.
 */
public class chapterTest extends BaseTest {
    @Test
    public void test() {
        login("classpath:chapter6/shiro.ini", u3.getUsername() , password);
        Assert.assertTrue(subject().isAuthenticated());
    }



    @Test
    public void testLoginFailWithLimitRetryCount() {
        for(int i = 1; i <= 5; i++) {
            try {
                login("classpath:chapter6/shiro.ini", u3.getUsername(), password + "1");
            } catch (Exception e) {/*ignore*/}
        }
        login("classpath:chapter6/shiro.ini", u3.getUsername(), password + "1");

        //需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
    }

    @Test
    public void testHasRole() {
        login("classpath:chapter6/shiro.ini", u1.getUsername(), password );
        Assert.assertTrue(subject().hasRole("admin"));
    }

}
