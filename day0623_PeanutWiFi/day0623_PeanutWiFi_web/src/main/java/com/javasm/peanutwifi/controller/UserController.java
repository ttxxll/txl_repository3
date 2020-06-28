package com.javasm.peanutwifi.controller;

import com.javasm.peanutwifi.UserInfo;
import com.javasm.peanutwifi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    //查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){

        ModelAndView mv = new ModelAndView();

        List<UserInfo> users = userInfoService.findAll();

        mv.addObject("userList", users);
        mv.setViewName("user-list");

        return mv;
    }
}
