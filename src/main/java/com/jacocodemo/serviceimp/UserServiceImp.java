package com.jacocodemo.serviceimp;

import com.jacocodemo.bean.User;
import com.jacocodemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private static List<User> users = new ArrayList<>();

    @Override
    public List<User> getUserList() {
        return users;
    }

    @Override
    public void saveUser(String name, String age, String sex) {
        User user = new User();
        user.setSex(sex);
        user.setName(name);
        user.setAge(age);
        users.add(user);
    }
}
