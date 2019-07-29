package com.example.demograd.Service;

import com.example.demograd.Entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user);

    List<User> getAllUserList();

    User getUser(int id);

    void deleteUser(int id);
}
