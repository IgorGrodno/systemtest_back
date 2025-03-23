package com.example.systemstest.caloriesCalculator.service;

import com.example.systemstest.caloriesCalculator.DTO.TodayMeal;
import com.example.systemstest.caloriesCalculator.repository.MealRepository;
import com.example.systemstest.model.Dish;
import com.example.systemstest.model.Meal;
import com.example.systemstest.model.User;
import com.example.systemstest.security.payload.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    final MealRepository mealRepository;
    final UserRepository userRepository;

    public MealService(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }


    public List<TodayMeal> getTodayMeals(Long userId) {
        return getTodayMealsByDate(userId, LocalDate.now());
    }

    public List<TodayMeal> getTodayMealsByDate(Long userId, LocalDate date) {
        LocalDateTime startDate = date.atStartOfDay(); // 00:00:00
        LocalDateTime endDate = date.atTime(LocalTime.MAX); // 23:59:59.999999999
        List<Meal> meals = mealRepository.getTodayMeals(userId, startDate, endDate);
        List<TodayMeal> todayMeals = new ArrayList<>();
        for (Meal meal : meals) {
            todayMeals.add(new TodayMeal(meal));
        }
        return todayMeals;

    }

    public Meal createMeal(Long userId, List<Dish> dishes) {
        Optional<User> User = userRepository.findById(userId);
        if (User.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        } else {
            Meal meal = new Meal();
            meal.setDishes(dishes);
            meal.setCreatedAt(LocalDateTime.now());
            meal.setUser(User.get());
            return mealRepository.save(meal);
        }
    }


    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }

    public List<TodayMeal> getMealsByDay(Long userId) {
        List<TodayMeal> result = new ArrayList<>();
        List<Meal> meals = mealRepository.getMealsByUserId(userId);
        if (meals.isEmpty()) {
            return result;
        } else {
            meals = mealRepository.getMealsByUserId(userId).stream().
                    sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt())).toList();
            LocalDate date = meals.get(0).getCreatedAt().toLocalDate();
            List<Meal> dayMeals = new ArrayList<>();
            for (int i = 0; i < meals.size(); i++) {
                if (meals.get(i).getCreatedAt().toLocalDate().isEqual(date)) {
                    dayMeals.add(meals.get(i));
                } else {
                    date = meals.get(i).getCreatedAt().toLocalDate();
                    result.add(new TodayMeal(i, meals.get(i).getUser().getId(),
                            dayMeals.stream().mapToInt(dish ->
                                    dish.getDishes().stream().mapToInt(Dish::getCalories).sum()).sum(),
                            meals.get(i).getCreatedAt()));
                }
                ;
                if (i == meals.size() - 1) {
                    result.add(new TodayMeal(i, meals.get(i).getUser().getId(),
                            dayMeals.stream().mapToInt(dish ->
                                    dish.getDishes().stream().mapToInt(Dish::getCalories).sum()).sum(),
                            meals.get(i).getCreatedAt()));
                }
            }
            return result;
        }
    }

}

