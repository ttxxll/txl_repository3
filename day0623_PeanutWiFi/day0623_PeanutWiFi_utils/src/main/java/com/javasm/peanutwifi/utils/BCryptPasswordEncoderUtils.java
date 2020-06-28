package com.javasm.peanutwifi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//对密码进行加密的工具类
//加密后密码很长，要注意原来数据库设置的密码长度类型可能存不住
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    //测试一下
    public static void main(String[] args){
        String password = "123";
        String pwd  = encodePassword(password);

        System.out.println(pwd);
    }

}
