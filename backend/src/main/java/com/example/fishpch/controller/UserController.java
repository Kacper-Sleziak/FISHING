package com.example.fishpch.controller;

import com.example.fishpch.model.User;
import com.example.fishpch.service.GlobalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@AllArgsConstructor
@CrossOrigin
@RestController
public class UserController {

    private GlobalService globalService;

    @GetMapping("/get/{id}")
    public User get(@PathVariable String id) {

        return globalService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAll() {

        return globalService.getUserAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        globalService.deleteUser(id);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/friends/{id}")
    public List<User> getFriends(@PathVariable String id) {

        return globalService.getFriendsByUser(id);
    }

    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable String name) {

        return globalService.getUserByName(name);
    }
    @PostMapping("/register")
    public User addUser(@RequestBody User user) {

        globalService.saveUser(user);

        return user;
    }
    @PutMapping("/edit")
    public User editUser(@RequestBody User user) {

        if (user.getId() == null || user.getId().isEmpty()) {
            return null;
        }

        globalService.updateUser(user);
        return user;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        String token = globalService.login(user.getName(), user.getPassword());

        if(token == null) return null;

        return user;
    }

    @PutMapping("/friend/{userId}/{friendId}")
    public User addFriend(@PathVariable String userId, @PathVariable String friendId) {

        return globalService.addFriendTouser(userId, friendId);
    }
}
