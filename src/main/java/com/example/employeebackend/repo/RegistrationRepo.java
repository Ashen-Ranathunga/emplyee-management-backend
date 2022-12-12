package com.example.employeebackend.repo;

import com.example.employeebackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepo extends JpaRepository<Admin,Integer> {
}
