
package  com.example.systemstest.authentication.controller;

import com.example.systemstest.authentication.model.User;
import com.example.systemstest.authentication.payload.repository.RoleRepository;
import com.example.systemstest.authentication.payload.repository.UserRepository;
import com.example.systemstest.authentication.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 600, allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @GetMapping("/getallusers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @DeleteMapping("/deleteuser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

}