package com.example.employeebackend.repo;

import com.example.employeebackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepo extends JpaRepository<Admin,Integer> {
   public Admin getByEmail(String email);
}
