package com.example.systemstest.caloriesCalculator.DTO;

import com.example.systemstest.model.Dish;
import com.example.systemstest.model.Meal;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TodayMeal {
    private long id;
    private long userId;
    private int calories;
    private LocalDateTime createdAt;

    public TodayMeal(long id, long userId, int calories, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.calories = calories;
        this.createdAt = createdAt;
    }
    public TodayMeal(Meal meal) {
        this.id = meal.getId();
        this.userId = meal.getUser().getId();
        this.calories = meal.getDishes().stream().mapToInt(Dish::getCalories).sum();
        this.createdAt = meal.getCreatedAt();
    }
}
