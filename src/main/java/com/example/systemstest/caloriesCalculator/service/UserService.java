package com.example.systemstest.caloriesCalculator.service;

import com.example.systemstest.model.User;
import com.example.systemstest.security.payload.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean caloricNormIsCorrect(User user, double caloric) {
        if (!user.getWeight().isNaN() && !user.getHeight().isNaN() && !user.getAge().isNaN()) {
            return caloric < ((88.36 + (13.4 * user.getWeight()) + (4.8 * user.getHeight()) - (5.7 * user.getAge())) * 1.2);
        } else return false;
    }

    public User findById(Long userid) {
        return userRepository.findById(userid).orElse(null);
    }
}


