package com.shirotest.chapter6.test;

import com.shirotest.chapter6.entity.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-29
 * <p>Version: 1.0
 */
public class PrincialCollectionTest extends BaseTest {

    @Test
    public void test() {
        login("classpath:chapter6/shiro-multirealm.ini", "zhang", "123");
        Subject subject = subject();
        //获取primary Principal（即第一个）
        Object primaryPrincipal1 = subject.getPrincipal();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Object primaryPrincipal2 = principalCollection.getPrimaryPrincipal();
        Assert.assertEquals(primaryPrincipal1,primaryPrincipal2);

        Set<String> realmNames = principalCollection.getRealmNames();
        System.out.println(realmNames);

        Set<Object> set =  principalCollection.asSet();
        System.out.println(set);

        Collection<User> collection = principalCollection.fromRealm("b");
        System.out.println(collection);
    }
}
