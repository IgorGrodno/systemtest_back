package com.example.systemstest.caloriesCalculator.repository;

import com.example.systemstest.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
