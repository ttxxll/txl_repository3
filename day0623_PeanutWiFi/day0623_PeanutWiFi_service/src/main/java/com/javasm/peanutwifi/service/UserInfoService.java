package com.javasm.peanutwifi.service;

import com.javasm.peanutwifi.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 帮助spring security完成认证操作
 */
public interface UserInfoService extends UserDetailsService {
    List<UserInfo> findAll();
}
