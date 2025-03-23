package com.example.systemstest.caloriesCalculator.controller;

import com.example.systemstest.caloriesCalculator.service.DishService;
import com.example.systemstest.caloriesCalculator.service.MealService;
import com.example.systemstest.caloriesCalculator.service.UserService;
import com.example.systemstest.model.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
@Slf4j
public class ProfileController {
    final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getuser/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        log.info("Fetching user with username: {}", userName);
        Optional<User> user = Optional.ofNullable(userService.findByUsername(userName));
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/edituser")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        log.info("Saving user: {}", user);
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/caloricnormiscorrect/{userid}/{caloric}")
    public ResponseEntity<Boolean> caloricNormIsCorrect(@PathVariable Long userid, @PathVariable Double caloric) {
        log.info("Checking if caloric norm is correct for user with id: {}", userid);
        Optional<User> user = Optional.ofNullable(userService.findById(userid));
        return user.map(value -> ResponseEntity.ok(userService.caloricNormIsCorrect(value, caloric)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}