package com.java.ResfulAPI.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.ResfulAPI.domain.User;
import com.java.ResfulAPI.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/hi")
    public String hello(@RequestBody String message) {
        return message;
    }

    @PostMapping
    public User postMethodName(@RequestBody User user) {

        User createUser = userService.createUser(user);
        return createUser;
    }

    @GetMapping("/a")
    public List<User> getAll() {
        List<User> all = userService.getAllUsers();
        return all;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id); // ✅ sửa ở đây
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public User putUpdateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User updateUser = userService.updateUser(id, user);
        return updateUser;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "Deleted successfully";
    }
    // @PostMapping
    // public ResponseEntity<User> createUser(@RequestBody User user) {
    // User createdUser = userService.createUser(user);
    // return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    // }

    // @GetMapping
    // public List<User> getAllUsers() {
    // List<User> users = userService.getAllUsers();
    // return users;
    // }

    // // @GetMapping
    // // public ResponseEntity<List<User>> getAllUsers() {
    // // List<User> users = userService.getAllUsers(); // ✅ sửa ở đây
    // // return ResponseEntity.ok(users);
    // // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    // userService.deleteUser(id);
    // return ResponseEntity.ok("Deleted successfully");
    // }
}