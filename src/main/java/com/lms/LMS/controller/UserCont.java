package com.lms.LMS.controller;

import com.lms.LMS.dto.UserDto;
import com.lms.LMS.service.UserSer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserCont
{
    private UserSer userser;

    public UserCont(UserSer userser)
    {
        this.userser = userser;
    }

    // Build the Rest API Add user call
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto)
    {
        UserDto saved = userser.createUser(userdto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Build the Rest API get user call
    @GetMapping("{id}")
    public ResponseEntity<UserDto> GetUser(@PathVariable("id") Long userId)
    {
        UserDto saved = userser.GetUser(userId);
        return ResponseEntity.ok(saved);
    }

    // Build the Rest API get all users call
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(userser.getAllUsers());
    }

    // Build the Rest API update user call
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto updated)
    {
        UserDto newUser = userser.updateUser(userId, updated);
        return ResponseEntity.ok(newUser);
    }

    // Build the Rest API delete user call
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId)
    {
        userser.deleteUser(userId);
        return ResponseEntity.ok("user deleted successfully");
    }
}
