package com.huidian;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ShiroTest {
    @Test
    public void shiroTest(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证状态"+authenticated);
        subject.logout();
        authenticated=subject.isAuthenticated();
        System.out.println("认证状态"+authenticated);
    }


    @Test
    public void shiroTest01(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro1.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否通过"+authenticated);
    }

    @Test
    public void test01(){
        SimpleHash simpleHash = new SimpleHash("MD5", "123456", "zxcvbn");
        System.out.println(simpleHash.toString());
    }

    @Test
    public void shiroPermission(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否通过"+authenticated);
        System.out.println("用户是否拥有角色1"+subject.hasRole("role1"));
        System.out.println("用户是否拥有角色2"+subject.hasRole("role2"));
        System.out.println("用户是否拥有多个角色"+subject.hasAllRoles(Arrays.asList("role1","role2")));


        System.out.println("是否拥有某一权限"+subject.isPermitted("user:create"));
        System.out.println("是否拥有多个权限"+subject.isPermittedAll("user:create:1","user:update"));
        subject.checkPermission("user:delete");
        subject.checkPermissions("user:create","user:delete");
    }
}
