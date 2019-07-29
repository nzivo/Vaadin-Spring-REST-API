package com.example.demograd.impl;

import com.example.demograd.Dao.UserDao;
import com.example.demograd.Entity.User;
import com.example.demograd.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUserList() {
        return userDao.findAll();
    }

    @Override
    public User getUser(int id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }
}
