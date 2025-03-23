package com.example.systemstest.caloriesCalculator.controller;

import com.example.systemstest.caloriesCalculator.DTO.TodayMeal;
import com.example.systemstest.caloriesCalculator.service.DishService;
import com.example.systemstest.caloriesCalculator.service.MealService;
import com.example.systemstest.caloriesCalculator.service.UserService;
import com.example.systemstest.model.Dish;
import com.example.systemstest.model.Meal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
@Slf4j
public class DishControllerr {
    final UserService userService;
    final DishService dishService;
    final MealService mealService;

    public DishControllerr(MealService mealService, UserService userService, DishService dishService)
    {
        this.userService = userService;
        this.dishService = dishService;
        this.mealService = mealService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Dish>> getAll() {
        log.info("getsDish: {}", "userId");
        return ResponseEntity.ok(dishService.getAll());
    }

    @GetMapping("/todaymeals/{userId}")
    public ResponseEntity<List<TodayMeal>> getTodayMeals(@PathVariable Long userId) {
        log.info("getTodayMeals: {}", userId);
        return ResponseEntity.ok(mealService.getTodayMeals(userId));
    }

    @GetMapping("/daymeals/{userId}")
    public ResponseEntity<List<TodayMeal>> getDayMeals(@PathVariable Long userId) {
        log.info("getDayMeals: {}", userId);
        return ResponseEntity.ok(mealService.getMealsByDay(userId));
    }

    @PostMapping("/meal/{userId}")
    public ResponseEntity<Meal> createMeal(@PathVariable Long userId, @RequestBody List<Dish> dishes)
    {
        log.info("userId: {}", "userId");
        return ResponseEntity.ok(mealService.createMeal(userId,dishes));}

    @DeleteMapping("/meal/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        log.info("deleteMeal: {}", id);
        mealService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
