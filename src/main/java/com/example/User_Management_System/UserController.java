package com.example.User_Management_System;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
//        }
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()) ;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        userService.registerUser(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

}
