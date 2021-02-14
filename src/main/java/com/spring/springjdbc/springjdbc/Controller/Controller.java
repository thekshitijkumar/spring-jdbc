package com.spring.springjdbc.springjdbc.Controller;


import com.spring.springjdbc.springjdbc.Model.User;
import com.spring.springjdbc.springjdbc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() throws SQLException {

        return userService.getUsers();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("id") int id) throws SQLException {
//        return get user
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public void insertUser(@RequestBody User user) throws SQLException {
        //insert user
        userService.insertUser(user);
    }
    @PutMapping("/user")
    public void updateUser(@RequestBody User user) throws SQLException {
        userService.updateUser(user);
    }
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam int id) throws SQLException {
        userService.deleteUser(id);
    }


}
