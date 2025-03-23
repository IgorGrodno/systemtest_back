package com.example.systemstest;

import com.example.systemstest.caloriesCalculator.controller.DishControllerr;
import com.example.systemstest.caloriesCalculator.service.DishService;
import com.example.systemstest.model.Dish;
import com.example.systemstest.model.ERole;
import com.example.systemstest.model.Role;
import com.example.systemstest.model.User;
import com.example.systemstest.security.payload.repository.RoleRepository;
import com.example.systemstest.security.payload.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication(scanBasePackages = "com.example.systemstest")
public class SystemsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemsTestApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, DishService dishService) {
        return args -> {
            if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
                Optional<Role> role = roleRepository.findByName(ERole.ROLE_ADMIN);
            }
            if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_USER));
                Optional<Role> role = roleRepository.findByName(ERole.ROLE_USER);
            }
            if (!userRepository.existsByUsername("admin")) {
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
                User admin = new User("admin", "admin@gmail.com",
                        passwordEncoder.encode("admin"));
                admin.setRoles(roles);
                userRepository.save(admin);
            }
            if (!userRepository.existsByUsername("simpleUser")) {
                User simpleUser = new User("simpleUser", "simpleUser@gmail.com",
                        passwordEncoder.encode("simpleUser"));
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
                simpleUser.setRoles(roles);
                userRepository.save(simpleUser);
            }
            if(dishService.getAll().isEmpty()) {
               dishService.save(new Dish("Pizza", 1000,(double)100,(double)100,(double)100));
                dishService.save(new Dish("Burger", 500,(double)50,(double)50,(double)50));
                dishService.save(new Dish("Salad", 200,(double)20,(double)20,(double)20));
                dishService.save(new Dish("Nuggets", 300,(double)30,(double)30,(double)30));
                dishService.save(new Dish("French fries", 400,(double)40,(double)40,(double)40));
            }
        };
    }
}
