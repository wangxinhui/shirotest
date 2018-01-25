package com.shirotest.chapter5;

import org.apache.shiro.codec.Base64;

public class Base64Test {
    public static void main(String[] args) {
        String hello = "hello";
        String base64encode = Base64.encodeToString(hello.getBytes());
        String base64decode = Base64.decodeToString(base64encode);
        System.out.println(base64encode);
        System.out.println(base64decode);
    }
}
