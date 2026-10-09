package com.findsomeone.controller;

import com.findsomeone.dto.CreateMeetupRequest;
import com.findsomeone.entity.Meetup;
import com.findsomeone.entity.User;
import com.findsomeone.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PatchMapping("/users/{id}")
    public User updateUserPartially(
            @PathVariable Long id,
            @RequestBody User user) {
        return userService.updateUserPartially(id, user);
    }
}