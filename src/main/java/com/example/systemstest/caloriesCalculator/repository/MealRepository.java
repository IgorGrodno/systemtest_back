package com.example.systemstest.caloriesCalculator.repository;

import com.example.systemstest.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.user.id = :userId AND m.createdAt >= :start AND m.createdAt < :end")
    List<Meal> getTodayMeals(@Param("userId") Long userId,
                             @Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    @Query("SELECT m FROM Meal m WHERE m.user.id = :userId")
    List<Meal> getMealsByUserId(@Param("userId") Long userId);

}
