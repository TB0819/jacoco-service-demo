package com.jacocodemo.controller;

import com.jacocodemo.bean.User;
import com.jacocodemo.service.UserService;
import com.jacocodemo.serviceimp.UserServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/get_user",  method = {RequestMethod.POST,RequestMethod.GET})
    public User getUser(){
        User user = new User();
        if (userService.getUserList().size() > 1) {
            user.setAge("18");
            user.setName("jacocoTester");
            user.setSex("男");
        } else {
            user.setAge("109");
            user.setName("jacoco覆盖率新增修改");
            user.setSex("女");
        }
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/get_single_user",  method = {RequestMethod.POST,RequestMethod.GET})
    public User getSingleUser(String name){
        return userService.getUser(name);
    }

    @ResponseBody
    @RequestMapping(value = "/save_user",  method = {RequestMethod.POST,RequestMethod.GET})
    public void saveUser(String name, String age, String sex){
        userService.saveUser(name,age,sex);
    }

    @ResponseBody
    @RequestMapping(value = "/get_users",  method = {RequestMethod.POST,RequestMethod.GET})
    public List<User> saveUser(){
        return userService.getUserList();
    }
}
