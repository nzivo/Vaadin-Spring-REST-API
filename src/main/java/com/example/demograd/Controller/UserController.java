package com.example.demograd.Controller;

import com.example.demograd.Entity.User;
import com.example.demograd.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/post")
    public User save(@RequestBody User user){
        return  userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUserList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") int id){
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public User update(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") int id){
        userService.deleteUser(id);
    }

}
