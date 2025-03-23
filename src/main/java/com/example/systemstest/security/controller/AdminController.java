
package com.example.systemstest.security.controller;

import com.example.systemstest.model.User;
import com.example.systemstest.security.payload.repository.RoleRepository;
import com.example.systemstest.security.payload.repository.UserRepository;
import com.example.systemstest.security.payload.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    final
    UserRepository userRepository;
    final
    RoleRepository roleRepository;
    final
    PasswordEncoder encoder;

    public AdminController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @GetMapping("/getallusers")
        public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

}