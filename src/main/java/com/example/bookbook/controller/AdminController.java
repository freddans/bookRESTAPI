package com.example.bookbook.controller;

import com.example.bookbook.user.User;
import com.example.bookbook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return adminService.createUser(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable long id) {
        return adminService.findUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return adminService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        return adminService.deleteUser(id);
    }
}
