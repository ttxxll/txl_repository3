package com.javasm.peanutwifi.service.impl;

import com.javasm.peanutwifi.Role;
import com.javasm.peanutwifi.UserInfo;
import com.javasm.peanutwifi.dao.UserInfoDao;
import com.javasm.peanutwifi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 查询所有用户表
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    /**
     * 登录时Spring Security会调用这个service进行登录验证。
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = null;

        /**
         * 从页面传来的登录名：会在此处进行联合查询，得到全部的信息，封装到Bean中
         */
        userInfo = userInfoDao.findByUserName(username);

        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus()==0?false:true, true, true, true, getAuthority(userInfo.getRoles()));

        return user;
    }

    /**
     * 返回一个list集合，集合中装入的是角色名
     *  角色名是：admin/user允许登录
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for (Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        }

        return list;
    }

}
