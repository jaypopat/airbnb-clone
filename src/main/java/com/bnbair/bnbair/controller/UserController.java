package com.bnbair.bnbair.controller;

import com.bnbair.bnbair.domain.User;
import com.bnbair.bnbair.exception.UserNotFoundException;
import com.bnbair.bnbair.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails)
            throws UserNotFoundException {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUsersByFullNames(@RequestParam String firstName, @RequestParam String lastName) {
        List<User> users = userService.getUsersByFirstAndLastName(firstName, lastName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<List<User>> findUsersByEmail(@RequestParam String email) {
        List<User> users = userService.getUsersByEmail(email);
        return ResponseEntity.ok(users);
    }
}
