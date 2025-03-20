package com.example.systemstest.authentication.payload.repository;

import com.example.systemstest.authentication.model.ERole;
import com.example.systemstest.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}