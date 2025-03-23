package com.example.systemstest.caloriesCalculator.service;

import com.example.systemstest.caloriesCalculator.repository.DishRepository;
import com.example.systemstest.model.Dish;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DishService {
    final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void getDishById(Long id) {
        dishRepository.findById(id);
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public void save(Dish dish) {
        dishRepository.save(dish);
    }


}
