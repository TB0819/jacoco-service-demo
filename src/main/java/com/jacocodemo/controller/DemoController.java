package com.jacocodemo.controller;

import com.jacocodemo.bean.User;
import com.jacocodemo.service.UserService;
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
        user.setAge("18");
        user.setName("jacocoTester");
        user.setSex("男");
        return user;
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
