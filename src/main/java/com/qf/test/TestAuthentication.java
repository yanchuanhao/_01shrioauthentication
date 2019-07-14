package com.qf.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestAuthentication {

    @Test
    public void test1(){
        //1.加载ini文件，通过工厂创建SecurityManager对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        //2.将securityManager对象配置到应用程序中
        SecurityUtils.setSecurityManager(securityManager);
        //3.当前主体用户
        Subject subject = SecurityUtils.getSubject();
        //4.根据subject对象进行认证操作
        //根据用户输入的账户密码组成AuthenticationToken对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin","1234");
        try{
            subject.login(usernamePasswordToken);
            //5.验证登录（认证）是否成功
            if(subject.isAuthenticated()){
                System.out.println("登录成功");
            }
            //获取当前账户的身份信息
            System.out.println(subject.getPrincipal());
            //注销
            //subject.logout();
        }catch(UnknownAccountException e){
            System.out.println("未知账户");
        }catch(IncorrectCredentialsException e){
            System.out.println("密码错误");
        }catch(AuthenticationException e){
            System.out.println("登录失败");
        }



    }
}
