package com.example.systemstest;

import authentication.model.ERole;
import authentication.model.Role;
import authentication.model.User;
import authentication.payload.repository.RoleRepository;
import authentication.payload.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = "authentication")
@EnableJpaRepositories(basePackages = "authentication.payload.repository")
@EntityScan(basePackages = "authentication.model")
public class SystemsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemsTestApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
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
        };
    }
}
