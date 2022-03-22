package com.example.gestionuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping("/users")
    public User addUser(@RequestBody User newUser){
        return userService.addUser(newUser);
    }
    @DeleteMapping("/users")
    public void deleteAllUsers(){
        userService.deleteAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) throws Exception {
        return userService.getUserById(id);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User updateUser, @PathVariable String id)throws Exception{
        return userService.updateUser(updateUser,id);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id)throws Exception{
        userService.deleteUserById(id);
    }

}
