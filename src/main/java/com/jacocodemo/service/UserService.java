package com.jacocodemo.service;

import com.jacocodemo.bean.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList();

    public void saveUser(String name,String age,String sex);

    public User getUser(String name);
}
