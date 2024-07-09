package com.intuit.controllers;


import com.intuit.models.Users;
import com.intuit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/validUser")
    public ResponseEntity<String> validUser(@RequestBody Users users) {
        if (userService.doesUserExists(users.getEmail())) {
            return ResponseEntity.ok("User verified successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or invalid credentials.");
    }

    @PostMapping("/get")
    public ResponseEntity<Users> getUser(@RequestBody Users users) {
      Users user = userService.getUser(users.getEmail());
      if(user != null) {
         return  ResponseEntity.status(HttpStatus.OK).body(user);
      }
      return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/signup")
    public Users signup(@RequestBody Users users){
       return userService.createUser(users);
    }

    @PutMapping("/update")
    public Users updateUser(@RequestBody Users users){
        return userService.updateUser(users);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody Users users) {
        if (userService.verifyUser(users.getEmail(), users.getPassword())) {
            return ResponseEntity.ok("User verified successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or invalid credentials.");
    }


}
