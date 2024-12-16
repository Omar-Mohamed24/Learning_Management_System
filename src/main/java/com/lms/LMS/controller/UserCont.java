package com.lms.LMS.controller;
import com.lms.LMS.exception.NotFound;
import com.lms.LMS.model.AuthRequest;
import com.lms.LMS.model.User;
import com.lms.LMS.service.JwtService;
import com.lms.LMS.service.UserSer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserCont
{
    private UserSer userser;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserCont(UserSer userser, JwtService jwtService, AuthenticationManager authenticationManager)
    {
        this.userser = userser;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // Build the Rest API Add user call
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User saved = userser.createUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Build the Rest API get user call
    @GetMapping("{id}")
    public ResponseEntity<User> GetUser(@PathVariable("id") Long userId)
    {
        User saved = userser.GetUser(userId);
        return ResponseEntity.ok(saved);
    }

    // Build the Rest API get all users call
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.ok(userser.getAllUsers());
    }

    // Build the Rest API update user call
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,@RequestBody User updated)
    {
        User newUser = userser.updateUser(userId, updated);
        return ResponseEntity.ok(newUser);
    }

    // Build the Rest API delete user call
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId)
    {
        userser.deleteUser(userId);
        return ResponseEntity.ok("user deleted successfully");
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new NotFound("Invalid user request!");
        }
    }
}
